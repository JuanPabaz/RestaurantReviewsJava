package com.reviews.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_restaurant")
    private Long idRestaurant;

    @Column(name = "restaurant_name")
    private String restuarantName;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Image> imageList;

    @OneToMany(targetEntity = Review.class, fetch = FetchType.LAZY, mappedBy = "restaurant")
    private List<Review> reviewList;

}
