package com.cdac.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entities.FoodItem;
import com.cdac.entities.Restaurant;
import static com.cdac.utils.HibernateUtils.getFactory;

public class FoodItemDaoImpl implements FoodItemDao {

	@Override
	public String addFoodItemToResturant(Long restaurantId, 
			FoodItem foodItem) {
		String mesg="failed !!!!!!!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			//get restaurant details from its id
			Restaurant restaurant=session.get(Restaurant.class, restaurantId);
			if(restaurant != null)
			{
				//=> valid restaurant
				restaurant.getFoodItems().add(foodItem);
				foodItem.setMyRestaurant(restaurant);
				session.persist(foodItem);
				mesg="added ";
			}
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;
	}

}
