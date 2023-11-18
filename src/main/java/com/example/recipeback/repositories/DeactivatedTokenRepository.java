package com.example.recipeback.repositories;

import com.example.recipeback.entities.DeactivatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeactivatedTokenRepository extends JpaRepository<DeactivatedToken, Long> {
    Boolean existsByToken(String token);
}