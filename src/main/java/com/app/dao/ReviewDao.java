package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Review;

public interface ReviewDao extends JpaRepository<Review, Long>{
		
	List<Review> findByRestaurants_Id(Long restaurantId);
	
//	List<Review> findByUsers_Id(Long userId);
}
