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


		<div ng-controller="DeptCtrl as ctrl" layout="row" layout-wrap>

			<div flex="25"></div>

			<div flex="50">
				<md-card>
				<h2 style="text-align: center">Department Master <hr /></h2>
				<form ng-submit="ctrl.submit()" name="deptForm">
					<md-input-container class="md-block"> <label>
						Department ID</label> <input
						type="{{!ctrl.dept.dept_id ? 'text' : 'text'}}" readonly
						name="dept_id" ng-model="ctrl.dept.dept_id"> </md-input-container>
					<md-input-container class="md-block"> <label>
						Department Name</label> <input required name="dept_name"
						ng-model="ctrl.dept.dept_name">
					<div ng-messages="deptForm.name.$error">
						<div ng-message="required">This is required.</div>
					</div>
					</md-input-container>
					<md-button type="button" ng-click="ctrl.reset()">Reset</md-button>
					<md-button class="md-primary md-default-theme"
						ng-disabled="deptForm.$invalid">{{!ctrl.dept.dept_id ?
					'Add' : 'Update'}}</md-button>
				</form>
				</md-card>
				<md-card>
				<div layout="row">
					<md-input-container>
					 <label>Search ID</label> <input ng-model="search.dept_id" id="search">
					 </md-input-container>
					 <md-input-container>
					 <label>Search Name</label> <input ng-model="search.dept_name" id="search">
					  </md-input-container>

					<md-input-container> <label>Items per
						page</label> <input type="number" min="1" max="100" ng-model="pageSize">
					</md-input-container>
				</div>



				<table class="md-table">
					<thead>
						<tr>
							<th class="table-header"><a href ng-click="orderByField='dept_id'; reverseSort = !reverseSort">ID</a></th>
							<th class="table-header"><a href ng-click="orderByField='dept_name'; reverseSort = !reverseSort">Department Name</a></th>
							<th class="table-header"><a href ng-click="orderByField='status'; reverseSort = !reverseSort">Status</a></th>
							<th class="table-header" colspan="2">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr
							dir-paginate="u in ctrl.depts | filter:{dept_id: search.dept_id, dept_name: search.dept_name} | orderBy: orderByField:reverseSort | itemsPerPage:pageSize"
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


	<script src="<c:url value='/assets/js/controller/dept_controller.js' />"></script>
	<script src="<c:url value='/assets/js/service/dept_service.js' />"></script>



</body>
</html>
