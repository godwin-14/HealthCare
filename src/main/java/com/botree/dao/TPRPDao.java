package com.botree.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TPRPDao {

	@Autowired
	private SessionFactory sessionFactory;
}
