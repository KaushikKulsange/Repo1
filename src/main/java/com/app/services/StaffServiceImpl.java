package com.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.custom_exception.NoContentException;
import com.app.custom_exception.ResourceNotFound;
import com.app.dao.RestaurantDao;
import com.app.dao.StaffDao;
import com.app.dto.RestaurantStaffDTO;
import com.app.dto.StaffDTO;
import com.app.entities.Restaurant;
import com.app.entities.Staff;
import com.app.entities.StaffRole;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class StaffServiceImpl implements StaffService {
	//Constructor based DI (Mandatory)
	private RestaurantDao restaurantDao;
	private StaffDao staffDao;
	private ModelMapper mapper;
	
	@Override
	public String addStaff(Long restroId,StaffDTO staff) {
		//Get Restaurant By Id or else throw Exception
		Restaurant restaurant = restaurantDao.findById(restroId)
		.orElseThrow(()->new ResourceNotFound("No Restaurant Found With Id "+restroId));
		
		//Add Staff to restaurant by attaching it to persistent entity of restaurant
		restaurant.addStaff(mapper.map(staff,Staff.class));
		return "Added Successfully";
	}

	@Override
	public RestaurantStaffDTO getAllStaff(Long restaurantId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
		.orElseThrow(()->new ResourceNotFound("No Restaurant Found With Id "+restaurantId));
		
		List<StaffDTO> staffs = restaurant.getStaffs().stream()
		.map((staff)->mapper.map(staff, StaffDTO.class)).collect(Collectors.toList());
		if(staffs.size()==0) {
			throw new NoContentException("No Staff for "+ restaurant.getName());
		}
		RestaurantStaffDTO restroStaffDto=new RestaurantStaffDTO();
		restroStaffDto.setName(restaurant.getName());
		restroStaffDto.setStaff(staffs);
		return restroStaffDto;
	}


	@Override
	public String removeStaff(Long restaurantId, Long staffId) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(()->new ResourceNotFound("No Restaurant Found With Id "+restaurantId));
		Staff staff = staffDao.findById(staffId)
		.orElseThrow(()->new ResourceNotFound("No Staff Found With Id "+staffId));
		restaurant.removeStaff(staff);
		return "Deleted Successfully";
	}

	@Override
	public String updateStaff(Long restaurantId, Long staffId, StaffDTO staff) {
		Staff staffDetached = staffDao.findById(staffId)
		.orElseThrow(()->new ResourceNotFound("No Staff Found With Id "+staffId));
		staffDetached.setFirstName(staff.getFirstName());
		staffDetached.setLastName(staff.getLastName());
		staffDetached.setRole(StaffRole.valueOf(staff.getRole()));
		staffDetached.setPhoneNumber(staff.getPhoneNumber());
		staffDao.save(staffDetached);
		return "Updated Successfully";
	}

	@Override
	public RestaurantStaffDTO getStaffByRole(Long restaurantId, String role) {
		Restaurant restaurant = restaurantDao.findById(restaurantId)
				.orElseThrow(()->new ResourceNotFound("No Restaurant Found With Id "+restaurantId));
		List<StaffDTO> staffs = restaurant.getStaffs().stream()
			.filter(staff->staff.getRole()==StaffRole.valueOf(role.toUpperCase()))
			.map((staff)->mapper.map(staff, StaffDTO.class)).collect(Collectors.toList());
		RestaurantStaffDTO restroStaffDto=new RestaurantStaffDTO();
		restroStaffDto.setName(restaurant.getName());
		restroStaffDto.setStaff(staffs);
		return restroStaffDto;
	}

}
