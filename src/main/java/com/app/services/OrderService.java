package com.app.services;

import com.app.dto.CancelFoodItemDTO;
import com.app.dto.FoodOrderedDto;
import com.app.dto.OrderFoodResponseDTO;
import com.app.dto.ReservationDTO;

public interface OrderService {
	
	String addOrder(Long userId, ReservationDTO rsDto);
	OrderFoodResponseDTO getOrderById(Long orderId);
	String cancelOrder(Long userId,Long orderId);
	String addFoodItemToOrder(Long userId,Long orderId,FoodOrderedDto newFoodItem);
	String removeFoodItemFromOrder(Long orderId,Long foodId);
	
}
