'use strict';
App.controller('CategoryCtrl', [
		'$scope',
		'CategoryService',
		function($scope, CategoryService) {
			var $scope = this;
			$scope.category = {
				category_id : '',
				dept_id:'',
				description : ''
			};
			$scope.dept='';
			$scope.dept_option=[{id:'D0001',name:'HR' },{id:'D0002',name:'IT'}];
			$scope.r_list=["Administrator" , "Manager", "Employee" ];
			$scope.pageSize=3;
			$scope.categories = [];
			// ------------Fetch all Category master data--------------
			$scope.fetchAllData = function() {
				CategoryService.fetchAllData().then(function(d) {
					$scope.categories = d;
				}, function(errResponse) {
					console.error('Error while fetching Categories');
				});
			};

			// ------------Create new Category master data--------------
			$scope.create = function(category) {

				CategoryService.create(category).then($scope.fetchAllData,
						function(errResponse) {

							console.log(errResponse.data);
						});
			};
			$scope.fetchAllData();// execute the function when page loading
			// ------------Submit form in master_category.jsp--------------
			$scope.submit = function() {

				if ($scope.category.category_id == '') {
					console.log('Saving New User', $scope.category);
					$scope.createDept($scope.category);

				} else {
					$scope.update($scope.category, $scope.category.category_id);
					console.log('User updated with id ', $scope.category.category_id);
				}

				$scope.reset();
			};
			// ------------Reset form in master_category.jsp-------------
			$scope.reset = function() {
				$scope.category = {
					category_id : '',
					dept_id:'',
					description : ''
				};
				alert("sda");
				$scope.categoryForm.$setPristine(); // reset Form
			};

			// ------------Update department master data-------------
			$scope.update = function(category, id) {
				CategoryService.update(category, id).then($scope.fetchAllData,
						function(errResponse) {
							console.error('Error while updating User.');
						});
			};
			// ------------Deactvaite department master data-------------
			$scope.deactivate = function(id) {
				CategoryService.deactivate(id).then($scope.fetchAllData,
						function(errResponse) {
					console.error('Error while deactivating category.');
				});
			};
			// ------------Deactvaite department master data-------------
			$scope.activate = function(id) {
				CategoryService.activate(id).then($scope.fetchAllData,
						function(errResponse) {
					console.error('Error while deactivating category.');
				});
			};
			// Edit the form in master_category.jsp
			$scope.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < $scope.categories.length; i++) {
					if ($scope.categories[i].category_id == id) {
						$scope.category = angular.copy($scope.categories[i]);
						break;
					}
				}
			};
			
		} ]);