package com.cdac.dao;

import static com.cdac.utils.HibernateUtils.getFactory;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entities.Restaurant;


public class RestaurantDaoImpl implements RestaurantDao {

	@Override
	public String addResturant(Restaurant newRestaurant) {
		String mesg="adding restaurant failed !!!!!!!!!!!";
		// 1. get Session from SF
		Session session=getFactory().getCurrentSession();
		//2. begin a tx
		Transaction tx=session.beginTransaction();
		try {
			session.persist(newRestaurant);
			tx.commit();
			mesg="added new restaurant , ID"+newRestaurant.getId();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			//re throw the same exception to the caller
			throw e;
		}
	
		return mesg;
	}

}
