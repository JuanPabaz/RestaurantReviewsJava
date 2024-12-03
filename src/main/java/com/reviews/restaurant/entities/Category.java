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
    @Column(name = "id_categoria")
    private Long idCategoria;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "descripcion" , columnDefinition = "TEXT")
    private String descripcion;

    @OneToMany(targetEntity = Restaurant.class, fetch = FetchType.LAZY, mappedBy = "categoria")
    private List<Restaurant> restaurantes;

    @OneToOne
    @JoinColumn(name = "id_imagen")
    private Image imagenCategoria;

}
