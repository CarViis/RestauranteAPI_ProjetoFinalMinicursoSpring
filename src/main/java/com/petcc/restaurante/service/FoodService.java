package com.petcc.restaurante.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcc.restaurante.model.Food;
import com.petcc.restaurante.repository.FoodRepository;

@Service
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }
    public void saveFood(String title, double price, String image){
        foodRepository.save(new Food(title, price, image));
    }
    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }
    public void updateFood(Long id, String title, double price, String image){
        Food food = foodRepository.findById(id).orElseThrow(() -> new RuntimeException("Food not found"));
        food.setTitle(title);
        food.setPrice(price);
        food.setImage(image);
        foodRepository.save(food);
    }
}
