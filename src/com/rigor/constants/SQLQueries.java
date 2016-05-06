package com.rigor.constants;


public class SQLQueries {
	
	//USER related queries
	public static final String USER_ACTIVATION = "UPDATE com.rigor.model.User SET status=1 WHERE user_id = :user_id";
	public static final String USER_DEACTIVATION = "UPDATE com.rigor.model.User SET status=0 WHERE user_id = :user_id";
	
	//FEEDBACK related queries
	public static final String FEEDBACK_GETALL = "SELECT fb FROM com.rigor.model.Feedback  fb, fb.category  cat ";
	
	public static final String FEEDBACK_GET_BY_DEPT = "FROM com.rigor.model.Feedback  fb INNER JOIN fb.category cat"
			+ " WHERE cat.dept_id='D0001'";
	
	
	//Department related queries
	public static final String DEPARTMENT_DEACTIVATION = "UPDATE com.rigor.model.Department SET status=0 WHERE dept_id = :dept_id";
	public static final String DEPARTMENT_ACTIVATION = "UPDATE com.rigor.model.Department SET status=1 WHERE dept_id = :dept_id";

	//Category related queries
	public static final String CATEGORY_GETALL ="SELECT cat.category_id,cat.description,dept.dept_id,dept.dept_name, cat.status "
			+ "FROM  com.rigor.model.Category as cat , com.rigor.model.Department as dept "
			+ "WHERE cat.department.dept_id=dept.dept_id";
	public static final String CATEGORY_DEACTIVATION = "UPDATE com.rigor.model.Category SET status=0 WHERE category_id = :category_id";
	public static final String CATEGORY_ACTIVATION = "UPDATE com.rigor.model.Category SET status=1 WHERE category_id = :category_id";


}
