package com.example.recipeback.controllers;

import com.example.recipeback.dtos.CreateDishDto;
import com.example.recipeback.dtos.FilterDishesDto;
import com.example.recipeback.entities.Dish;
import com.example.recipeback.services.DishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController("dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    @PostMapping("/get")
    public ResponseEntity<Page<Dish>> searchRestaurant(@RequestBody FilterDishesDto filterDishesDto) {
        return new ResponseEntity<>(dishService.getDishes(filterDishesDto), OK);
    }
    @PostMapping("/unapproved")
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Dish> getUnapprovedDishes(@RequestBody FilterDishesDto filterDishesDto) {
        return dishService.getUnapprovedDishes(filterDishesDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Dish getDishById(@PathVariable Long id) {
        return dishService.getDishById(id);
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public Dish approveDish(@PathVariable Long id) {
        return dishService.approveDish(id);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Dish createDish(@RequestBody CreateDishDto createDishDto) {
        return dishService.createDish(createDishDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Dish updateDish(@PathVariable Long id, @RequestBody CreateDishDto updateDishDto) {
        return dishService.updateDish(id, updateDishDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
    }
}
