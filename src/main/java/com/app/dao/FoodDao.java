package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Food;
import com.app.entities.Restaurant;

public interface FoodDao extends JpaRepository<Food, Long> {
	Optional<List<Food>> findByRestaurantId(Long restaurantId);
}
