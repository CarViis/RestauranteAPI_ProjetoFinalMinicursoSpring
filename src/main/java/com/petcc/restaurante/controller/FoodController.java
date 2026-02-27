package com.petcc.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<Food>> getAllFoods(){
        List<Food> foods = foodService.getAllFoods();
        return new ResponseEntity<List<Food>>(foods, HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<Food> deleteFood(Long id){
        boolean deleted = foodService.deleteFood(id);
        if (deleted) {
            return new ResponseEntity<Food>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/save")
    public ResponseEntity<Food> saveFood(@RequestBody Food food){
        try {
            Food savedFood = foodService.saveFood(food);
            if (savedFood == null) {
                return new ResponseEntity<Food>(HttpStatus.BAD_REQUEST);
            }else{
                return new ResponseEntity<Food>(savedFood, HttpStatus.CREATED);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<Food>(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Food> updateFood(@RequestBody Food food){
        try {
            Food updatedFood = foodService.updateFood(food.getId(), food.getTitle(), food.getPrice(), food.getImage());
            return new ResponseEntity<Food>(updatedFood, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<Food>(HttpStatus.NOT_FOUND);
        }
    }
}
