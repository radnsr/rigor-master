'use strict';
App.controller('DeptCtrl', [
		'$scope',
		'DeptService','SweetAlert',
		function($scope, DeptService,SweetAlert) {
			var self = this;
			self.dept = {
				dept_id : '',
				dept_name : '',
			};
			
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

				DeptService.createDept(dept).then(function(status){
					if(status==201){
						SweetAlert.swal("Data Saved!", "You created a department!", "success");
						self.fetchAllDepts();
					}else{
						SweetAlert.swal("Data not saved!", "Something went wrong!", "error");
					}
				},
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
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "You will be updated the department of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",
					   confirmButtonText: "Yes, update it!",
					   closeOnConfirm: false}, 
					function(){ 
				DeptService.updateDept(dept, id).then(function(status){
					if(status==200){
						SweetAlert.swal("Department updated!", "You updated a department of "+id, "success");
						self.fetchAllDepts();
					}else{
						SweetAlert.swal("Department not updated!", "Please contact administrator!", "error");
					}
				},
						function(errResponse) {
							console.error('Error while updating User.');
						});
			   });
			};
			// ------------Deactvaite department master data-------------
			self.deactivateDept = function(id) {
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "Your will be deactivate the department of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",confirmButtonText: "Yes, deactivate it!",					 
					   closeOnConfirm: false
					   }, 
					function(){ 
				DeptService.deactivateDept(id).then(function(status){
					if(status==200){
						SweetAlert.swal("Department deactivated","You deactivated the department of "+id,"success");
						self.fetchAllDepts();
					}else{
						SweetAlert.swal("Department is not deactivated","Please contact administrator","error");

					}
				},
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
			});
			};
			// ------------Deactvaite department master data-------------
			self.activateDept = function(id) {
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "Your will be activate the department of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",confirmButtonText: "Yes, activate it!",					 
					   closeOnConfirm: false
					   }, 
					function(){ 
				DeptService.activateDept(id).then(function(status){
					if(status==200){
						SweetAlert.swal("Department activated","You activated the department of "+id,"success");
						self.fetchAllDepts();
					}else{
						SweetAlert.swal("Department is not activated","Please contact administrator!","error");

					}
				},
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
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