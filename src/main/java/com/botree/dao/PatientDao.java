package com.botree.dao;

import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.botree.model.Patient;

@Repository
public class PatientDao {
	
	@Autowired
	 SessionFactory sessionFactory;
	
	  
	  public boolean register(Patient patient) {
			boolean flag=false;
			var session=sessionFactory.openSession();
			var tran=session.beginTransaction();
			
			try {
				session.persist(patient);
				flag=true;
				tran.commit();
				
			}catch(Exception e)
			{
				tran.rollback();
				e.printStackTrace();
				flag=false;
			}
			
			session.close();
			
			return flag;
		}
	  
	
		
}

