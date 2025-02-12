package com.app.services;

import com.app.dto.RestaurantStaffDTO;
import com.app.dto.StaffDTO;

public interface StaffService {
	String addStaff(Long restroId,StaffDTO staff);
	RestaurantStaffDTO getAllStaff(Long restroId);
	String removeStaff(Long restaurantId, Long staffId);
	String updateStaff(Long restaurantId, Long staffId,StaffDTO staff);
	RestaurantStaffDTO getStaffByRole(Long restaurantId,String role);
}
