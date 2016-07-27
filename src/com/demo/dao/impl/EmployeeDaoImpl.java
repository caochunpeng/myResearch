package com.demo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.dao.EmployeeDao;
import com.model.Employee;

@Repository
public class EmployeeDaoImpl  implements EmployeeDao{
	
	@Autowired
	 private SessionFactory sessionFactory;
	
	@Override
	public Session getCurrentSession(){
		/*
		Session session =sessionFactory.getCurrentSession();
		if (session.isOpen()==false)
			return sessionFactory.openSession();
		else 
			return session;
		*/
		return sessionFactory.openSession();
	}
	
	@Override
	public void saveOrUpdate(Employee employee) {
		sessionFactory.getCurrentSession().saveOrUpdate(employee);
	}
	
	@Override
	public void save(Employee employee) {
		sessionFactory.getCurrentSession().save(employee);
	}

	@Override
	public void update(Employee employee) {
		sessionFactory.getCurrentSession().update(employee);
	}

	@Override
	public void delete(Employee employee) {
		sessionFactory.getCurrentSession().delete(employee);
	}
	
	@Override
	public Employee findByEmail(String email) {
		   return (Employee) sessionFactory.
				      getCurrentSession().
				      get(Employee.class, email);
	}
	




}
