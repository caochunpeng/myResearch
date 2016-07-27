package com.demo.dao;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;

import com.model.Employee;

public interface EmployeeDao{

	void save(Employee employee);
	void update(Employee employee);
	void delete(Employee employee);
	
	//@Query("SELECT e FROM  Employee  where email= ? ")
	Employee findByEmail(String email);
	
	void saveOrUpdate(Employee employee);
	Session getCurrentSession();
}