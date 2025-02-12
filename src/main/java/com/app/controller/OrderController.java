package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.FoodOrderedDto;
import com.app.dto.ReservationDTO;
import com.app.services.OrderService;

@RestController
@RequestMapping("/users/{userId}/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	public OrderController() {
		System.out.println("UserController Initailised");
	}
	
	@PostMapping
	public ResponseEntity<?> placeOrder(@PathVariable Long userId,@RequestBody ReservationDTO reservation ){
		System.out.println(reservation.getReservationTime()+" Time");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.addOrder(userId, reservation));
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity<?> getOrder(@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.getOrderById(orderId));
	}
	
	@DeleteMapping("/{orderId}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long userId,@PathVariable Long orderId){
		return ResponseEntity.ok(orderService.cancelOrder(userId,orderId));
	}
	
	@PostMapping("/{orderId}/additem")
	public ResponseEntity<?> addNewFoodItemToOrder(@PathVariable Long userId,@PathVariable Long orderId,
							@RequestBody FoodOrderedDto newFood){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.addFoodItemToOrder(userId, orderId, newFood));
	}
	
	@GetMapping("/{orderId}/removeitem")
	public ResponseEntity<?> removeFoodItemFromOrder(@PathVariable Long orderId,@RequestParam Long foodId){
		System.out.println("hjvgjvghvchgc");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(orderService.removeFoodItemFromOrder(orderId, foodId));
	}
}
