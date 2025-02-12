package com.app.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User extends BaseEntity{
	@Column(length = 20)
    private String email;
	@Column(length = 20)
	private String firstName;
	@Column(length = 20)
	private String lastName;
	@Column(length = 10)
	private String phoneNumber;
	@Column(length = 20)
	private String password;
	
	@Column(length=50)
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList<>();
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void cancelOrder(Order order) {
		orders.remove(order);
	}
}
