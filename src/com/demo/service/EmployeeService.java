package com.demo.service;

import org.hibernate.Session;

import com.model.Employee;

public interface EmployeeService {
	void save(Employee employee);
	void update(Employee employee);
	void delete(Employee employee);
	Employee findByEmail(String email);
	Session getCurrentSession();	
}
