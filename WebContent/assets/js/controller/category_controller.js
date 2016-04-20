'use strict';
App.controller('CategoryCtrl', [
		'$scope',
		'CategoryService',
		function($scope, CategoryService) {
			var self = this;
			self.dept = {
				dept_id : '',
				dept_name : '',
			};
			$scope.dept_list=[{id:'D001',name:'HR' },{id:'D002',name:'IT'}];
			
			$scope.pageSize=3;
			self.depts = [];
			// ------------Fetch all Department master data--------------
			self.fetchAllDepts = function() {
				DeptService.fetchAllDepts().then(function(d) {
					self.depts = d;
				}, function(errResponse) {
					console.error('Error while fetching Depts');
				});
			};

			// ------------Create new Department master data--------------
			self.createDept = function(dept) {

				DeptService.createDept(dept).then(self.fetchAllDepts,
						function(errResponse) {

							console.log(errResponse.data);
						});
			};
			self.fetchAllDepts();// execute the function when page loading
			// ------------Submit form in master_dept.jsp--------------
			self.submit = function() {

				if (self.dept.dept_id == '') {
					console.log('Saving New User', self.dept);
					self.createDept(self.dept);

				} else {
					self.updateDept(self.dept, self.dept.dept_id);
					console.log('User updated with id ', self.dept.dept_id);
				}

				self.reset();
			};
			// ------------Reset form in master_dept.jsp-------------
			self.reset = function() {
				self.dept = {
					dept_id : '',
					dept_name : '',
				};
				$scope.deptForm.$setPristine(); // reset Form
			};

			// ------------Update department master data-------------
			self.updateDept = function(dept, id) {
				DeptService.updateDept(dept, id).then(self.fetchAllDepts,
						function(errResponse) {
							console.error('Error while updating User.');
						});
			};
			// ------------Deactvaite department master data-------------
			self.deactivateDept = function(id) {
				DeptService.deactivateDept(id).then(self.fetchAllDepts,
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
			};
			// ------------Deactvaite department master data-------------
			self.activateDept = function(id) {
				DeptService.activateDept(id).then(self.fetchAllDepts,
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
			};
			// Edit the form in master_dept.jsp
			self.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < self.depts.length; i++) {
					if (self.depts[i].dept_id == id) {
						self.dept = angular.copy(self.depts[i]);
						break;
					}
				}
			};
			
		} ]);