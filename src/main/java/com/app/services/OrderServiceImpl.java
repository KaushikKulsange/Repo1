package com.app.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.custom_exception.ResourceNotFound;
import com.app.dao.DineTableDao;
import com.app.dao.FoodDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderedFoodDao;
import com.app.dao.RestaurantDao;
import com.app.dao.UserDao;
import com.app.dto.CancelFoodItemDTO;
import com.app.dto.FoodOrderedDto;
import com.app.dto.OrderFoodResponseDTO;
import com.app.dto.OrderedFoodNameQuantityDTO;
import com.app.dto.ReservationDTO;
import com.app.entities.DineTable;
import com.app.entities.Food;
import com.app.entities.Order;
import com.app.entities.OrderedFood;
import com.app.entities.Restaurant;
import com.app.entities.TableStatus;
import com.app.entities.User;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

	
	private RestaurantDao restaurantDao;
	private UserDao userDao;
	private FoodDao foodDao;
	private DineTableDao dineTableDao;
	private OrderDao orderDao;
	private OrderedFoodDao orderedFoodDao;
	private ModelMapper mapper;
	
	@Override
	public String addOrder(Long userId, ReservationDTO rsDto) {
		// TODO Auto-generated method stub
		try {
			//Find User By UserId or else throw exception
			User user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFound("User Not Found"));
			//Find Restaurant By Restaurant id or else throw exception
			Restaurant restaurant = restaurantDao.findById(rsDto.getRestaurantId()).orElseThrow(()-> new ResourceNotFound("Restaurant Not Found"));
			//Find DineTable By Table Id or else throw exception
			DineTable dineTable = dineTableDao.findById(rsDto.getTableId()).orElseThrow(()-> new ResourceNotFound("Dine Table Not Found"));
			
			//Create Order instance
			Order order = new Order();
			//Mapping OrderedFood with order
			rsDto.getFoodDtoList().stream()  //Fetching List from ReservationDTO object
			.forEach((orderedFoodReq)->{
				//Fetching food from database using food id
				Food food=foodDao.findById(orderedFoodReq.getFoodId())
				.orElseThrow(()->new ResourceNotFound("Food Not Found"));
				//Creating orderedFood instance and attaching it with food and quantity sent by front end or user
				OrderedFood orderedFood=new OrderedFood(orderedFoodReq.getQuantity(),food);
				//Attaching OrderedFood to order
				order.orderFood(orderedFood);
			});
			
			//Adding Dinetable to order
			order.setBookedTable(rsDto.getTableId());
			//Changing status of booked table
			dineTable.setStatus(TableStatus.RESERVED);
			order.setRestaurant(restaurant);
			//adding order to user and making order persistent. This line actually makes hibernate to do dirty checking
			user.addOrder(order);
			return "Table Booked At restaurant "+restaurant.getName();
		}catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	@Override
	public OrderFoodResponseDTO getOrderById(Long orderId) {
		try {
			//Get Order From Database
			Order order = orderDao.findById(orderId)
			.orElseThrow(()->new ResourceNotFound("No Order Found By Id "+orderId));
			
			//Create object of response dto
			OrderFoodResponseDTO orderResponse=new OrderFoodResponseDTO();
			
			//Get List Of Ordered Food and them to list foodDesc
			List<OrderedFoodNameQuantityDTO> foodDesc=new ArrayList<>();
			order.getFoods().stream()
			.forEach((foodOrdered)->{
				foodDesc.add(new OrderedFoodNameQuantityDTO(foodOrdered.getFood().getName(),foodOrdered.getQuantity()));
			});
			//get restaurant name and address from order
			String restaurantName = order.getRestaurant().getName();
			String restaurantAddress=order.getRestaurant().getAddress();
			//get table 
			DineTable table = dineTableDao.findById(order.getBookedTable())
			.orElseThrow(()->new ResourceNotFound("Table Not Found"));
			
			//Get table number and location
			String tableLocation = table.getLocation().toString();
			Long tableNumber = table.getTableNumber();
			
			//Set all the fileds of OrderedFoodNameQuantityDTO
			orderResponse.setFoods(foodDesc);
			orderResponse.setRestaurantName(restaurantName);
			orderResponse.setTableLocation(tableLocation);
			orderResponse.setTableNumber(tableNumber);
			orderResponse.setRestaurantAddress(restaurantAddress);
			orderResponse.setBookedTime(order.getCreatedAt());
			orderResponse.setId(orderId);
			return orderResponse;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String cancelOrder(Long userId,Long orderId) {
		try {
			//Find User By UserId or else throw exception
			User user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFound("User Not Found"));
			
			//Get Order From Database
			Order order = orderDao.findById(orderId)
			.orElseThrow(()->new ResourceNotFound("No Order Found By Id "+orderId));
			
			//get table 
			DineTable table = dineTableDao.findById(order.getBookedTable())
			.orElseThrow(()->new ResourceNotFound("Table Not Found"));
			//set table status to open
			table.setStatus(TableStatus.OPEN);
			
			//remove ordered foods 
//			order.getFoods().clear();
			
			//remove orderedfood record
			order.getFoods().stream()
				.forEach((orderedFood)->orderedFoodDao.delete(orderedFood));
			
			//delete order from user
			user.cancelOrder(order);
			//remove order from database
			orderDao.delete(order);
			return "Order Cancelled Successfully";
			
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public String addFoodItemToOrder(Long userId, Long orderId, FoodOrderedDto newFoodItem) {
		try {
			//Find User By UserId or else throw exception
			User user = userDao.findById(userId).orElseThrow(()-> new ResourceNotFound("User Not Found"));
			//Get Order From Database
			Order order = orderDao.findById(orderId)
			.orElseThrow(()->new ResourceNotFound("No Order Found By Id "+orderId));
			
			//Get Food that is to be added
			Food food = foodDao.findById(newFoodItem.getFoodId())
			.orElseThrow(()->new ResourceNotFound("No Food Item Found"));
			
			OrderedFood addedFood=new OrderedFood();
			addedFood.setFood(food);
			addedFood.setQuantity(newFoodItem.getQuantity());
			
			// Add new food item to the order
			order.orderFood(addedFood);
			return "New Food Item Added";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	@Override
	public String removeFoodItemFromOrder(Long orderId, Long foodId) {
		try {
			//Get Order From Database
			Order order = orderDao.findById(orderId)
			.orElseThrow(()->new ResourceNotFound("No Order Found By Id "+orderId));
			System.out.println("foodid "+foodId+" ");
			//Get food
			OrderedFood foodObj = order.getFoods().stream()
			.filter((food)->food.getId().equals(foodId)).findFirst().orElseThrow(()->new ResourceNotFound());
			
			order.removeFood(foodObj);
			orderedFoodDao.deleteById(foodId);
			return "Food Items Updated";
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
