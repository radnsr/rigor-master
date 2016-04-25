package com.rigor.model;

import java.sql.Date;

public class Feedback {
	
private String feedback_id;
private Date date;
private String feedback_type;
private String user_id;
private Department department;
private Category category;
private String description;
private int status;

public Feedback(String feedback_id,Date date,String feedback_type,String description,int status){

	this.feedback_id=feedback_id;
	this.date=date;
	this.feedback_type=feedback_type;
	this.description=description;
	this.status=status;
	
}

public Feedback() {
	// TODO Auto-generated constructor stub
}

public String getFeedback_id() {
	return feedback_id;
}
public void setFeedback_id(String feedback_id) {
	this.feedback_id = feedback_id;
}
public Date getDate() {
	return date;
}
public void setDate(Date dt) {
	this.date = dt;
}
public String getFeedback_type() {
	return feedback_type;
}
public void setFeedback_type(String feedback_type) {
	this.feedback_type = feedback_type;
}

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

public Category getCategory() {
	return category;
}

public void setCategory(Category category) {
	this.category = category;
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
	return "Feedback [feedback_id=" + feedback_id + ", Date=" + date + ", status=" + status + "]";
}

public String getUser_id() {
	return user_id;
}

public void setUser_id(String user_id) {
	this.user_id = user_id;
}
}
