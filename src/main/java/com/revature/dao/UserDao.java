package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.util.HibernateUtil;

// servlet -> calls service --> calls dao
public class UserDao {
	
	// CRUD methods
	
	// Create (think that in the service layer we'll have a REGISTER()
	public int insert(User u) {
		
		// grab the session object
		Session ses = HibernateUtil.getSession();
		
		// begin a tx
		Transaction tx = ses.beginTransaction();
		
		// capture the pk returned when the session method save() is called
		int pk = (int) ses.save(u);
		
		// return the pk
		return pk;
		
	}
	
	// Read
	public List<User> findAll() {
		
		// grab the session
		Session ses = HibernateUtil.getSession();
		
		// make an HQL -- Hibernate Query Language: odd mix of OOP & native SQL
		 List<User> emps = ses.createQuery("from User", User.class).list();
		
		 // return the list of employees
		return emps;
		
	}
	
	public boolean delete(int id) {
		return false;
		
	}
	
	public boolean update(User u) {
		return false;
	}
	

}