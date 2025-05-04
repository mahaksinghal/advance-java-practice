package com.cdac.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="restaurants")
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true,exclude = "foodItems")
public class Restaurant extends BaseEntity{
	@Column(length = 50,unique = true)
	private String name;
	private String address;
	@Column(length = 30)
	private String city;
	private String description;
	//always init the collection - to avoid NullPointerException
	//Restaurant 1---->* FoodItem
	//Restaurant - one , parent table , inverse side
	@OneToMany(mappedBy = "myRestaurant")
	private List<FoodItem>foodItems=new ArrayList<>();
	public Restaurant(String name, String address, String city, String description) {
		super();
		this.name = name;
		this.address = address;
		this.city = city;
		this.description = description;
	}
}
