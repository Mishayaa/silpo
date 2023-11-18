package com.example.recipeback.dtos;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterDishesDto {
    private int skip;
    private int limit;
    private String customFilter;
}
