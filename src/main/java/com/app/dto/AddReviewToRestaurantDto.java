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
public class AddReviewToRestaurantDto {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NonNull
	@NotBlank
	@Min(0)
	private Long userId;
	
	@NonNull
	@NotBlank
	@Min(0)
	private Long restaurantId;
	
	@NonNull
	@NotBlank
	@Size(max = 50)
	private String comment;
}
