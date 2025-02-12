package com.app.controller;

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

import com.app.dto.AddReviewToRestaurantDto;
import com.app.dto.ReviewDto;
import com.app.services.ReviewService;

@RestController
@RequestMapping("/restaurant/{restaurantId}/reviews")
public class ReviewController {

	@Autowired
	public ReviewService reviewService;

//	@GetMapping("/getAllReviewsPostedByUser/{userId}")
//	public ResponseEntity<?> getAllReviewsPostedByUserController(@PathVariable Long userId) {
//		System.out.println("In getAllReviewsPostedByUserController: " + getClass());
//		return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.getAllReviewsPostedByUser(userId));
//	}

	@PostMapping("/addNewReview/{userId}")
	public ResponseEntity<?> addNewReviewController(@RequestBody AddReviewToRestaurantDto review,
			@PathVariable Long restaurantId, @PathVariable Long userId) {
		System.out.println("In getAllReviewsPostedByUserController: " + getClass());
		return ResponseEntity.ok(reviewService.addNewReview(review, restaurantId, userId));
	}

	@DeleteMapping("/deleteReview/{userId}")
	public ResponseEntity<?> deleteReviewController(@RequestBody ReviewDto review, @PathVariable Long restaurantId,
			@PathVariable Long userId) {
		System.out.println("In getAllReviewsPostedByUserController: " + getClass());
		return ResponseEntity.ok(reviewService.deleteReview(review, restaurantId, userId));
	}

	@PutMapping("/updateReview/{userId}")
	public ResponseEntity<?> updateReviewController(@RequestBody ReviewDto review, @PathVariable Long restaurantId,
			@PathVariable Long userId) {
		System.out.println("In getAllReviewsPostedByUserController: " + getClass());
		return ResponseEntity.ok(reviewService.updateReview(review, restaurantId, userId));
	}

	@GetMapping("/getAllReviewsPostedByRestaurant")
	public ResponseEntity<?> findReviewByRestaurantIdController(@PathVariable Long restaurantId) {
		System.out.println("In getAllReviewsPostedByUserController: " + getClass());
		return ResponseEntity.status(HttpStatus.FOUND).body(reviewService.findReviewByRestaurantId(restaurantId));
	}
}
