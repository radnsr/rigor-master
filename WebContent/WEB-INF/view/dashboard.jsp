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

		<md-grid-list md-cols-xs="1" md-cols-sm="2" md-cols-md="4"
			md-cols-gt-md="6" md-row-height-gt-md="1:1" md-row-height="2:2"
			md-gutter="12px" md-gutter-gt-sm="8px"> <md-grid-tile
			class="gray" md-rowspan="3" md-colspan="2" md-colspan-sm="1"
			md-colspan-xs="1"> </md-grid-tile>
			 <md-grid-tile class="green">
		<md-button href="http://localhost:8080/RigorProject/user"> <ng-md-icon
			icon="supervisor_account" style="fill: pink" size="100"></ng-md-icon>
		</md-button> <md-grid-tile-footer>
		<h3>Users</h3>
		</md-grid-tile-footer> </md-grid-tile> 
		
			 
			 <md-grid-tile class="green">
		<md-button href="http://localhost:8080/RigorProject/feedback_form"> <ng-md-icon
			icon="supervisor_account" style="fill: pink" size="100"></ng-md-icon>
		</md-button> <md-grid-tile-footer>
		<h3>Feedback Form</h3>
		</md-grid-tile-footer> </md-grid-tile> 
		
			 <md-grid-tile class="green">
		<md-button href="http://localhost:8080/RigorProject/feedback_list"> <ng-md-icon
			icon="supervisor_account" style="fill: pink" size="100"></ng-md-icon>
		</md-button> <md-grid-tile-footer>
		<h3>Feedback List</h3>
		</md-grid-tile-footer> </md-grid-tile> 
		
		
		
		</md-grid-list> </ui-view> </md-content>
	</div>
	<%@include file="global_js.jsp"%>
</body>
</html>