package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.EmerEncounter;

@Repository
public class EncounterDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean saveCounter(EmerEncounter encounter) {
		boolean flag = false;
		var session = sessionFactory.openSession();
		var tran = session.beginTransaction();

		try {
			session.persist(encounter);
			flag = true;
			tran.commit();

		} catch (Exception e) {
			tran.rollback();
			e.printStackTrace();
			flag = false;
		}

		session.close();

		return flag;

	}

	public void save(EmerEncounter encounter) {
		var session = sessionFactory.openSession();
		var tran = session.beginTransaction();
		session.persist(encounter);
		tran.commit();
		session.close();
	}

	public List<EmerEncounter> getEncountersByPatientId(int pid) {
		var session = sessionFactory.openSession();
		var tran = session.beginTransaction();

		var query = session.createQuery("from EmerEncounter e where e.patient.pid = :pid order by e.encounterDate desc",
				EmerEncounter.class);
		query.setParameter("pid", pid);

		List<EmerEncounter> list = query.getResultList();

		tran.commit();
		session.close();

		return list;
	}

}
