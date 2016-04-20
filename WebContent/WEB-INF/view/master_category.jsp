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


		<div ng-controller="CategoryCtrl" layout="row" layout-wrap>

			<div flex="25"></div>

			<div flex="50">
				<md-card>
				<h2 style="text-align: center">
					Category Master
					<hr />
				</h2>
				<form ng-submit="submit()" name="categoryForm">
					<md-input-container class="md-block"> <label>
						Category ID</label> <input
						type="{{!category.category_id ? 'text' : 'text'}}" readonly
						name="category_id" ng-model="category.category_id"> </md-input-container>

					<label style="color: rgb(204, 204, 204);">Department :</label>
					<md-select ng-model="category.dept_id" name="dept_id" required placeholder="Select a department"> <md-option
						ng-repeat="dept in dept_list" value="{{dept.id}}" class="md-primary">
					{{dept.name}} </md-option> </md-select>
					<div ng-messages="categoryForm.role.$error">
						<div ng-message="required"
							style="font-size: 12px; line-height: 24px;">This is
							required.</div>
					</div>
					<br />

					<md-input-container class="md-block"> <label>
						Category Name</label> <input required name="category_name"
						ng-model="category.category_name">
					<div ng-messages="categoryForm.category_name.$error">
						<div ng-message="required">This is required.</div>
					</div>
					</md-input-container>
					<md-button type="button" ng-click="reset()">Reset</md-button>
					<md-button class="md-primary md-default-theme"
						ng-disabled="deptForm.$invalid">{{!dept.dept_id ?
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
							<th class="table-header">ID</th>
							<th class="table-header">Department Name</th>
							<th class="table-header">Status</th>
							<th class="table-header" colspan="2">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr
							dir-paginate="u in ctrl.depts | filter:q | orderBy: 'dept_id' | itemsPerPage:pageSize"
							class="md-table-row">
							<td><span ng-bind="u.dept_id"></span></td>
							<td><span ng-bind="u.dept_name"></span></td>
							<td><span ng-if="u.status==1" style="color: #0f9d58;">Active</span>
								<span style="color: rgb(253, 65, 64)" ng-if="u.status==0">Inactive</span></td>
							<td><md-button type="button" ng-click="ctrl.edit(u.dept_id)"
									class="md-raised">Edit</md-button></td>
							<td><md-button ng-if="u.status==1" class="md-raised md-warn"
									type="button" ng-click="ctrl.deactivateDept(u.dept_id)">Deactivate</md-button>

								<md-button ng-if="u.status==0" type="button"
									class="md-raised md-primary"
									ng-click="ctrl.activateDept(u.dept_id)">Activate</md-button></td>
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

			<div flex="25"></div>
		</div>

	</div>
	<%@include file="global_js.jsp"%>
	<%@include file="table-sources.jsp"%>


	<script
		src="<c:url value='/assets/js/controller/category_controller.js' />"></script>
	<script src="<c:url value='/assets/js/service/category_service.js' />"></script>



</body>
</html>
