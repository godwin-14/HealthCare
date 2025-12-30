package com.botree.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.EmerEncounter;
import com.botree.model.Patient;

@Repository
public class ViewTreatmentDao {

	@Autowired
	private SessionFactory sessionFactory;
}
