package com.demo.service.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.dao.EmployeeDao;
import com.demo.service.EmployeeService;
import com.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Transactional
	@Override
	public void save(Employee employee) {
		employeeDao.save(employee); 
		
	}

	@Transactional
	@Override
	public void update(Employee employee) {
		employeeDao.update(employee);
	}

	@Transactional
	@Override
	public void delete(Employee employee) {
		employeeDao.delete(employee);
	}
	@Transactional
	@Override
	public Employee findByEmail(String email) {
		return employeeDao.findByEmail(email);
	}
	@Transactional
	@Override
	public Session getCurrentSession(){
		return employeeDao.getCurrentSession();
	}
}
