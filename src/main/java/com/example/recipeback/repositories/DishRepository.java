package com.example.recipeback.repositories;

import com.example.recipeback.entities.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Page<Dish> findByTitleStartingWithAndApprovedIsTrue(String title, Pageable pageable);

    Page<Dish> findByTitleStartingWithAndApprovedIsFalse(String title, Pageable pageable);
}
