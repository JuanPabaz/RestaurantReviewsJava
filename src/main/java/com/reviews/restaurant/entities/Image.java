package com.reviews.restaurant.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @Column(name = "imagen_url")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_restaurante")
    private Restaurant restaurante;

    @ManyToOne
    @JoinColumn(name = "id_resena")
    private Review review;
}
