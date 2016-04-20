package com.rigor.model;

public class Category {
	
private String category_id;
private String dept_id;
private String description; 
private int status;


public Category(String category_id,String dept_id,String description,int status){
	
	this.category_id=category_id;
	this.dept_id=dept_id;
	this.description=description;
	this.status=status;
}
public Category() {
	// TODO Auto-generated constructor stub
}
public String getCategory_id() {
	return category_id;
}
public void setCategory_id(String category_id) {
	this.category_id = category_id;
}
public String getDept_id() {
	return dept_id;
}
public void setDept_id(String dept_id) {
	this.dept_id = dept_id;
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
}
