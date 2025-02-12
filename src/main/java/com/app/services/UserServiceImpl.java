package com.app.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFound;
import com.app.dao.FoodDao;
import com.app.dao.UserDao;
import com.app.entities.Food;
import com.app.entities.Order;
import com.app.entities.OrderedFood;
import com.app.entities.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private FoodDao foodDao;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public String addOrder(Long userId, Long foodId, Long quantity) {
		//Find Food By Id
		Food food = foodDao.findById(foodId)
		.orElseThrow(()->new ResourceNotFound("Unable To Find Food"));
		
		//Create Transient entity of OrderedFood
		OrderedFood orderedFood=new OrderedFood(quantity);
		//Add food to Orderedfood
		orderedFood.setFood(food);
		
		//Create Transient entity of Order
		Order order=new Order();
		order.orderFood(orderedFood);
		
		//Find User By User Id
		User user = userDao.findById(userId)
		.orElseThrow(()->new ResourceNotFound("Unable To Find User"));
		user.addOrder(order);
		return "Order Successful";
	}

}
