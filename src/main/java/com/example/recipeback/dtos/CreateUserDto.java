package com.example.recipeback.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDto {
    private String email;
    private String username;
    private String password;

}
