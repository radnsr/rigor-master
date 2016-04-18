'use strict';
App.controller('UserCtrl', [
		'$scope',
		'UserService',
		function($scope, UserService) {
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

				UserService.createUser(user).then($scope.fetchAllUsers,
						function(errResponse) {

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
				UserService.updateUser(user, id).then($scope.fetchAllUsers,
						function(errResponse) {
							console.error('Error while updating User.');
						});
			};
			// ------------Deactvaite user data-------------
			$scope.deactivate = function(id) {
				UserService.deactivate(id).then($scope.fetchAllUsers,
						function(errResponse) {
					console.error('Error while deactivating dept.');
				});
			};
			// ------------Deactvaite user data-------------
			$scope.activate = function(id) {
				UserService.activate(id).then($scope.fetchAllUsers,
						function(errResponse) {
					console.error('Error while deactivating dept.');
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