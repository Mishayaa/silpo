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
@Table(name = "steps")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "description", nullable = false)
    private String description;
    @ManyToMany(mappedBy = "steps")
    private List<Dish> dishes;
}
