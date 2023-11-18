package com.example.recipeback.entities;

import jakarta.validation.constraints.Email;

import javax.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Email должен соотвестновать паттерну *@*.*")
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private UserRole role;
}
