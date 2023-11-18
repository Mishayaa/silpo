package com.example.recipeback.services;

import com.example.recipeback.dtos.CreateDishDto;
import com.example.recipeback.dtos.FilterDishesDto;
import com.example.recipeback.entities.Dish;
import com.example.recipeback.exception.ResourceNotFoundException;
import com.example.recipeback.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DishService {

    private final DishRepository dishRepository;

    public Page<Dish> getDishes(FilterDishesDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getSkip(), filterDto.getLimit());
        return dishRepository.findByTitleStartingWithAndApprovedIsTrue(filterDto.getCustomFilter(), pageable);
    }

    public Page<Dish> getUnapprovedDishes(FilterDishesDto filterDto) {
        Pageable pageable = PageRequest.of(filterDto.getSkip(), filterDto.getLimit());
        return dishRepository.findByTitleStartingWithAndApprovedIsFalse(filterDto.getCustomFilter(), pageable);
    }

    public Dish getDishById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dish not found"));
    }

    @Transactional
    public Dish createDish(CreateDishDto createDishDto) {
        Dish dish = new Dish();
        dish.setTitle(createDishDto.getTitle());
        dish.setDescription(createDishDto.getDescription());
        dish.setPrice(createDishDto.getPrice());
        dish.setDuration(createDishDto.getDuration());
        dish.setIngredients(createDishDto.getIngredients());
        dish.setSteps(createDishDto.getSteps());
        dish.setServings(createDishDto.getServings());
        dish.setUrl(createDishDto.getUrl());
        dish.setTopics(createDishDto.getTopics());

        return dishRepository.save(dish);
    }

    @Transactional
    public Dish approveDish(Long id) {
        Dish dish = getDishById(id);
        dish.setApproved(true);
        return dishRepository.save(dish);
    }

    @Transactional
    public Dish updateDish(Long id, CreateDishDto updateDishDto) {
        Dish dish = getDishById(id);
        // update dish properties from updateDishDto
        return dishRepository.save(dish);
    }

    @Transactional
    public void deleteDish(Long id) {
        if (!dishRepository.existsById(id)) {
            throw new ResourceNotFoundException("Dish not found");
        }
        dishRepository.deleteById(id);
    }
}
