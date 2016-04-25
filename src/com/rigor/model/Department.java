package com.rigor.model;


public class Department {

	private String dept_id;
	private String dept_name;
	private int status;
	

	public Department() {
		//dept_id = "D0001";
	}

	

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Department(String dept_id, String dept_name, int status) {
		this.dept_id = dept_id;
		this.dept_name = dept_name;
		this.status = status;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	//	result = prime * result + (int) (status ^ (status >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Department))
			return false;
		Department other = (Department) obj;
		if (dept_id != other.dept_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Department [dept_id=" + dept_id + ", dept_name=" + dept_name + ", status=" + status + "]";
	}

}