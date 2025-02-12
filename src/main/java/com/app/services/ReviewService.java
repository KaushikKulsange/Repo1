package com.app.services;

import java.util.List;

import com.app.dto.AddReviewToRestaurantDto;
import com.app.dto.ReviewDto;

public interface ReviewService {

//	List<ReviewDto> getAllReviewsPostedByUser(Long userId);

	String addNewReview(AddReviewToRestaurantDto review, Long restaurantId, Long userId);

	String deleteReview(ReviewDto review, Long restaurantId, Long userId);

	String updateReview(ReviewDto review, Long restaurantId, Long userId);

	List<ReviewDto> findReviewByRestaurantId(Long restaurantId);
}
