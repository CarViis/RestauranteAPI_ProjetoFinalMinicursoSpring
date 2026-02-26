package com.petcc.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petcc.restaurante.model.Food;
import com.petcc.restaurante.service.FoodService;

@RestController
@RequestMapping("/foods")
public class FoodController {
    @Autowired
    private FoodService foodService;

    @GetMapping("/get")
    public List<Food> getAllFoods(){
        return foodService.getAllFoods();
    }
    @DeleteMapping("/delete")
    public void deleteFood(Long id){
        foodService.deleteFood(id);
    }
    @PostMapping("/save")
    public void saveFood(String title, double price, String image){
        foodService.saveFood(title, price, image);
    }
    @PutMapping("/update")
    public void updateFood(Long id, String title, double price, String image){
        foodService.updateFood(id, title, price, image);
    }



}
