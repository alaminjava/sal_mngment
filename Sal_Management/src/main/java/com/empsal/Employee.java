package com.empsal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String empname;
	@Column
	private String emprank;
	@Column
	private String empaddress;
	@Column
	private String empmobile;
	@Column
	private String empbankaccno;
	@Column
	private double empsalary;
	public Employee() {
		super();
	}
	public Employee(Long id, String empname, String emprank, String empaddress, String empmobile, String empbankaccno,
			double empsalary) {
		super();
		this.id = id;
		this.empname = empname;
		this.emprank = emprank;
		this.empaddress = empaddress;
		this.empmobile = empmobile;
		this.empbankaccno = empbankaccno;
		this.empsalary = empsalary;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getEmprank() {
		return emprank;
	}
	public void setEmprank(String emprank) {
		this.emprank = emprank;
	}
	public String getEmpaddress() {
		return empaddress;
	}
	public void setEmpaddress(String empaddress) {
		this.empaddress = empaddress;
	}
	public String getEmpmobile() {
		return empmobile;
	}
	public void setEmpmobile(String empmobile) {
		this.empmobile = empmobile;
	}
	public String getEmpbankaccno() {
		return empbankaccno;
	}
	public void setEmpbankaccno(String empbankaccno) {
		this.empbankaccno = empbankaccno;
	}
	public double getEmpsalary() {
		return empsalary;
	}
	public void setEmpsalary(double empsalary) {
		this.empsalary = empsalary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", empname=" + empname + ", emprank=" + emprank + ", empaddress=" + empaddress
				+ ", empmobile=" + empmobile + ", empbankaccno=" + empbankaccno + ", empsalary=" + empsalary + "]";
	}
	
	
}
