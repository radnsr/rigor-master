'use strict';
App.factory('UserService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				departmentList : function() {
					return $http.get(
							'http://localhost:8080/RigorProject/dept_list/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching depts');
								return $q.reject(errResponse);
							});
				},
				createUser : function(user) {
					return $http.post(
							'http://localhost:8080/RigorProject/user/', user)
							.then(function(response) {
								console.log(response);
								return response.data;
							}, function(errResponse) {

								console.error('Error while creating user');
								return $q.reject(errResponse);
							});
				},
				fetchAllUsers : function() {
					return $http
							.get('http://localhost:8080/RigorProject/user/')
							.then(function(response) {
								return response.data;
							}, function(errResponse) {
								console.error('Error while fetching users');
								return $q.reject(errResponse);
							});
				},
				updateUser : function(user, id) {
					return $http.put(
							'http://localhost:8080/RigorProject/user/' + id,
							user).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while updating user');
						return $q.reject(errResponse);
					});
				},
				deactivate : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/user_deactivate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching depts');
						return $q.reject(errResponse);
					});

				},
				activate : function(id) {
					return $http.get(
							'http://localhost:8080/RigorProject/user_activate/'
									+ id).then(function(response) {
						return response.data;
					}, function(errResponse) {
						console.error('Error while fetching depts');
						return $q.reject(errResponse);
					});
				}
			}
		} ]);