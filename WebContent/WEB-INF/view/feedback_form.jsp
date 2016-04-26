<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en" ng-app="StarterApp">
<head>
<%@include file="global_css.jsp"%>
</head>

<body layout="row" ng-controller="AppCtrl">
	<%@include file="side-nav.jsp"%>


	<div layout="column" class="relative" layout-fill role="main">

		<%@ include file="header.jsp"%>


		<div ng-controller="FeedbackCtrl" layout="row" layout-wrap>

			<div flex="25"></div>

			<div flex="50">
				<md-card>
				<h2 style="text-align: center">
					Feedback Form
					<hr />
				</h2>
				<form ng-submit="submit()" name="feedbackForm">
				<input type="hidden" ng-model="feedback.fb.user_id" ng-init="feedback.fb.user_id =' ${user.user_id }'"> 
					<md-input-container class="md-block"> <label>
						Feedback ID</label> <input
						type="{{!feedback.fb.feedback_id ? 'text' : 'text'}}" readonly
						name="feedback_id" ng-model="feedback.fb.feedback_id"> </md-input-container>
					<label style="color: rgb(204, 204, 204);">Type :</label>
					<md-select ng-model="feedback.fb.feedback_type" name="type" required
						placeholder="Select a type"> <md-option
						ng-repeat="type in type_list" value="{{type}}"
						class="md-primary"> {{type}} </md-option> </md-select>
					<div ng-messages="feedbackForm.type.$error">
						<div ng-message="required"
							style="font-size: 12px; line-height: 24px;">This is
							required.</div>
					</div>
					<br />
					<label style="color: rgb(204, 204, 204);">Department :</label>
					<md-select ng-model="feedback.dept.dept_id" required ng-change="loadCatList(feedback.dept.dept_id)" name="dept_id" 
						placeholder="Select a department"> <md-option
						ng-repeat="dept in dept_list" value="{{dept.dept_id}}"
						class="md-primary"> {{dept.dept_name}} </md-option> </md-select>
					<div ng-messages="feedbackForm.dept_id.$error">
						<div ng-message="required"
							style="font-size: 12px; line-height: 24px;">This is
							required.</div>
					</div>
					<br />
					<label style="color: rgb(204, 204, 204);">Category :</label>
					<md-select ng-model="feedback.cat.category_id"  name="category_id" required
						placeholder="Select a category"> <md-option
						ng-repeat="cat in cat_list track by $index" value="{{cat.category_id}}"
						class="md-primary"> {{cat.description}} </md-option> </md-select>
					<div ng-messages="feedbackForm.category_id.$error">
						<div ng-message="required"
							style="font-size: 12px; line-height: 24px;">This is
							required.</div>
					</div>
					<br />

					<md-input-container class="md-block"> <label>
						Description</label> <textarea  md-maxlength="150" required name="feedback_description"
						ng-model="feedback.fb.description"> </textarea>
					<div ng-messages="feedbackForm.feedback_description.$error">
						<div ng-message="required">This is required.</div>
					</div>
					</md-input-container>
					<br />
					<md-button type="button" ng-click="reset()">Reset</md-button>
					<md-button class="md-primary md-default-theme"
						ng-disabled="feedbackForm.$invalid">{{!feedback.fb.feedback_id ? 'Add' : 'Update'}}</md-button>
				</form>
				</md-card>
				</div>

			<div flex="25"></div>
		</div>

	</div>
	<%@include file="global_js.jsp"%>
	<%@include file="table-sources.jsp"%>


	<script
		src="<c:url value='/assets/js/controller/feedback_controller.js' />"></script>
	<script src="<c:url value='/assets/js/service/feedback_service.js' />"></script>



</body>
</html>
