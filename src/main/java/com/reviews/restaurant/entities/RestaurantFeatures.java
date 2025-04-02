package com.reviews.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurant_features")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantFeatures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant_feature")
    private Long idRestaurantFeature;

    @Column(name = "feature")
    private String feature;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

}
