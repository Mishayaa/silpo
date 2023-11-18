package com.example.recipeback.jwt;

import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtToken {
    private UUID id;
    private String subject;
    private Long userId;
    private List<String> authorities;
    private Instant createdAt;
    private Instant expiredAt;
}
