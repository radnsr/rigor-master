'use strict';
App.factory('DeptService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				createDept : function(dept) {
					return $http.post(
							'http://localhost:8080/RigorProject/dept/', dept)
							.then(function(response) {
								console.log(response);
								return response.data;
							}, function(errResponse) {

								// console.error('Error while creating user');
								return $q.reject(errResponse);
							});
				},
				fetchAllDepts : function() {
					return $http
							.get('http://localhost:8080/RigorProject/dept/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching depts');
								return $q.reject(errResponse);
							});
				},
				updateDept : function(dept, id) {
					return $http.put(
							'http://localhost:8080/RigorProject/dept/' + id,
							dept).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating dept');
						return $q.reject(errResponse);
					});
				},
				deactivateDept : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/dept_deactivate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching depts');
						return $q.reject(errResponse);
					});

				},
				activateDept : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/dept_activate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching depts');
						return $q.reject(errResponse);
					});
				}
			}
		} ]);