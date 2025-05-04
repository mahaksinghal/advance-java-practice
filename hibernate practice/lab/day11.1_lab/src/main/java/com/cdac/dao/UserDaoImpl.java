package com.cdac.dao;

import static com.cdac.utils.HibernateUtils.getFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.cdac.entities.User;
import com.cdac.entities.UserRole;

public class UserDaoImpl implements UserDao {

	@Override
	public String signUp(User user) {
		// user - TRANSIENT (exists only in heap)
		String mesg = "Sign Up failed !!!!";
		// 1. Get Hibernate Session from SessionFactory
		/*
		 * SessionFactory methods public Session openSession() throws HibernateException
		 * OR public Session getCurrentSession() throws HibernateException - SF returns
		 * NEW session object , if none exists - otherwise rets EXISTING session
		 */
		Session session = getFactory().getCurrentSession();
		Session session2 = getFactory().getCurrentSession();
		Session session3 = getFactory().getCurrentSession();
		System.out.println(session == session2);// t
		System.out.println(session == session3);// t
		// 2. Begin a Tx
		Transaction tx = session.beginTransaction();
		System.out.println(session.isConnected() + " " + session.isOpen());// t t
		try {
			/*
			 * Session API public void persist(Object transientEntity) throws
			 * HibernateException - It makes transient -> persistent transition n rec will
			 * be inserted in DB upon commit
			 */
			session.persist(user);// user - PERSISTENT - part of L1 cache
			tx.commit();// insert query
			System.out.println(session.isConnected() + " " + session.isOpen());// f f
			mesg = "User registered with ID - " + user.getUserId();
			/*
			 * What happens upon commit ? 1. session.flush() -> 2. triggers automatic dirty
			 * checking Hibernate checks the state of L1 cache with DB In case of any
			 * changes - DML In case of new persistent entity -> insert updated entity ->
			 * update upon session.delete -> delete 3. session.close() -> L1 cache is
			 * destroyed pooled out db cn , simply rets to the pool.
			 * 
			 */
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			/*
			 * What happens upon rollback ?
			 * 
			 * 1. session.close() -> L1 cache is destroyed pooled out db cn , simply rets to
			 * the pool.
			 * 
			 */
			// re throw the same exc to the caller
			throw e;
		}
		// user - DETACHED (from L1 cache)
		return mesg;
	}

	/*
	 * Play with the code - to understand Entity state transitions , PERSISTENT vs
	 * DETACHED n session API n caching with session.get
	 */
	@Override
	public User getUserDetailsById(Long userId) {
		User user = null;
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			user = session.get(User.class, userId);// select
			if (user != null) {
				// => user : PERSISTENT - part of L1 cache , in db
				// user.setPassword("abc12455");//modifying state of the
				// PERSISTENT entity
				session.evict(user);// user : DETACHED
			}
//			user=session.get(User.class, userId);//cache
//			user=session.get(User.class, userId);
//			user=session.get(User.class, userId);//cache
			// in case of valid id - user : PERSISTENT (exists in L1 cache n DB)
			// invalid id - null
			tx.commit();// updated state entity - DML - update
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}
		// user.setPassword("def123456");//user - DETACHED - hibernate CAN'T track the
		// changes made to detached entity !
		return user;// user - null or DETACHED
	}

	@Override
	public List<User> getAllUsersDetails() {
		List<User> users = null;
		String jpql = "select u from User u";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// create query object from Session n execute the query
			users = session.createQuery(jpql, User.class).getResultList();
			// users - List of PERSISTENT entities
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return users;// users - List of DETACHED entities
	}

	@Override
	public List<User> getUserDetailsByRoleAndDob(UserRole role1, LocalDate date1) {
		List<User> users = null;
		String jpql = "select u from User u where u.role=:rl and u.dob > :date";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("rl", role1).setParameter("date", date1)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return users;
	}

	@Override
	public List<User> getUserDetailsByRole(UserRole role1) {
		List<User> users = null;
		String jpql = "select new com.cdac.entities.User(u.firstName,u.lastName,u.dob) from User u where u.role=:role";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			users = session.createQuery(jpql, User.class).setParameter("role", role1).getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return users;
	}

	@Override
	public String changePassword(String email, String pwd, String newPwd) {
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		String mesg = "pwd updation failed!!!!!!!!!!!!";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			User user = session.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", pwd)
					.getSingleResult();// select
			// => valid credentials , user : persistent
			user.setPassword(newPwd);// modifying state of the persistent entity
			tx.commit();// session.flush -> dirty chking -> update -> session.close
			mesg = "pwd updated !";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;
	}

	@Override
	public String deleteUserDetailsById(Long userId) {
		String mesg = "user un subscription failed !!!!!!";
		// 1. get Session from SF
		User user = null;
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// session - get
			user = session.get(User.class, userId);
			if (user != null) {
				// user - PERSISTENT
				session.remove(user);
				mesg = "user details deleted....";
				// user - REMOVED
			}
			tx.commit();// DML - delete , close - L1 cache
			// user - TRANSIENT
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;
	}// user : marked for GC - Does NOT exist !

	@Override
	public String saveImage(Long userId, String imageFileName) throws IOException {
		String mesg="saving img failed ....";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			// 1. get user details -
			User user = session.get(User.class, userId);
			if (user != null) {
				// user : PERSISTENT
				// validate file
				File file = new File(imageFileName);
				if (file.isFile() && file.canRead()) {
					byte[] imgContents =
							FileUtils.readFileToByteArray(file);
					user.setImage(imgContents);
					mesg="saved img contents !";
				}
			}
			tx.commit();//DML - update, close
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;
	}
	
	@Override
	public String restoreImage(String email1, String imageFileName) throws IOException {
		String mesg="restoring img failed ....";
		String jpql="select u.image from User u where u.email=:em";
		// 1. get Session from SF
		Session session = getFactory().getCurrentSession();
		// 2. begin a tx
		Transaction tx = session.beginTransaction();
		try {
			byte[] imgContents=session.createQuery(jpql,byte[].class)
					.setParameter("em", email1)
					.getSingleResult();
			//=> record is found !
			//store img contents in bin file
			FileUtils.writeByteArrayToFile
			(new File(imageFileName), imgContents);			
			tx.commit();
			mesg="image restored";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			// re throw the same exception to the caller
			throw e;
		}

		return mesg;
	}


	@Override
	public String deleteUsers(LocalDate dob) {
		String mesg="Deletion failed !";
		String jpql="delete from User u where u.dob < :date";
		// 1. get Session from SF
		Session session=getFactory().getCurrentSession();
		//2. begin a tx
		Transaction tx=session.beginTransaction();
		try {
			int deleteCount=session.createMutationQuery(jpql)
					.setParameter("date",dob)
					.executeUpdate();
			mesg="Deleted "+deleteCount+" no of users";
			tx.commit();
		} catch (RuntimeException e) {
			if(tx != null)
				tx.rollback();
			//re throw the same exception to the caller
			throw e;
		}
	
		return mesg;
	}
	

}
