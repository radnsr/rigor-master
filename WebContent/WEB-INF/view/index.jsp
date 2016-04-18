<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html ng-app="StarterApp">
<head>
<title>Login Page</title>
<%@include file="global_css.jsp"%>
<link rel="stylesheet" href="<c:url value='/assets/css/login.css' />" />

</head>
<body ng-controller="loginCtrl">
	
		<h1>Employee Feedback System</h1>
	<form name="loginForm"
		action="http://localhost:8080/RigorProject/login/" method="post">
		<md-input-container class="md-block"> <label>Email</label>
		<input required type="text" name="email" ng-model="user.email"
			minlength="5" maxlength="50" ng-pattern="/^.+@.+\..+$/">
		<div ng-messages="loginForm.email.$error">
			<div ng-message="required">This is required.</div>
			<div ng-message="minlength">Minimum length is 5.</div>
			<div ng-message="pattern">Not look like an e-mail address.</div>
			<div ng-message="maxlength">Maximum length is 50.</div>
		</div>
		</md-input-container>
		<md-input-container class="md-block"> <label>Password</label>
		<input type="password" required name="password"
			ng-model="user.password">
		<div ng-messages="loginForm.password.$error">
			<div ng-message="required">This is required.</div>
		</div>
		</md-input-container>

		<md-button ng-disabled="loginForm.$invalid" type="submit"
			class="md-primary"> Login
		</div>
		</md-button>
		<br /> <span ng-hide="alertMsg" style="color: red"><i>${error}</i></span>
	</form>
	<footer>
		<a href="http://www.virtusapolaris.com/" target="_blank"><img
			src="http://www.virtusa.com/common/img/logos/virtusa_tag_logo_lg.svg"></a>
		<p>Copyright © 2016 Virtusa Corporation. All Rights Reserved.</p>
	</footer>
	<%@include file="global_js.jsp"%>
	<script>
		App.controller('loginCtrl', function($scope, $timeout) {
			$scope.alertMsg = false;

			$timeout(function() {
				$scope.alertMsg = true;
			}, 3000);
		});
	</script>
</body>
</html>