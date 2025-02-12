package com.app.dto;

import com.app.entities.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class OrderDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	private Double price;
}
