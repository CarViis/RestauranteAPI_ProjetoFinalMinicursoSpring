package com.petcc.restaurante.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false) private String title;
    @Column (nullable = false) private double price;
    @Column (nullable = false) private String image;
    public Food(String title, double price, String image){
        this.title = title;
        this.price = price;
        this.image = image;
    }
}
