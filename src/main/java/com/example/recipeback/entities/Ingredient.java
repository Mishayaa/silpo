package com.example.recipeback.entities;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingridients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "unit", nullable = false)
    private String unit;
    @Column(name = "quantity", nullable = false)
    private Long quantity;
    @Column(name = "price", nullable = false)
    private Double price;
    @Column(name = "mainImg", nullable = false)
    private String mainImg;
    @ManyToMany(mappedBy = "ingredients")
    private List<Dish> dishes;
}
