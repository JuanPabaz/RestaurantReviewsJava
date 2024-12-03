package com.reviews.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "category")
    private String category;

    @Column(name = "description" , columnDefinition = "TEXT")
    private String description;

    @OneToMany(targetEntity = Restaurant.class, fetch = FetchType.LAZY, mappedBy = "category")
    private List<Restaurant> restaurants;

    @OneToOne
    @JoinColumn(name = "id_image")
    private Image imageCategory;

}
