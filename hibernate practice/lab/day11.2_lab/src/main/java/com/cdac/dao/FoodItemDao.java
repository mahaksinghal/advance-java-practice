package com.cdac.dao;

import com.cdac.entities.FoodItem;

public interface FoodItemDao {
	String addFoodItemToResturant(Long restaurantId, FoodItem foodItem);;
}
