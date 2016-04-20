package com.rigor.model;

import java.sql.Date;

public class Feedback {
	
private String feedback_id;
private Date date;
private String feedback_type;
private String user_id;
private String dept_id;
private String category_id;
private String description;
private int status;

public Feedback(String feedback_id,Date date,String feedback_type,String user_id,String dept_id, String category_id,String description,int status){

	this.feedback_id=feedback_id;
	this.date=date;
	this.feedback_type=feedback_type;
	this.user_id=user_id;
	this.dept_id=dept_id;
	this.category_id=category_id;
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
public void setDate(Date date) {
	this.date = date;
}
public String getFeedback_type() {
	return feedback_type;
}
public void setFeedback_type(String feedback_type) {
	this.feedback_type = feedback_type;
}
public String getUser_id() {
	return user_id;
}
public void setUser_id(String user_id) {
	this.user_id = user_id;
}
public String getDept_id() {
	return dept_id;
}
public void setDept_id(String dept_id) {
	this.dept_id = dept_id;
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
}
