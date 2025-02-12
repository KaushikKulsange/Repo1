package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Restaurant;

public interface RestaurantDao extends JpaRepository<Restaurant, Long> {
	
}
