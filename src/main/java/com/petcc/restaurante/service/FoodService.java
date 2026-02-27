package com.petcc.restaurante.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcc.restaurante.model.Food;
import com.petcc.restaurante.repository.FoodRepository;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public List<Food> getAllFoods(){
        return foodRepository.findAll().stream().collect(Collectors.toList());
    }
    public Food saveFood(Food food){
        Food savedFood = foodRepository.save(food);
        return savedFood;
    }
    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }
    public Food updateFood(Long id, String title, double price, String image){
        Food food = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        food.setTitle(title);
        food.setPrice(price);
        food.setImage(image);
        foodRepository.save(food);
        return food;
    }
}
