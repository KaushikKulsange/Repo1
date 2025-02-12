package com.app.services;

import com.app.dto.RestaurantDTO;

public interface RestaurantService {
	String addRestaurant(RestaurantDTO restro);
	RestaurantDTO getRestaurant(Long restaurantId);
	String deleteRestaurant(Long restaurantId);
	RestaurantDTO updateRestaurant(Long restaurantId,RestaurantDTO restaurant);
}
