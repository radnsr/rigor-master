'use strict';
App.controller('CategoryCtrl', [
		'$scope',
		'CategoryService',
		function($scope, CategoryService) {
			$scope.category = {
					cat:{category_id : '',description : ''},
					dept:{dept_id:''}
			};
			$scope.pageSize=3;
			$scope.categories = [];
			$scope.dept_list=[];
			
			//----------load Department options--------------
			$scope.loadDeptsList=function(){
				CategoryService.departmentList().then(function(data) {
					$scope.dept_list = data;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};
			
			$scope.loadDeptsList();
			// ------------Fetch all category master data--------------
			$scope.fetchAllData = function() {
				CategoryService.fetchAllData().then(function(d) {
					$scope.categories = d;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};

			// ------------Create new category master data--------------
			$scope.create = function(category) {

				CategoryService.create(category).then($scope.fetchAllData,
						function(errResponse) {

							console.log(errResponse.data);
						});
			};
			$scope.fetchAllData();// execute the function when page loading
			// ------------Submit form in master_category.jsp--------------
			$scope.submit = function() {

				if ($scope.category.cat.category_id == '') {
					console.log('Saving New category', $scope.category);
					$scope.create($scope.category);

				} else {
					$scope.update($scope.category, $scope.category.cat.category_id);
					console.log('category updated with id ', $scope.category.cat.category_id);
				}

				$scope.reset();
			};
			// ------------Reset form in master_category.jsp-------------
			$scope.reset = function() {
				$scope.category = {
						cat:{category_id : '',description : ''},
						dept:{dept_id:''}
				};
				$scope.categoryForm.$setPristine(); // reset Form
			};

			// ------------Update category master data-------------
			$scope.update = function(category, id) {
				CategoryService.update(category, id).then($scope.fetchAllData,
						function(errResponse) {
							console.error('Error while updating category.');
						});
			};
			// ------------Deactvaite category master data-------------
			$scope.deactivate = function(id) {
				CategoryService.deactivate(id).then($scope.fetchAllData,
						function(errResponse) {
					console.error('Error while deactivating category.');
				});
			};
			// ------------Deactvaite category master data-------------
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
					if ($scope.categories[i][0] == id) {
						var clone = angular.copy($scope.categories[i]);
						
						$scope.category = {
								cat:{category_id : clone[0],description : clone[1]},
								dept:{dept_id:clone[2]}
						};
						break;
					}
				}
			};
} ]);