package com.app.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="orderedfood")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderedFood extends BaseEntity{
	private Long quantity;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(nullable = true)
	private Food food;
	
	public OrderedFood(Long quantity) {
		this.quantity=quantity;
	}

}
