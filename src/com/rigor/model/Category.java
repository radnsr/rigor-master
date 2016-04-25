package com.rigor.model;


public class Category {
	
private String category_id;
private Department department;
private String description; 
private int status;


public Category(String category_id,String dept_id,String description,int status){
	
	this.category_id=category_id;
	this.description=description;
	this.status=status;
}
public Category() {
	// TODO Auto-generated constructor stub
}


public Department getDepartment() {
	return department;
}
public void setDepartment(Department department) {
	this.department = department;
}
public String getCategory_id() {
	return category_id;
}
public void setCategory_id(String category_id) {
	this.category_id = category_id;
}

public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String toString() {
	return "Category [cat_id=" + category_id + ", description=" + description + ", status=" + status + "]";
}
}