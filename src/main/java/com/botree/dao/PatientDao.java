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


	  public List<Patient> searchByName(String searchName)
	  {
		    if (searchName == null || searchName.trim().isEmpty()) {
		        return Collections.emptyList();
		    }

		    String searchParam = "%" + searchName.trim().toLowerCase() + "%";

		    try (var session = sessionFactory.openSession()) {
		        var query = session.createQuery(
		            "from Patient p where lower(trim(p.firstName)) like :name or lower(trim(p.lastName)) like :name",
		            Patient.class
		        );
		        query.setParameter("name", searchParam);
		        return query.getResultList();
		    }
		}
}
