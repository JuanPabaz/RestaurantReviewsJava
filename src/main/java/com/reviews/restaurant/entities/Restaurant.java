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
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Long idRestaurant;

    @Column(name = "restaurant_name")
    private String restuarantName;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Image> imageList;

    @OneToMany(targetEntity = Review.class, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Review> reviewList;

}
