package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.RestaurantDao;
import com.app.dto.RestaurantDTO;
import com.app.entities.Restaurant;
import com.app.entities.RestaurantType;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RestaurantServiceImpl implements RestaurantService{
	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String addRestaurant(RestaurantDTO restro) {
		Restaurant restaurant=mapper.map(restro, Restaurant.class);
		restaurant.setFoodType(RestaurantType.valueOf(restro.getFoodType()));
		Restaurant persistentRestro = restaurantDao.save(restaurant);
		return "Restaurant Added";
	}

	@Override
	public RestaurantDTO getRestaurant(Long restaurantId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
		.orElseThrow(()->new RuntimeException("Restaurant Not Found By Id "+restaurantId));
		return mapper.map(restaurant, RestaurantDTO.class);
	}

	@Override
	public String deleteRestaurant(Long restaurantId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(()->new RuntimeException("Restaurant Not Found By Id "+restaurantId));
		restaurantDao.delete(restaurant);
		return "Deleted Restaurant Successfully";
	}

	@Override
	public RestaurantDTO updateRestaurant(Long restaurantId, RestaurantDTO restaurant) {
		Restaurant restro = restaurantDao.findById(restaurantId)
				.orElseThrow(()->new RuntimeException("Restaurant Not Found By Id "+restaurantId));
		restro.setAddress(restaurant.getAddress());
		restro.setName(restaurant.getName());
		restro.setEmail(restaurant.getEmail());
		restro.setPassword(restaurant.getPassword());
		restro.setFoodType(RestaurantType.valueOf(restaurant.getFoodType()));
		restaurantDao.save(restro);
		return mapper.map(restro, RestaurantDTO.class);
	}
	
	

}
