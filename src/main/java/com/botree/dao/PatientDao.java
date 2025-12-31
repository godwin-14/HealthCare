package com.botree.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Patient;

@Repository
public class PatientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void update(Patient patient) {
        sessionFactory.getCurrentSession().update(patient);
    }

}
