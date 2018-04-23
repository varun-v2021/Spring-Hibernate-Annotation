package com.hibernate.annotation.main;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.dto.manytomany.ReaderEntity;
import com.hibernate.dto.manytomany.SubscriptionEntity;
import com.hibernate.model.dto.AccountEntity;
import com.hibernate.model.dto.NewEmployeeEntity;
import com.hibernate.model.dto.onetomany.AccountEntity2;
import com.hibernate.model.dto.onetomany.NewEmployeeEntity2;
import com.hibernate.model.dto.onetomany.solution2.AccountEntity3;
import com.hibernate.model.dto.onetomany.solution2.NewEmployeeEntity3;

public class MainApp {
	private static SessionFactory factory;

	public static void main(String[] args) {

		try {
			factory = new Configuration().configure().addAnnotatedClass(Employee.class)
					.addAnnotatedClass(NewEmployeeEntity.class).addAnnotatedClass(AccountEntity.class)
					.addAnnotatedClass(NewEmployeeEntity2.class).addAnnotatedClass(AccountEntity2.class)
					.addAnnotatedClass(NewEmployeeEntity3.class).addAnnotatedClass(AccountEntity3.class)
					.addAnnotatedClass(ReaderEntity.class).addAnnotatedClass(SubscriptionEntity.class)
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}

		MainApp ME = new MainApp();

		/* Add few employee records in database */
		Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
		Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
		Integer empID3 = ME.addEmployee("John", "Paul", 10000);

		/* List down all the employees */
		ME.listEmployees();

		/* Update employee's records */
		ME.updateEmployee(empID1, 5000);

		/* Delete an employee from the database */
		ME.deleteEmployee(empID2);

		/* List down new list of the employees */
		ME.listEmployees();

		addEntityUsingOneToOneMappingWithoutCascade();

		addEntityUsingOneToManyMappingUsingJoinColumnWithCascade();

		addEntityUsingOneToManyMappingUsingJoinTableWithCascade();
		
		addEntityUsingManyToManyMappingUsingJoinTableWithCascade();
	}

	public static void addEntityUsingOneToManyMappingUsingJoinColumnWithCascade() {
		Session session = factory.openSession();
		session.beginTransaction();

		AccountEntity2 account1 = new AccountEntity2();
		account1.setAccountNumber("Account detail 1");

		AccountEntity2 account2 = new AccountEntity2();
		account2.setAccountNumber("Account detail 2");

		AccountEntity2 account3 = new AccountEntity2();
		account3.setAccountNumber("Account detail 3");

		// Add new Employee object
		NewEmployeeEntity2 firstEmployee = new NewEmployeeEntity2();
		firstEmployee.setEmail("demo-user-first@mail.com");
		firstEmployee.setFirstName("demo-one");
		firstEmployee.setLastName("user-one");

		NewEmployeeEntity2 secondEmployee = new NewEmployeeEntity2();
		secondEmployee.setEmail("demo-user-second@mail.com");
		secondEmployee.setFirstName("demo-two");
		secondEmployee.setLastName("user-two");

		Set<AccountEntity2> accountsOfFirstEmployee = new HashSet<AccountEntity2>();
		accountsOfFirstEmployee.add(account1);
		accountsOfFirstEmployee.add(account2);

		Set<AccountEntity2> accountsOfSecondEmployee = new HashSet<AccountEntity2>();
		accountsOfSecondEmployee.add(account3);

		firstEmployee.setAccounts(accountsOfFirstEmployee);
		secondEmployee.setAccounts(accountsOfSecondEmployee);
		// Save Employee
		session.save(firstEmployee);
		// saving only employee and not account as there is cascading enabled
		// so automatically account table will also be updated
		session.save(secondEmployee);

		session.getTransaction().commit();
		session.close();
	}

	public static void addEntityUsingOneToManyMappingUsingJoinTableWithCascade() {
		Session session = factory.openSession();
		session.beginTransaction();

		AccountEntity3 account1 = new AccountEntity3();
		account1.setAccountNumber("Account detail 1");

		AccountEntity3 account2 = new AccountEntity3();
		account2.setAccountNumber("Account detail 2");

		AccountEntity3 account3 = new AccountEntity3();
		account3.setAccountNumber("Account detail 3");

		// Add new Employee object
		NewEmployeeEntity3 firstEmployee = new NewEmployeeEntity3();
		firstEmployee.setEmail("demo-user-first@mail.com");
		firstEmployee.setFirstName("demo-one");
		firstEmployee.setLastName("user-one");

		NewEmployeeEntity3 secondEmployee = new NewEmployeeEntity3();
		secondEmployee.setEmail("demo-user-second@mail.com");
		secondEmployee.setFirstName("demo-two");
		secondEmployee.setLastName("user-two");

		Set<AccountEntity3> accountsOfFirstEmployee = new HashSet<AccountEntity3>();
		accountsOfFirstEmployee.add(account1);
		accountsOfFirstEmployee.add(account2);

		Set<AccountEntity3> accountsOfSecondEmployee = new HashSet<AccountEntity3>();
		accountsOfSecondEmployee.add(account3);

		firstEmployee.setAccounts(accountsOfFirstEmployee);
		secondEmployee.setAccounts(accountsOfSecondEmployee);
		// Save Employee
		session.save(firstEmployee);
		// saving only employee and not account as there is cascading enabled
		// so automatically account table will also be updated
		session.save(secondEmployee);

		session.getTransaction().commit();
		session.close();
	}

	public static void addEntityUsingManyToManyMappingUsingJoinTableWithCascade() {
		Session session = factory.openSession();
		session.beginTransaction();

		// Add subscription
		SubscriptionEntity subOne = new SubscriptionEntity();
		subOne.setSubscriptionName("Entertainment");

		SubscriptionEntity subTwo = new SubscriptionEntity();
		subTwo.setSubscriptionName("Horror");

		Set<SubscriptionEntity> subs = new HashSet<SubscriptionEntity>();
		subs.add(subOne);
		subs.add(subTwo);

		// Add readers
		ReaderEntity readerOne = new ReaderEntity();
		readerOne.setEmail("demo-user1@mail.com");
		readerOne.setFirstName("demo");
		readerOne.setLastName("user");

		ReaderEntity readerTwo = new ReaderEntity();
		readerTwo.setEmail("demo-user2@mail.com");
		readerTwo.setFirstName("demo");
		readerTwo.setLastName("user");

		Set<ReaderEntity> readers = new HashSet<ReaderEntity>();
		readers.add(readerOne);
		readers.add(readerTwo);

		readerOne.setSubscriptions(subs);
		readerTwo.setSubscriptions(subs);

		session.save(readerOne);
		session.save(readerTwo);

		session.getTransaction().commit();
		session.close();
	}

	public static void addEntityUsingOneToOneMappingWithoutCascade() {
		Session session = factory.openSession();
		Transaction tx = null;
		tx = session.beginTransaction();

		try {
			// owned entity
			AccountEntity account = new AccountEntity();
			account.setAccNumber("123-345-65454");

			// owner entity
			// Add new Employee object
			NewEmployeeEntity emp = new NewEmployeeEntity();
			emp.setEmail("demo-user@mail.com");
			emp.setFirstName("demo");
			emp.setLastName("user");

			// Save Account
			session.saveOrUpdate(account);
			// Save Employee
			emp.setAccount(account);
			session.saveOrUpdate(emp);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to CREATE an employee in the database */
	public Integer addEmployee(String fname, String lname, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeID = null;

		try {
			tx = session.beginTransaction();
			Employee employee = new Employee();
			employee.setFirstName(fname);
			employee.setLastName(lname);
			employee.setSalary(salary);
			employeeID = (Integer) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeID;
	}

	/* Method to READ all the employees */
	public void listEmployees() {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			List employees = session.createQuery("FROM Employee").list();
			for (Iterator iterator = employees.iterator(); iterator.hasNext();) {
				Employee employee = (Employee) iterator.next();
				System.out.print("First Name: " + employee.getFirstName());
				System.out.print("  Last Name: " + employee.getLastName());
				System.out.println("  Salary: " + employee.getSalary());
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to UPDATE salary for an employee */
	public void updateEmployee(Integer EmployeeID, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, 30); // Need
																			// to
																			// check
																			// whether
																			// Id
																			// exists
																			// in
																			// Table
			employee.setSalary(salary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	/* Method to DELETE an employee from the records */
	public void deleteEmployee(Integer EmployeeID) {
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Employee employee = (Employee) session.get(Employee.class, EmployeeID);
			session.delete(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}