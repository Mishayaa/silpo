package com.example.recipeback.dtos;

import com.example.recipeback.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDtoResponse {
    private User user;
    private String accessToken;
}
