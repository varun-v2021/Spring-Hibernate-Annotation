package com.hibernate.model.dto.onetomany.solution2;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/*
 * This approach uses a join table to store the associations between account and employee entities.
 *  @JoinTable annotation has been used to make this association.
 *  */

/*
 * Another approach is to have a common join table lets say EMPLOYEE_ACCOUNT. 
 * This table will have two column 
 * i.e. EMP_ID which will be foreign key referring to primary key in EMPLOYEE table 
 * and similarly ACCOUNT_ID which will be foreign key referring to primary key of ACCOUNT table.
 * 
 * */
@Entity
@Table(name = "NewEmployee3")
public class NewEmployeeEntity3 implements Serializable {

	private static final long serialVersionUID = -1798070786993154676L;

	@Id
	@GeneratedValue
	@Column(name = "EMP_ID", unique = true, nullable = false)
	private Integer employeeId;

	@Column(name = "EMAIL", unique = true, nullable = false, length = 100)
	private String email;

	@Column(name = "FIRST_NAME", unique = false, nullable = false, length = 100)
	private String firstName;

	@Column(name = "LAST_NAME", unique = false, nullable = false, length = 100)
	private String lastName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EMPLOYEE_ACCOUNT", joinColumns = {
			@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMP_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "ACC_ID") })
	private Set<AccountEntity3> accounts;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<AccountEntity3> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<AccountEntity3> accounts) {
		this.accounts = accounts;
	}
}