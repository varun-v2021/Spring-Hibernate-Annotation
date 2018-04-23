package com.hibernate.model.dto.onetomany.solution2;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
 
@Entity
@Table(name = "ACCOUNT3")
public class AccountEntity3 implements Serializable
{
 
    private static final long serialVersionUID = -6790693372846798580L;
 
    @Id
    @GeneratedValue
    @Column(name = "ACC_ID")
    private Integer accountId;
 
    @Column(name = "ACC_NUMBER", unique = true, nullable = false, length = 100)
    private String accountNumber;
 
    public Integer getAccountId() {
        return accountId;
    }
 
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
 
    public String getAccountNumber() {
        return accountNumber;
    }
 
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
