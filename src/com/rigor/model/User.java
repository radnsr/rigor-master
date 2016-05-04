package com.rigor.model;

public class User {
	private String user_id;
	private String name;
	private int emp_no;
	private String email;
	private String password;
	private int status;
	private String role;
	private String dept_id;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User(String user_id, String name, int emp_no, String email, String password, int status, String role) {
		this.user_id = user_id;
		this.name = name;
		this.emp_no = emp_no;
		this.email = email;
		this.password = password;
		this.status = status;
		this.setRole(role);
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", Name=" + name + ", emp_no=" + emp_no + ", Email=" + email + ",password="
				+ password + "]";
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

}
