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
    @Column(name = "id_restaurante")
    private Long idRestaurante;

    @Column(name = "nombre_restaurante")
    private String nombreRestaurante;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Category category;

    @OneToMany(targetEntity = Image.class, fetch = FetchType.LAZY, mappedBy = "restaurante")
    private List<Image> imagenList;

    @OneToMany(targetEntity = Review.class, fetch = FetchType.LAZY, mappedBy = "restaurante")
    private List<Review> resenaList;

}
