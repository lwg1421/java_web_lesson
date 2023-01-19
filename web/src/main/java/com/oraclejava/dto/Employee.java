package com.oraclejava.dto;

import java.util.Date;

// dto는 이름을 잘 짓는것이 가장 중요!
// dto의 클래스는 public이어야하고 클래스명(DB의 테이블명)은 첫글자가 대문자로 시작하도록 한다.

// 클래스를 만들었으면 employee 테이블의 컬럼명을 다 가져와서 변수로 지정해줘야한다(소문자로)
// 변수명(DB의 테이블의 컬럼명)은 private로 지정하고 자료형은 DB의 컬럼의 자료형과 동일하게 만들어준다.
// 컬럼명을 변수로 지정을 다 해줬으면 getter/setter를 전부 만들어준다.
public class Employee {
	private int employee_id;
	private String first_name;
	private String last_name;
	private String email;
	private String phone_number;
	private Date hire_date;
	private String job_id;
	private int salary;
	private double commission_pct;
	private int manager_id;
	private int departmant_id;
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Date getHire_date() {
		return hire_date;
	}
	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public double getCommission_pct() {
		return commission_pct;
	}
	public void setCommission_pct(double commission_pct) {
		this.commission_pct = commission_pct;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public int getDepartmant_id() {
		return departmant_id;
	}
	public void setDepartmant_id(int departmant_id) {
		this.departmant_id = departmant_id;
	}
	
}
