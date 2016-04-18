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
	<form name="freshForm"
		action="http://localhost:8080/RigorProject/login/" method="post">
		<md-input-container class="md-block"> <label>Email</label>
		<input readonly="readonly" type="text" name="email" >
		</md-input-container>
		<md-input-container class="md-block"> <label>Password</label>
		<input type="password" name="password" ng-model="pwd"
			required minlength="8" maxlength="20"
			ng-pattern="/(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z])/">
		<div ng-messages="freshForm.password.$error">
			<div ng-message="required">This is required.</div>
			<div ng-message="minlength">Minimum length is 8 characters</div>
			<div ng-message="maxlength">Maximum length is 20 characters</div>
			<div ng-message="pattern">Must contain one lower &amp;
				uppercase letter, and one non-alpha character (a number or a
				symbol.)</div>
		</div>
		</md-input-container>
		<md-input-container class="md-block"> <label>Confirm
			Password</label> <input type="password" required name="con_password"
			ng-model="con_pwd" wj-validation-error="con_pwd != pwd ? 'Passwords don\'t match' : ''">
		<div ng-messages="freshForm.con_password.$error">
			<div ng-message="required">This is required.</div>
			<div ng-message="wjValidationError">Passwords don't match.</div>
		</div>
		</md-input-container>

		<md-button ng-disabled="loginForm.$invalid" type="submit"
			class="md-primary"> Update
		</div>
		</md-button>
		<br />
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
		
		
		App.directive('wjValidationError', function () {
			  return {
			    require: 'ngModel',
			    link: function (scope, elm, attrs, ctl) {
			      scope.$watch(attrs['wjValidationError'], function (errorMsg) {
			        elm[0].setCustomValidity(errorMsg);
			        ctl.$setValidity('wjValidationError', errorMsg ? false : true);
			      });
			    }
			  };
		});
	</script>
</body>
</html>