'use strict';
App.controller('FeedbackCtrl', [
		'$scope',
		'FeedbackService',
		function($scope, FeedbackService) {
			$scope.feedback = {
					fb:{feedback_id : '',feedback_type:'',description : ''},
					cat:{category_id:''},
					dept:{dept_id:''},
					
			};
			$scope.type_list=["Suggestion","Complain"];
			$scope.pageSize=3;
			$scope.categories = [];
			$scope.dept_list=[];
			$scope.cat_list=[];
			//-----------load Category options----------
			
			$scope.loadCatList=function(id){
				$scope.feedback.cat.category_id='';
				FeedbackService.categoryList(id).then(function(data) {
					$scope.cat_list = data;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};
			
		
			//----------load Department options--------------
			$scope.loadDeptsList=function(){
				FeedbackService.departmentList().then(function(data) {
					$scope.dept_list = data;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};
			
			$scope.loadDeptsList();
			// ------------Fetch all feedback master data--------------
			$scope.fetchAllData = function() {
				FeedbackService.fetchAllData().then(function(d) {
					$scope.categories = d;
				}, function(errResponse) {
					console.error('Error while fetching categories');
				});
			};

			// ------------Create new feedback master data--------------
			$scope.create = function(feedback) {
				FeedbackService.create(feedback);
			};
			$scope.fetchAllData();// execute the function when page loading
			// ------------Submit form in master_feedback.jsp--------------
			$scope.submit = function() {

				if ($scope.feedback.fb.feedback_id == '') {
					console.log('Saving New feedback', $scope.feedback);
					$scope.create($scope.feedback);

				} else {
					$scope.update($scope.feedback, $scope.feedback.fb.feedback_id);
					console.log('feedback updated with id ', $scope.feedback.fb.feedback_id);
				}

				$scope.reset();
			};
			// ------------Reset form in master_feedback.jsp-------------
			$scope.reset = function() {
				$scope.feedback = {
						fb:{feedback_id : '',feedback_type:'',description : ''},
						cat:{category_id:''},
						dept:{dept_id:''}
				};
				$scope.feedbackForm.$setPristine(); // reset Form
			};

			// ------------Update feedback master data-------------
			$scope.update = function(feedback, id) {
				FeedbackService.update(feedback, id).then($scope.fetchAllData,
						function(errResponse) {
							console.error('Error while updating feedback.');
						});
			};
			// ------------Deactvaite feedback master data-------------
			$scope.deactivate = function(id) {
				FeedbackService.deactivate(id).then($scope.fetchAllData,
						function(errResponse) {
					console.error('Error while deactivating feedback.');
				});
			};
			// ------------Deactvaite feedback master data-------------
			$scope.activate = function(id) {
				FeedbackService.activate(id).then($scope.fetchAllData,
						function(errResponse) {
					console.error('Error while deactivating feedback.');
				});
			};
			// Edit the form in master_feedback.jsp
			$scope.edit = function(id) {
				console.log('id to be edited', id);
				for (var i = 0; i < $scope.categories.length; i++) {
					if ($scope.categories[i][0] == id) {
						var clone = angular.copy($scope.categories[i]);
						
						$scope.feedback = {
								cat:{feedback_id : clone[0],description : clone[1]},
								dept:{dept_id:clone[2]}
						};
						break;
					}
				}
			};
} ]);