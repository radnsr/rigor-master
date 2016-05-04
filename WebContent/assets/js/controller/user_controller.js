'use strict';
App.controller('UserCtrl', [
		'$scope',
		'UserService','SweetAlert',
		function($scope, UserService,SweetAlert) {
			$scope.user = {
				user_id : '',
				name : '',
				emp_no:null,
				email:'',
				role:''
			};
			
			$scope.role_option=["Administrator" , "Manager", "Employee" ];
		
			$scope.pageSize=3;
			$scope.users = [];
			$scope.dept_list=[];
			//----------load Department options--------------
			$scope.loadDeptsList=function(){
				UserService.departmentList().then(function(data) {
					$scope.dept_list = data;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};
			$scope.loadDeptsList();
			// ------------Fetch all user data--------------
			$scope.fetchAllUsers = function() {
				UserService.fetchAllUsers().then(function(d) {
					$scope.users = d;
				}, function(errResponse) {
					console.error('Error while fetching Depts');
				});
			};

			// ------------Create new user data--------------
			$scope.create = function(user) {

				UserService.createUser(user).then(function(status){
					
					if(status==201){
						SweetAlert.swal("Data Saved!", "You created a user!", "success");
						$scope.fetchAllUsers();
					}else{
						SweetAlert.swal("Data not saved!", "Something went wrong!", "error");
					}
					
				},function(errResponse) {
							console.log(errResponse.data);
						});
				
			};
			$scope.fetchAllUsers();// execute the function when page loading
			// ------------Submit form in user.jsp--------------
			$scope.submit = function() {

				if ($scope.user.user_id == '') {
					console.log('Saving New User', $scope.user);
					$scope.create($scope.user);

				} else {
					$scope.update($scope.user, $scope.user.user_id);
					console.log('User updated with id ', $scope.user.user_id);
				}

				$scope.reset();
			};
			// ------------Reset form in user.jsp-------------
			$scope.reset = function() {
				$scope.user = {
						user_id : '',
						name : '',
						emp_no:null,
						email:'',
						role:''
					};
				$scope.userForm.$setPristine(); // reset Form
			};

			// ------------Update user data-------------
			$scope.update = function(user, id) {
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "You will be updated the user of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",
					   confirmButtonText: "Yes, update it!",
					   closeOnConfirm: false}, 
					function(){ 
							
				UserService.updateUser(user, id).then(function(status){
					if(status==200){
						SweetAlert.swal("User updated!", "You updated a user of "+id, "success");
						$scope.fetchAllUsers();
					}else{
						SweetAlert.swal("User not updated!", "User of "+id+" is not updated!", "error");
					}
				},function(errResponse) {
							console.error('Error while updating User.');
						});
				});
			};
			// ------------Deactvaite user data-------------
			$scope.deactivate = function(id) {
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "Your will be deactivate the user of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",confirmButtonText: "Yes, deactivate it!",					 
					   closeOnConfirm: false
					   }, 
					function(){ 
					   
						   UserService.deactivate(id).then(function(status){
								if(status==200){
									SweetAlert.swal("User deactivated!", "You deactivated a user of "+id, "success");
									$scope.fetchAllUsers();
								}else{
									SweetAlert.swal("User not deactivated", "User of "+id+" is not deactivated! ", "error");
								}
							},
									function(errResponse) {
								console.error('Error while deactivating dept.');
							});					   
					});
				
				
			};
			// ------------Actvaite user data-------------
			$scope.activate = function(id) {
				
				SweetAlert.swal({
					   title: "Are you sure?",
					   text: "Your will be activate the user of "+id,
					   type: "warning",
					   showCancelButton: true,
					   confirmButtonColor: "#DD6B55",
					   confirmButtonText: "Yes, activate it!",
					   closeOnConfirm: false}, 
					function(){ 
					  				
				UserService.activate(id).then(function(status){
					if(status==200){
						SweetAlert.swal("User activated!", "You activated a user of "+id, "success");
						$scope.fetchAllUsers();
					}else{
						SweetAlert.swal("User not activated", "Please contact administrator! ", "error");
					}
				},
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
					   });
			};
			// Edit the form in user.jsp
			$scope.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < $scope.users.length; i++) {
					if ($scope.users[i].user_id == id) {
						$scope.user = angular.copy($scope.users[i]);
						break;
					}
				}
			};
			
		} ]);