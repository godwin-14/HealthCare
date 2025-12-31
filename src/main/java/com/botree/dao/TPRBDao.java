package com.botree.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.TPRB;

@Repository
public class TPRBDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(TPRB tprb) {
		boolean flag=false;
		
	 var session = sessionFactory.openSession();
     var tran = session.beginTransaction();
     try
     {
     session.persist(tprb);
     tran.commit();
     return true;
     }catch(Exception e)
     {
     session.close();
     e.printStackTrace();
     return false;
     }
	 
	}
}
