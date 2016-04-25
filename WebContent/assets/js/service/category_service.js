'use strict';
App.factory('CategoryService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				departmentList : function() {
				return $http
				.get('http://localhost:8080/RigorProject/dept_list/')
				.then(function(response) {
					return response.data;
				}, function(errResponse) {
					console.error('Error while fetching depts');
					return $q.reject(errResponse);
				});
	},
				create : function(dept) {
					return $http.post(
							'http://localhost:8080/RigorProject/category/', dept)
							.then(function(response) {
								console.log(response);
								return response.data;
							}, function(errResponse) {

								 console.error('Error while creating category');
								return $q.reject(errResponse);
							});
				},
				fetchAllData : function() {
					return $http
							.get('http://localhost:8080/RigorProject/category/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching category');
								return $q.reject(errResponse);
							});
				},
				update : function(dept, id) {
					return $http.put(
							'http://localhost:8080/RigorProject/category/' + id,
							dept).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating category');
						return $q.reject(errResponse);
					});
				},
				deactivate : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/category_deactivate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while deactivating category');
						return $q.reject(errResponse);
					});

				},
				activate : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/category_activate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while activating category');
						return $q.reject(errResponse);
					});
				}
			}
		} ]);