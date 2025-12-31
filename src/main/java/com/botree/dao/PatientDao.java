package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.Patient;

@Repository
public class PatientDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public List<Patient> searchById(int pid)
	{
	    var session = sessionFactory.openSession();
	    var trans =  session.beginTransaction();
	    var query = session.createQuery("from Patient p where p.pid = :pid", Patient.class);
	    query.setParameter("pid", pid);
	    var list = query.getResultList();
	    session.close();
	    trans.commit();
	    return list;
	}
}
