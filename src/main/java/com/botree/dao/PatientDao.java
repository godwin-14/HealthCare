package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.botree.model.Patient;

@Repository
@Transactional
public class PatientDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void update(Patient patient) {
        sessionFactory.getCurrentSession().update(patient);
    }

}
