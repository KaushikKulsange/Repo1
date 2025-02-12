package com.app.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderFoodResponseDTO {
	private Long id;
	private String restaurantName;
	private Long tableNumber;
	private String tableLocation;
	private String restaurantAddress;
	private LocalDateTime reservationTime;
	private LocalDateTime bookedTime;
	private List<OrderedFoodNameQuantityDTO> foods;
	
}
