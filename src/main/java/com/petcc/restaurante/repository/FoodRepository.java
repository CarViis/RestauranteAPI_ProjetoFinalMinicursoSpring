package com.petcc.restaurante.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.petcc.restaurante.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    
}
