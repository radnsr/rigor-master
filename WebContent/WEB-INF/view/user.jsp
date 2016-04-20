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


		<div ng-controller="UserCtrl" layout="row" layout-wrap>

			<div flex="25"></div>

			<div flex="60">
				<md-card>
				<h2 style="text-align: center">
					User Registration
					<hr />
				</h2>
				<form ng-submit="submit()" name="userForm">
					<md-input-container class="md-block"> <label>
						User ID</label> <input type="{{!user.user_id ? 'text' : 'text'}}" readonly
						name="dept_id" ng-model="user.user_id"> </md-input-container>
					<md-input-container class="md-block"> <label>
						Name</label> <input required name="name" ng-model="user.name">
					<div ng-messages="userForm.name.$error">
						<div ng-message="required">This is required.</div>
					</div>
					</md-input-container>
					<md-input-container class="md-block"> <label>
						Employee Number</label> <input minlength="7" maxlength="7" type="number"
						required name="emp_no" ng-model="user.emp_no">
					<div ng-messages="userForm.emp_no.$error">
						<div ng-message="required">This is required.</div>
						<div ng-message="number">This should be a number.</div>
						<div ng-message="minlength">Length should be 7.</div>
						<div ng-message="maxlength">Length should be 7.</div>

					</div>
					</md-input-container>
					<md-input-container class="md-block"> <label>
						Email</label> <input required type="email" name="email"
						ng-model="user.email" minlength="5" maxlength="50"
						ng-pattern="/^.+@.+\..+$/" />
					<div ng-messages="userForm.email.$error">
						<div ng-message="required">This is required.</div>
						<div ng-message="minlength">Minimum length is 5.</div>
						<div ng-message="pattern">Not look like an e-mail address.</div>
						<div ng-message="maxlength">Maximum length is 50.</div>

					</div>
					</md-input-container>

					<label style="color: rgb(204, 204, 204);">Role :</label>
					<md-select ng-model="user.role" name="role" required placeholder="Select a role"> <md-option
						ng-repeat="r in role_option" value="{{r}}" class="md-primary">
					{{r}} </md-option> </md-select>
					<div ng-messages="userForm.role.$error">
						<div ng-message="required"
							style="font-size: 12px; line-height: 24px;">This is
							required.</div>
					</div>
					<br />


					<md-button type="button" ng-click="reset()">Reset</md-button>
					<md-button class="md-primary md-default-theme"
						ng-disabled="userForm.$invalid">{{!user.user_id ?
					'Add' : 'Update'}}</md-button>
				</form>
				</md-card>
				<md-card>
				<div layout="row">
					<md-input-container> <label>Search
						here</label> <input ng-model="q" id="search"> </md-input-container>

					<md-input-container> <label>Items per
						page</label> <input type="number" min="1" max="100" ng-model="pageSize">
					</md-input-container>
				</div>



				<table class="md-table">
					<thead>
						<tr>
							<th class="table-header">User ID</th>
							<th class="table-header">Name</th>
							<th class="table-header">Employee No.</th>
							<th class="table-header">Email</th>
							<th class="table-header">Role</th>
							<th class="table-header">Status</th>
							<th class="table-header" colspan="2">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr
							dir-paginate="u in users | filter:q | orderBy: 'user_id' | itemsPerPage:pageSize"
							class="md-table-row">
							<td><span ng-bind="u.user_id"></span></td>
							<td><span ng-bind="u.name"></span></td>
							<td><span ng-bind="u.emp_no"></span></td>
							<td><span ng-bind="u.email"></span></td>
							<td><span ng-bind="u.role"></span></td>
							<td><span ng-if="u.status==2" style="color: #ff8000;">Fresh</span>
								<span ng-if="u.status==1" style="color: #0f9d58;">Active</span>
								<span style="color: rgb(253, 65, 64)" ng-if="u.status==0">Inactive</span></td>
							<td><md-button type="button" ng-click="edit(u.user_id)"
									class="md-raised">Edit</md-button></td>
							<td><md-button ng-if="u.status==1" class="md-raised md-warn"
									type="button" ng-click="deactivate(u.user_id)">Deactivate</md-button>

								<md-button ng-if="u.status !=1" type="button"
									class="md-raised md-primary" ng-click="activate(u.user_id)">Activate</md-button>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="other-controller">
					<dir-pagination-controls
						template-url="<c:url value='/assets/template/dirPagination.tpl.html' />">
					</dir-pagination-controls>
				</div>
				</md-card>
			</div>

			<div flex="15"></div>
		</div>

	</div>
	<%@include file="global_js.jsp"%>
	<%@include file="table-sources.jsp"%>


	<script
		src="<c:url value='/assets/js/controller/user_controller.js' />"></script>
	<script src="<c:url value='/assets/js/service/user_service.js' />"></script>



</body>
</html>
