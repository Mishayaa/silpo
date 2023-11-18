package com.example.recipeback.entities;

import javax.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dishes")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "price")
    private double price;

    @ElementCollection
    @CollectionTable(name = "dish_topics", joinColumns = @JoinColumn(name = "dish_id"))
    @Column(name = "topic")
    private List<String> topics;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_ingredients",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dish_steps",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id")
    )
    private List<Step> steps;
    @Column(name = "servings")
    private int servings;

    @Column(name = "publisher_id")
    private String publisherId;

    @Column(name = "approved")
    private boolean approved;
}
