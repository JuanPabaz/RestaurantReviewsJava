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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;

    @Column(name = "place")
    private Double place;

    @Column(name = "food")
    private Double food;

    @Column(name = "service")
    private Double service;

    @Column(name = "drinks")
    private Double drinks;

    @Column(name = "music")
    private Double music;

    @Column(name = "menu")
    private Double menu;

    @Column(name = "waiting_time")
    private Double waiting_time;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.LAZY, mappedBy = "review")
    private List<Image> reviewImages;

}
