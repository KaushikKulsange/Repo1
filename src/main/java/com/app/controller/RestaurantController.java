package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.RestaurantDTO;
import com.app.dto.StaffDTO;
import com.app.services.RestaurantService;
import com.app.services.StaffService;

@RestController //@Controller+ResponseBody
@RequestMapping("/restaurants")
@CrossOrigin(origins = "http://localhost:3000")
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	
	public RestaurantController() {
		System.out.println("RestaurantController Instantiated");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getRestaurant(@PathVariable Long id){
		try {
			return ResponseEntity.ofNullable(restaurantService.getRestaurant(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO restro){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurantService.addRestaurant(restro));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteRestaurant(@PathVariable Long id){
		try {
			return ResponseEntity.ok(restaurantService.deleteRestaurant(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> updateRestaurant(@PathVariable Long restaurantId,@RequestBody RestaurantDTO restro){
		return ResponseEntity.ok(restaurantService.updateRestaurant(restaurantId, restro));
	}
	
}
