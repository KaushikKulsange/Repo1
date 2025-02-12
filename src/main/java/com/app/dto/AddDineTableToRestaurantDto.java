package com.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AddDineTableToRestaurantDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NonNull
	@NotBlank
	@Min(0)
	private Long tableNumber;
	
	@NonNull
	@NotBlank
	@Min(1)
	private Long seatingCapacity;
	
	@NonNull
	@NotBlank
	@Size(min = 6)
	private String location;
	
	@JsonProperty(access = Access.READ_ONLY)
	private String status;
	
	@NonNull
	@NotBlank
	@Min(0)
	private Long restaurantId;
}
