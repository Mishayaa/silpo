package com.example.recipeback.services;

import com.example.recipeback.dtos.RegisterDtoResponse;
import com.example.recipeback.mappers.AccessTokenSerializer;
import com.example.recipeback.config.EncoderConfig;
import com.example.recipeback.dtos.AuthTokenDtoResponse;
import com.example.recipeback.dtos.CreateUserDto;
import com.example.recipeback.dtos.UserCredentialsDto;
import com.example.recipeback.entities.Token;
import com.example.recipeback.entities.User;
import com.example.recipeback.entities.UserRole;
import com.example.recipeback.exception.EntityAlreadyExistException;
import com.example.recipeback.jwt.JwtToken;
import com.example.recipeback.jwt.JwtTokenUtils;
import com.example.recipeback.repositories.DeactivatedTokenRepository;
import com.example.recipeback.repositories.TokenRepository;
import com.example.recipeback.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;
    private final AccessTokenSerializer accessTokenSerializer;
    private final UserRepository userRepository;
    private UserService userService;
    private final TokenRepository tokenRepository;
    private final DeactivatedTokenRepository deactivatedTokenRepository;
    private EncoderConfig encoderConfig;
    @Value("${jwt.secret-key}")
    private String key;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired

    public void setEncoderConfig(EncoderConfig encoderConfig) {
        this.encoderConfig = encoderConfig;
    }

    @Transactional
    public RegisterDtoResponse register(CreateUserDto createUserDto) {
        Optional<User> userInDbWithUsername = userRepository.findByUsername(createUserDto.getUsername());
        Optional<User> userInDbWithEmail = userRepository.findByEmail(createUserDto.getEmail());
        if (userInDbWithUsername.isPresent() || userInDbWithEmail.isPresent()) {
            throw new EntityAlreadyExistException("Пользователь с таким именем или email уже существует");
        }

        User user = new User();
        user.setEmail(createUserDto.getEmail());
        user.setUsername(createUserDto.getUsername());
        user.setPassword(encoderConfig.passwordEncoder().encode(createUserDto.getPassword()));
        user.setRole(UserRole.USER);
        userRepository.save(user);
        UserDetails userDetails = userService.loadUserByUsername(user.getUsername());
        JwtToken accessToken = jwtTokenUtils.createToken(user, userDetails);
        String accessTokenString = accessTokenSerializer.apply(accessToken);

        Token tokens = Token.builder()
                .accessToken(accessTokenString)
                .accessTokenExpiry(accessToken.getExpiredAt().toString())
                .build();

        tokenRepository.save(tokens);

        return RegisterDtoResponse.builder()
                .accessToken(accessTokenString)
                .user(user)
                .build();
    }

    @Transactional
    public RegisterDtoResponse authenticate(UserCredentialsDto authLoginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authLoginRequest.getUsername(),
                        authLoginRequest.getPassword()
                )
        );
        UserDetails userDetails = userService.loadUserByUsername(authLoginRequest.getUsername());
        User user = userService.findByUsername(userDetails.getUsername());

        JwtToken accessToken = jwtTokenUtils.createToken(user, userDetails);
        String accessTokenString = accessTokenSerializer.apply(accessToken);

        Token tokens = Token.builder()
                .accessToken(accessTokenString)
                .accessTokenExpiry(accessToken.getExpiredAt().toString())
                .build();

        tokenRepository.save(tokens);

        return RegisterDtoResponse.builder()
                .accessToken(accessTokenString)
                .user(user)
                .build();
    }

    public User getUserFromToken(String jwsString) {
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwsString);
        Claims claims = jws.getBody();

        Optional<User> user =
                userRepository.findById(Long.parseLong(claims.get("userId").toString()));

        return user.get();
    }
}
