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

			<div flex="60">
				<md-card>
				<h2 style="text-align: center">
					Feedback List
					<hr />
				</h2>
							
				<div layout="row">
					<md-input-container>
					 <label>Search here</label> <input ng-model="q" id="search">
					 </md-input-container>
					<md-input-container>
					 <label>Items per
						page</label> <input type="number" min="1" max="100" ng-model="pageSize">
					</md-input-container>
				</div>



				<table class="md-table">
					<thead>
						<tr>
							<th class="table-header"><a href ng-click="orderByField='u[0].feedback_id'; reverseSort = !reverseSort">ID</a></th>
							<th class="table-header"><a href ng-click="orderByField='u[0].feedback_type'; reverseSort = !reverseSort">Feedback Type</a></th>
							<th class="table-header">Category Name</th>
							<th class="table-header">Department</th>
							<th class="table-header">Date</th>
							<th class="table-header">Status</th>
							<th class="table-header" colspan="2">Action</th>
						</tr>
					</thead>
					<tbody>
						<tr dir-paginate="u in feedback_list | filter:q | orderBy: orderByField:reverseSort | itemsPerPage:pageSize"
							class="md-table-row">
							<td><span ng-bind="u[0].feedback_id"></span></td>
							<td><span ng-bind="u[0].feedback_type"></span></td>
							<td><span ng-bind="u[0].category.description"></span></td>
							<td><span ng-bind="u[0].department.dept_name"></span></td>
							<td><span ng-bind="u[0].date"></span></td>
							<td><span ng-if="u[0].status==1" style="color: #0f9d58;">Active</span>
								<span style="color: rgb(253, 65, 64)" ng-if="u[0].status==0">Inactive</span></td>
							<td><md-button type="button" ng-click="showAlert(u[0].description, u[0].feedback_id)"
									class="md-raised">View</md-button></td>
							<td><md-button ng-if="u[0].status==1" class="md-raised md-primary"
									type="button" ng-click="deactivate(u[0])">Cancel</md-button>
								<md-button ng-if="u[0].status==0" type="button"
									class="md-raised md-primary" ng-disabled="true">Cancel</md-button></td>
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
		src="<c:url value='/assets/js/controller/feedback_controller.js' />"></script>
	<script src="<c:url value='/assets/js/service/feedback_service.js' />"></script>



</body>
</html>
