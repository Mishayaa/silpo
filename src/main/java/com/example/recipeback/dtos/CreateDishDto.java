package com.example.recipeback.dtos;

import com.example.recipeback.entities.Ingredient;
import com.example.recipeback.entities.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDishDto {
    private String title;
    private String url;
    private String description;
    private Double price;
    private List<String> topics;
    private List<Ingredient> ingredients;
    private String publisher;
    private int servings;
    private List<Step> steps;
    private int duration;


}
