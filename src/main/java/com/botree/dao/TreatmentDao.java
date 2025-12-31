package com.botree.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.Medicine;
import com.botree.model.Patient;
import com.botree.model.TPRB;
import com.botree.model.Treatment;

@Repository
public class TreatmentDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Patient getPatientById(int pid) {
		var session = sessionFactory.openSession();
		var  tran = session.beginTransaction();

		Patient patient = session.get(Patient.class, pid);

		tran.commit();
		session.close();

		return patient;

	}

	public TPRB getLatestTPRB(int pid) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		var query = session.createQuery("from TPRB t where t.patient.pid = :pid order by t.createdDate desc", TPRB.class);
		query.setParameter("pid", pid);
		query.setMaxResults(1);

		List<TPRB> list = query.getResultList();

		transaction.commit();
		session.close();

		return list.isEmpty() ? null : list.get(0);
	}

	public List<TPRB> getPreviousTPRB(int pid) {
		Session session = sessionFactory.openSession();
		Transaction tran = session.beginTransaction();

		var query = session.createQuery("from TPRB t where t.patient.pid = :pid order by t.encounterDate desc",
				TPRB.class);

		query.setParameter("pid", pid);

		List<TPRB> list = query.getResultList();

		tran.commit();
		session.close();

		return list;

	}

	public List<Medicine> getAllMedicines() {
		var session = sessionFactory.openSession();
		var tran = session.beginTransaction();

		var query = session.createQuery("from Medicine", Medicine.class);

		List<Medicine> list = query.getResultList();

		tran.commit();
		session.close();

		return list;

	}

	public boolean saveTreatment(Treatment treatment) {
		try (var session = sessionFactory.openSession()) {
			var tran = session.beginTransaction();
			session.persist(treatment);
			tran.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
