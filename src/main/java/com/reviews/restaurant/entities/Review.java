package com.reviews.restaurant.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Long idReview;

    @Column(name = "title")
    private String title;

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
    private Double waitingTime;

    @Column(name = "ambient")
    private Double ambient;

    @Column(name = "total_score")
    private Double totalScore;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "id_restaurant")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.LAZY, mappedBy = "review")
    private List<Image> reviewImages;

    public void updateTotalScore() {
        this.totalScore = (this.getAmbient() + this.getFood() + this.getDrinks()
                + this.getMenu() + this.getService() + this.getMusic()
                + this.getWaitingTime() + this.getPlace()) / 8;
    }

}
