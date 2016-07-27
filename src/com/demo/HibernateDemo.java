package com.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.demo.service.EmployeeService;
import com.model.Employee;
import com.model.Job;

public class HibernateDemo {
	
	private static ApplicationContext context;
	
	public static void main(String args[]){
		context = new ClassPathXmlApplicationContext("application-context.xml");
		//SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		//Session session = sessionFactory.openSession();
		//Session session = sessionFactory.getCurrentSession();
		//session.beginTransaction();
		//session.getTransaction().commit();
		//session.close();
		
		//sessionSave();
		//testSave();
		//testUpdate();
		
		//testCache2();
		
		testLevel2Cache();
												
	}
	@Transactional
	public static void sessionSave(){
		SessionFactory sessionFactory = (SessionFactory)context.getBean("sessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Employee em = new Employee();
		em.setLastName("jackson");
		em.setEmail("abc123@163.com");
		em.setHireDate(new Date());
		Job job = new Job();
		job.setJobId("AD_VP");
		em.setJob(job);
		session.save(em);
		session.getTransaction().commit();
		
	}

	
	/**
	 * ��������
	 */
	public static void testLevel2Cache(){
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");
		
		//�����ݿ����
		Session session = employeeService.getCurrentSession();
		session.beginTransaction();
		Employee em = (Employee)session.load(Employee.class, 200L);
		System.out.println("Employee from db >>"+em.getLastName());
		System.out.println("Employee from db >>"+em.getJob().getJobTitle());
		//System.out.println("Employee from db >>"+em.getDepartment().getDepartmentName());
		session.close();

		//�Ӷ����������
		EmployeeService employeeService2 =(EmployeeService) context.getBean("employeeServiceImpl");
		Session session2 = employeeService2.getCurrentSession();
		Employee em2 = (Employee)session2.load(Employee.class, 200L);
		//���ٷ���SQL��������em.getDepartment���򷢳�һ��ȡDepartment���SQL
		System.out.println("Employee from db >>"+em2.getLastName());
		System.out.println("Employee from db >>"+em2.getJob().getJobTitle());
		em2.setEmail("abcdefghijklmn@163.com");
		session2.beginTransaction();
		session2.getTransaction().commit();
		session2.close();

		
	}
	
	/**
	 * �ӻ�������loadȡ����
	 */
	public static void testCache2(){
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");
		Session session = employeeService.getCurrentSession();
		session.beginTransaction();
		Employee em = new Employee();
		em.setLastName("jackson_load");
		em.setEmail("cache123@cache2.com");
		em.setHireDate(new Date());
		Job job = new Job();
		job.setJobId("AD_VP");
		em.setJob(job);
		Long emId = (Long)session.save(em);
		session.getTransaction().commit();
		
		//���´ӻ�����
		System.out.println("Employee geted from level 1 cache >> ");
		Employee emCached = (Employee)session.load(Employee.class, emId);
		System.out.println("Employee lastName >> "+emCached.getLastName());
		
		//���ù����ֶ�ʱ�����ݿ����
		//System.out.println("JobTitile load from database >>> "+emCached.getJob().getJobTitle());
		//System.out.println("Department load from database >>>"+emCached.getDepartment().getDepartmentName());
		

		//��һ����������ݿ���أ������������ֶ���ʱ�Ż�����ݿ�
		Employee em2 = new Employee();
		em2.setEmployeeId(198L);
		em2 = (Employee)session.load(Employee.class, em2.getEmployeeId());
		System.out.println("Employee load from database >> ");		
		System.out.println("Employee lastName >> "+em2.getLastName());
		
		//���ù����ֶ�ʱ�����ݿ����
		System.out.println("JobTitile load from database >>> "+em2.getJob().getJobTitle());
		System.out.println("Department load from database >>>"+em2.getDepartment().getDepartmentName());
		
	}
	
	/**
	 * �ӻ�������getȡ����
	 */
	public static void testCache(){
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");
		Session session = employeeService.getCurrentSession();
		session.beginTransaction();
		Employee em = new Employee();
		em.setLastName("jackson");
		em.setEmail("cache123@cache.com");
		em.setHireDate(new Date());
		Job job = new Job();
		job.setJobId("AD_VP");
		em.setJob(job);
		Long emId = (Long)session.save(em);
		session.getTransaction().commit(); 						//�����ύ��Ȼ���־û�
		
		//���´ӻ�����
		System.out.println("Employee geted from level 1 cache >> ");
		Employee emCached =(Employee) session.get(Employee.class, emId);
		System.out.println("Employee lastName >> "+emCached.getLastName());
		
		
		//��һ����������ݿ���أ���ִ�й�����ѯSQL
		Employee em2 = new Employee();
		em2.setEmployeeId(198L);
		em2 = (Employee)session.get(Employee.class, em2.getEmployeeId());
		System.out.println("Employee get from database >> ");		
		System.out.println("Employee lastName >> "+em2.getLastName());
	}
	
	public static void testUpdate(){
		long id = 211L;
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");		
		Employee em = new Employee();
		em.setLastName("JACKSON");
		em.setEmail("abc@hotmail.com");
		em.setHireDate(new Date());
		Job job = new Job();
		job.setJobId("AD_VP");
		em.setJob(job);
		em.setEmployeeId(id);
		employeeService.update(em);
	}
	
	public static void testSave(){
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");
		Employee em = new Employee();
		em.setLastName("jackson");
		em.setEmail("abc@163.com");
		em.setHireDate(new Date());
		Job job = new Job();
		job.setJobId("AD_VP");
		em.setJob(job);
		employeeService.save(em);
		
	}
	
	public static void testDelete(){
		EmployeeService employeeService =(EmployeeService) context.getBean("employeeServiceImpl");		
		Employee em = new Employee();
		em.setEmployeeId(209);
		employeeService.delete(em);
		
	}

}
