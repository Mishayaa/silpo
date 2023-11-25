package com.example.recipeback.controllers;

import com.example.recipeback.dtos.AuthTokenDtoResponse;
import com.example.recipeback.dtos.CreateUserDto;
import com.example.recipeback.dtos.RegisterDtoResponse;
import com.example.recipeback.dtos.UserCredentialsDto;
import com.example.recipeback.services.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
   private final AuthenticationService authenticationService;

    @PostMapping("/auth/login")
    public ResponseEntity<RegisterDtoResponse> login(@RequestBody UserCredentialsDto userCredentialsDto) {
        return ResponseEntity.ok(authenticationService.authenticate(userCredentialsDto));
    }

    @PostMapping("/auth/register")
    public ResponseEntity<RegisterDtoResponse> register(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.ok(authenticationService.register(createUserDto));
    }

}
