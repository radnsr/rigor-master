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
	<form name="freshForm" action="http://localhost:8080/RigorProject/user_password/"  method="post">
		<span
			style="color: 0099FF; font-style: italic; font-family: 'Times New Roman';">Hi
			<span style="color: 003399; font-style: bold">${user_fresh.name}</span>,
			<br /> This is your first login.Please enter new password to
			activate your account.
		</span> <input name="user_id" ng-model="user.user_id">
		<md-input-container> <label>Email</label> <label
			style="color: black">${user_fresh.email}</label> </md-input-container>
		<md-input-container class="md-block"> <label>Password</label>
		<input type="password" name="password" ng-model="user.password"
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
			ng-model="con_pwd"
			wj-validation-error="con_pwd != user.password ? 'Passwords don\'t match' : ''">
		<div ng-messages="freshForm.con_password.$error">
			<div ng-message="required">This is required.</div>
			<div ng-message="wjValidationError">Passwords don't match.</div>
		</div>
		</md-input-container>

		<md-button ng-disabled="freshForm.$invalid" type="submit"
			class="md-primary"> Update </md-button>
		<br />
	</form>
	<footer>
		<a href="http://www.virtusapolaris.com/" target="_blank"><img
			src="http://www.virtusa.com/common/img/logos/virtusa_tag_logo_lg.svg"></a>
		<p>Copyright © 2016 Virtusa Corporation. All Rights Reserved.</p>
	</footer>
	<%@include file="global_js.jsp"%>
	<script>
		App.controller('loginCtrl', function($scope, $http, $timeout) {
			//$scope.email_box = true;
			$timeout(function() {
				//$scope.email_box = false;
			}, 1000);
			$scope.user = {
				user_id : '${user_fresh.user_id }',
				password : '',
			};
			id = $scope.user.user_id;
			$scope.update = function() {
				console.log($scope.user);
				$http.put('http://localhost:8080/RigorProject/user_password/'
						+ id, $scope.user);
			};

		});

		App.directive('wjValidationError', function() {
			return {
				require : 'ngModel',
				link : function(scope, elm, attrs, ctl) {
					scope.$watch(attrs['wjValidationError'],
							function(errorMsg) {
								elm[0].setCustomValidity(errorMsg);
								ctl.$setValidity('wjValidationError',
										errorMsg ? false : true);
							});
				}
			};
		});
	</script>
</body>
</html>