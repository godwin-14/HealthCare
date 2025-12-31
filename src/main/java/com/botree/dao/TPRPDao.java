package com.botree.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.botree.model.TPRB;

@Repository
public class TPRPDao {

	@Autowired
	private SessionFactory sessionFactory;

	public boolean save(TPRB tprb) {
		return false;
	}
}
