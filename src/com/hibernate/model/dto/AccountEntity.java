package com.hibernate.model.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "acc_number")
	private String accNumber;

	// mappedBy keyword specifies that this entity is not responsible for the
	// relationship
	/*
	 * In a bidirectional relationship, one of the sides (and only one) has to
	 * be the owner: the owner is responsible for the association column(s)
	 * update. To declare a side as not responsible for the relationship, the
	 * attribute mappedBy is used. mappedBy refers to the property name of the
	 * association on the owner side. Here NeEmployeeEntity is the owner.
	 */
	@OneToOne(mappedBy = "account") // property name in owner class
	private NewEmployeeEntity newEmployee;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccNumber() {
		return accNumber;
	}

	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}

	public NewEmployeeEntity getNewEmployeeEntity() {
		return newEmployee;
	}

	public void setNewEmployeeEntity(NewEmployeeEntity newEmployeeEntity) {
		this.newEmployee = newEmployeeEntity;
	}

}
