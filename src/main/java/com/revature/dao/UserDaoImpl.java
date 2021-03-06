package com.revature.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.Transaction;

//import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.HibernateUtil;


public class UserDaoImpl implements IUserDao {
	
	// CRUD methods
	
	// Create
	@Override
	public int insert(User u) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// capture the pk returned when the session method save() is called
		int pk = (int) ses.save(u);
		
		tx.commit();
		
		// return the pk
		return pk;
		
	}
	
	public User findByUsername(String username) {
		
		Session ses = HibernateUtil.getSession();
		
		Optional<User> possibleEmp = ses.createQuery("from User", User.class).stream()
				.filter(u -> (u.getUsername().equals(username) && u.getUsername().equals(username)))
				.findFirst();
		
		if (possibleEmp.isPresent()) {
			
			return possibleEmp.get();
			
		}

		return new User();
	}
	
	// Read
	@Override
	public List<User> findAll() {
		
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL -- Hibernate Query Language: odd mix of OOP & native SQL
		 List<User> emps = ses.createQuery("from User", User.class).list();
		
		 // return the list of employees
		 
		return emps;
		
	}
	
	@Override
	public List<User> findAllEmps() {
		
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL -- Hibernate Query Language: odd mix of OOP & native SQL
		 List<User> emps = ses.createQuery("from User WHERE userType = 1 ORDER BY lastName ASC", User.class).list();
		
		 // return the list of employees
		 
		return emps;
		
	}
	
	public boolean deleteUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.delete(u);
		tx.commit();
		return true;
	}
	public boolean updateUser(User u) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(u);
		tx.commit();
		return true;
	}
}