package com.app.services;

import java.util.List;

import com.app.dto.FoodDTO;

public interface FoodService {
	String addFood(Long restaurantId,FoodDTO food);
	List<FoodDTO> getAllFoods(Long restaurantId);
	FoodDTO getFoodById(Long FoodId);
	String deleteFood(Long FoodId);
	FoodDTO updateFood(Long FoodId,FoodDTO food);
}
