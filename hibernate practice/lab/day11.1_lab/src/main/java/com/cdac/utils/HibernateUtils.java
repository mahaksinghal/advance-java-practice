package com.cdac.utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory factory;
	static {
		factory = new Configuration()
				.configure() //entire hib frmwork is loaded -> DBCP is created
				.buildSessionFactory();
	}

	// getter
	public static SessionFactory getFactory() {
		return factory;
	}

}
