package com.botree.dao;

import java.util.Collections;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Patient;

import com.botree.model.Patient;

@Repository
public class PatientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void update(Patient patient) {
        sessionFactory.getCurrentSession().update(patient);
    }


	@Autowired
	private SessionFactory sessionFactory1;
	
	

	  public List<Patient> searchByName(String searchName) {
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
