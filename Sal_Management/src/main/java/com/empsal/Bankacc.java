package com.empsal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bankacc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    String bankaccno;
    @Column
    String accounttype;
    @Column
    String accountname;
    @Column
    double currentbalance;
    @Column
    String brunchname;
	public Bankacc() {
		super();
	}
	public Bankacc(Long id, String bankaccno, String accounttype, String accountname, double currentbalance,
			String brunchname) {
		super();
		this.id = id;
		this.bankaccno = bankaccno;
		this.accounttype = accounttype;
		this.accountname = accountname;
		this.currentbalance = currentbalance;
		this.brunchname = brunchname;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBankaccno() {
		return bankaccno;
	}
	public void setBankaccno(String bankaccno) {
		this.bankaccno = bankaccno;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public double getCurrentbalance() {
		return currentbalance;
	}
	public void setCurrentbalance(double currentbalance) {
		this.currentbalance = currentbalance;
	}
	public String getBrunchname() {
		return brunchname;
	}
	public void setBrunchname(String brunchname) {
		this.brunchname = brunchname;
	}
	@Override
	public String toString() {
		return "Bankacc [id=" + id + ", bankaccno=" + bankaccno + ", accounttype=" + accounttype + ", accountname="
				+ accountname + ", currentbalance=" + currentbalance + ", brunchname=" + brunchname + "]";
	}
}
