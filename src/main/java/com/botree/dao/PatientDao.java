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
        var session = sessionFactory.openSession();
        var tran = session.beginTransaction();
        
        session.update(patient);
        
        tran.commit();
        session.close();
    }

    public Patient getPatient(int pid) {
        var session = sessionFactory.openSession();
        var tran = session.beginTransaction();
        
        var patient = session.find(Patient.class, pid);
        
        tran.commit();
        session.close();
        
        return patient;
    }

    @SuppressWarnings("unchecked")
    public List<Patient> searchPatientsByName(String name) {
        var session = sessionFactory.openSession();
        var tran = session.beginTransaction();
        
        var patients = session.createQuery("FROM Patient p WHERE p.firstName LIKE :name OR p.lastName LIKE :name")
                .setParameter("name", "%" + name + "%")
                .list();
        
        tran.commit();
        session.close();
        
        return patients;
    }

    public List<Patient> searchByName(String searchName) {
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
