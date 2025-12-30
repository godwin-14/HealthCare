package com.botree.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.User;

@Repository
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public User getUser(String name)
	{
		var session=sessionFactory.openSession();
		var tran=session.beginTransaction();
		
		var u=session.find(User.class, name);
		
		tran.commit();
		session.close();
		
		return u;
	}

	

}
