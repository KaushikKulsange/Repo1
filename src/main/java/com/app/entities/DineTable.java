package com.app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dinetables")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class DineTable extends BaseEntity {
	private Long tableNumber;

	private Long seatingCapacity;

	@Enumerated(EnumType.STRING)
	private TableStatus status;

	@Enumerated(EnumType.STRING)
	private DineLocation location;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurants;
}
