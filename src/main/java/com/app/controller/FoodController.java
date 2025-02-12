package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FoodDTO;
import com.app.services.FoodService;

@RestController//@Controller + @ResponseBody
@RequestMapping("/restaurants/{restaurantId}/menu")
@CrossOrigin("*")
public class FoodController {
	@Autowired
	private FoodService foodService;

	public FoodController() {
		System.out.println("FoodController Instantiated");
	}
	
	//Get Food Menu from restaurant
	@GetMapping
	public ResponseEntity<?> getMenu(@PathVariable Long restaurantId){
		return ResponseEntity.ok(foodService.getAllFoods(restaurantId));
	}
	
	//Get Food By Food Id
	@GetMapping("/{foodId}")
	public ResponseEntity<?> getFood(@PathVariable Long foodId){
		return ResponseEntity.ok(foodService.getFoodById(foodId));
	}

	//add food 
	@PostMapping
	public ResponseEntity<?> addFood(@PathVariable Long restaurantId,@RequestBody FoodDTO food){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(foodService.addFood(restaurantId, food));
	}
	
	//Update Food
	@PutMapping("/{foodId}")
	public ResponseEntity<?> updateFood(@PathVariable Long foodId,@RequestBody FoodDTO food){
		return ResponseEntity.ok(foodService.updateFood(foodId, food));
	}

	//Remove Food Item From Menu
	@DeleteMapping("/{foodId}")
	public ResponseEntity<?> removeFoodFromMenu(@PathVariable Long foodId){
		return ResponseEntity.status(HttpStatus.OK).body(foodService.deleteFood(foodId));
	}
}










