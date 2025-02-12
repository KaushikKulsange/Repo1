package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFoundException;
import com.app.dao.RestaurantDao;
import com.app.dao.ReviewDao;
import com.app.dao.UserDao;
import com.app.dto.AddReviewToRestaurantDto;
import com.app.dto.ReviewDto;
import com.app.entities.Review;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;

	@Autowired
	private RestaurantDao restaurantDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper modelMapper;

//	@Override
//	public List<ReviewDto> getAllReviewsPostedByUser(Long userId) {
//		// TODO Auto-generated method stub
//		System.out.println("In getAllReviewsOfRestaurant: " + getClass());
//		try {
//			if (userDao.existsById(userId)) {
//				List<Review> reviewList = reviewDao.findByUsers_Id(userId);
//				List<ReviewDto> reviewListDto = new ArrayList<>();
//				for (Review r : reviewList) {
//					reviewListDto.add(modelMapper.map(r, ReviewDto.class));
//				}
//				return reviewListDto;
//			}
//		} catch (RuntimeException e) {
//			// TODO: handle exception
//			throw new ResourceNotFoundException("Invalid UserId");
//		}
//		throw new ResourceNotFoundException("Invalid UserId");
//	}

	@Override
	public String addNewReview(AddReviewToRestaurantDto review, Long restaurantId, Long userId) {
		// TODO Auto-generated method stub
		System.out.println("In getAllReviewsOfRestaurant: " + getClass());
		try {
			if (userDao.existsById(userId)) {
				if (restaurantDao.existsById(restaurantId)) {
					reviewDao.save(modelMapper.map(review, Review.class));
					return "Review Added successfully!!";
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
		}
		throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
	}

	@Override
	public String deleteReview(ReviewDto review, Long restaurantId, Long userId) {
		// TODO Auto-generated method stub
		System.out.println("In getAllReviewsOfRestaurant: " + getClass());
		try {
			if (userDao.existsById(userId)) {
				if (restaurantDao.existsById(restaurantId)) {
					reviewDao.delete(modelMapper.map(review, Review.class));
					return "Review deleted successfully!!";
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
		}
		throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
	}

	@Override
	public String updateReview(ReviewDto review, Long restaurantId, Long userId) {
		// TODO Auto-generated method stub
		System.out.println("In getAllReviewsOfRestaurant: " + getClass());
		try {
			if (userDao.existsById(userId)) {
				if(restaurantDao.existsById(restaurantId)) {
//					reviewDao.findById(modelMapper.map(review, Review.class).getId());
					Review rev = reviewDao.findById(modelMapper.map(review, Review.class).getId())
							.orElseThrow(()-> new ResourceNotFoundException("Invalid UserId / RestaurantId"));
					reviewDao.save(rev);
					return "Review updated successfully!!";
				}
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
		}
		throw new ResourceNotFoundException("Invalid UserId / RestaurantId");
	}

	@Override
	public List<ReviewDto> findReviewByRestaurantId(Long restaurantId) {
		// TODO Auto-generated method stub
		System.out.println("In getAllReviewsOfRestaurant: " + getClass());
		try {
			if (restaurantDao.existsById(restaurantId)) {
				List<Review> reviewList = reviewDao.findByRestaurants_Id(restaurantId);
				List<ReviewDto> reviewListDto = new ArrayList<>();
				for (Review r : reviewList) {
					reviewListDto.add(modelMapper.map(r, ReviewDto.class));
				}
				return reviewListDto;
			}
		} catch (RuntimeException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException("Invalid RestaurantId");
		}
		throw new ResourceNotFoundException("Invalid RestaurantId");
	}

}
