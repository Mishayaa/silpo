package com.example.recipeback.entities;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "deactivated_tokens")
public class DeactivatedToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date timestamp;

    public DeactivatedToken(String token, Date timestamp) {
        this.token = token;
        this.timestamp = timestamp;
    }
}