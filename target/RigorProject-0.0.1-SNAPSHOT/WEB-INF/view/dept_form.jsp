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

		<md-content flex md-scroll-y> <ui-view layout="column"
			layout-fill layout-padding>
		<div class="inset" hide-sm></div>


		<form ng-controller="AppCtrl2 as ctrl" ng-submit="ctrl.submit()" name="myForm">
       <input type="hidden" ng-model="ctrl.user.id" />
			<md-input-container class="md-block"> <label>
				Name</label> <input required name="name" ng-model="ctrl.user.name">
			<div ng-messages="myForm.name.$error">
				<div ng-message="required">This is required.</div>
			</div>
			</md-input-container>
			<md-input-container class="md-block"> <label>
				Address</label> <input required name="address" ng-model="ctrl.user.address">
			<div ng-messages="myForm.address.$error">
				<div ng-message="required">This is required.</div>
			</div>
			</md-input-container>
			<md-input-container class="md-block"> <label>
				Email</label> <input required type="email" name="email"
				ng-model="ctrl.user.email" minlength="5" maxlength="50"
				ng-pattern="/^.+@.+\..+$/" />
			<div ng-messages="myForm.email.$error">
				<div ng-message="required">This is required.</div>
				<div ng-message="minlength">Minimum length is 5.</div>
				<div ng-message="pattern">Not look like an e-mail address.</div>
				<div ng-message="maxlength">Maximum length is 50.</div>

			</div>
			</md-input-container>
			<md-button type="button" ng-click="reset()">RESET</md-button>
			<md-button class="md-primary md-default-theme"
				ng-disabled="myForm.$invalid">ADD</md-button>
		</form>



		</ui-view> </md-content>
	</div>
	<%@include file="global_js.jsp"%>
	<script src="<c:url value='/assets/js/test_con.js' />"></script>
	<script src="<c:url value='/assets/js/test_service.js' />"></script>
</body>
</html>
