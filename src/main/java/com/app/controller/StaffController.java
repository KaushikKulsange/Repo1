package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.StaffDTO;
import com.app.services.StaffService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/restaurants/{restaurantId}/staffs")
@AllArgsConstructor
public class StaffController {
	// COnstructor based D.I
	@Autowired
	private StaffService staffService;

	public StaffController() {
		System.out.println("StaffController Initailised As A Bean");
	}

	// Add Staff by restaurant Id
	@PostMapping
	public ResponseEntity<?> addStaff(@PathVariable Long restaurantId, @RequestBody StaffDTO staff) {
		return ResponseEntity.status(HttpStatus.CREATED).body(staffService.addStaff(restaurantId, staff));
	}
	
	//Get All Staff
	@GetMapping
	public ResponseEntity<?>  getStaffs(@PathVariable Long restaurantId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(staffService.getAllStaff(restaurantId));
	}
	
	//Remove Staff By Staff Id
	@DeleteMapping("/{staffId}")
	public ResponseEntity<?> removeStaff(@PathVariable Long restaurantId,@PathVariable Long staffId){
		return ResponseEntity.status(HttpStatus.OK)
				.body(staffService.removeStaff(restaurantId,staffId));
	}
	
	//Update Staff
	@PutMapping("/{staffId}")
	public ResponseEntity<?> updatedStaff(@PathVariable Long restaurantId,@PathVariable Long staffId,@RequestBody StaffDTO staff){
		return ResponseEntity.status(HttpStatus.OK)
				.body(staffService.updateStaff(restaurantId, staffId, staff));
	}
	
	@GetMapping("/{role}")
	public ResponseEntity<?> getStaffByROle(@PathVariable Long restaurantId,@PathVariable String role){
		return ResponseEntity.status(HttpStatus.OK)
				.body(staffService.getStaffByRole(restaurantId, role));
	}
}


















