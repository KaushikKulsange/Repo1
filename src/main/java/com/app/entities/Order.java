package com.app.entities;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order extends BaseEntity {
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="ordered_foods")
	private List<OrderedFood> foods = new ArrayList<>();
	
	private LocalTime reservationTime;
	
	@Enumerated(EnumType.STRING)
	@Column(columnDefinition = "varchar(20) default 'PENDING'")
	private OrderStatus orderStatus;
	
	private Long bookedTable;
	
	private Double price=0.0;
	
	@ManyToOne
	private Restaurant restaurant;
	
	
	public void orderFood(OrderedFood orderedFood) {
		foods.add(orderedFood);
		price+=orderedFood.getFood().getPrice()*orderedFood.getQuantity();
	}
	
	public void removeFood(OrderedFood orderedFood) {
		foods.remove(orderedFood);
		price-=orderedFood.getFood().getPrice()*orderedFood.getQuantity();
	}

}
