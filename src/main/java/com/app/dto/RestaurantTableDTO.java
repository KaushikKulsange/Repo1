package com.app.dto;

import java.util.List;

import com.app.entities.DineTable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantTableDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	private String name;
	private List<DineTable> dineTables;
}
