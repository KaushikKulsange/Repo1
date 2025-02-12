package com.app.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Restaurant extends BaseEntity {
	@Column(length = 25,unique = true)
	private String email;
	
	@Column(length = 25)
	private String password;
	
	@Column(length = 25)
	private String name;
	
	@Column(length = 50)
	private String address;
	
	private LocalTime openingTime;

	private LocalTime closingTime;
	
	@Column(length = 14,unique = true)
	private String fassaiId;

	@Enumerated(EnumType.STRING)
	private OpenStatus status;
	
	@Column(length = 10)
	private String contactNo;
	
	@Enumerated(EnumType.STRING)
	private RestaurantType foodType;
	
//	@OneToMany(cascade = CascadeType.ALL)
//	private List<Food> foods=new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<DineTable> dineTables = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL)
	private List<Staff> staffs = new ArrayList<>();
	
	@OneToMany
	private List<Order> orders=new ArrayList<>();
	
	public void addStaff(Staff staff) {
		staffs.add(staff);
	}
	
	public void removeStaff(Staff staff) {
		staffs.remove(staff);
	}
	public void addTables(Restaurant restaurant,DineTable dineTable) {
		dineTable.setRestaurants(restaurant);
		dineTables.add(dineTable);
	}

	public void deleteTables(Restaurant restaurant,DineTable dineTable) {
		dineTable.setRestaurants(null);
		dineTables.remove(dineTable);
	}
}
