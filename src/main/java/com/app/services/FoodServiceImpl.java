package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.custom_exception.NoContentException;
import com.app.custom_exception.ResourceNotFound;
import com.app.dao.FoodDao;
import com.app.dao.OrderedFoodDao;
import com.app.dao.RestaurantDao;
import com.app.dto.FoodDTO;
import com.app.entities.Food;
import com.app.entities.OrderedFood;
import com.app.entities.Restaurant;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class FoodServiceImpl implements FoodService {
	private FoodDao foodDao;
	private RestaurantDao restaurantDao;
	private ModelMapper mapper;
	private OrderedFoodDao orderedFoodDao;

	// Business Logic To add food
	@Override
	public String addFood(Long restaurantId, FoodDTO foodDto) {
		// Get Restaurant by id or else throw exception
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFound("Restaurant Not Found"));

		// Using modelmapper map FoodDTO to Food Entity
		Food food = mapper.map(foodDto, Food.class);
		// Creating relation of food with restaurant as restaurantId can not be null in
		// food table
		food.setRestaurant(restaurant);
		// Making food persistent
		foodDao.save(food);

		return "Food Added Successfully";
	}

	@Override
	public List<FoodDTO> getAllFoods(Long restaurantId) {
		// Get Restaurant by id or else throw exception
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(() -> new ResourceNotFound("Restaurant Not Found"));
		// Get All Foods with provided restaurantId as forign key
		List<Food> foods = foodDao.findByRestaurantId(restaurantId)
				.orElseThrow(() -> new NoContentException("No Food Added To Restaurant " + restaurant.getName()));

		// Map List<Food> to List<FoodDTO>
		List<FoodDTO> foodList = foods.stream().map((food) -> mapper.map(food, FoodDTO.class))
				.collect(Collectors.toList());
		return foodList;
	}

	// Get Food from Id
	@Override
	public FoodDTO getFoodById(Long FoodId) {
		Food food = foodDao.findById(FoodId).orElseThrow(() -> new ResourceNotFound("Food Not Found"));
		return mapper.map(food, FoodDTO.class);
	}

	// Delete Food Item By Id
	@Override
	public String deleteFood(Long FoodId) {
		
		// validate food item is there in the database
		Food food = foodDao.findById(FoodId).orElseThrow(() -> new ResourceNotFound("Food Not Found"));
		
		//Check if food is there in any orders
		List<OrderedFood> orders = orderedFoodDao.findByFood(food);
		//Set them to null else foreign key constraint will occur while deleteing food
		if(orders!=null)
			orders.stream().forEach((order)->order.setFood(null));
		
		// make food item removed using method of crudrepository
		// delete(persistentEntity)
		foodDao.delete(food);
		return food.getName() + " removed from menu";
	}

	@Override
	public FoodDTO updateFood(Long FoodId, FoodDTO foodDto) {
		// validate food item is there in the database
		if(foodDao.existsById(FoodId)) {
			Food food = foodDao.findById(FoodId).orElseThrow(() -> new ResourceNotFound("Food Not Found"));
			food.setName(foodDto.getName());
			food.setDescription(foodDto.getDescription());
			food.setPrice(foodDto.getPrice());
			return mapper.map(food, FoodDTO.class);
		}else {
			throw new ResourceNotFound("Resource Not Found Exception");
		}
	}

}
