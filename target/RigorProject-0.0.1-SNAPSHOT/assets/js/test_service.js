'use strict';
App.factory('RestService', [
		'$http',
		'$q',
		function($http, $q) {

			return {
				createUser : function(user) {
					return $http.post(
							'http://localhost:8080/RigorProject/user_post/',
							user).then(function(response) {
						
						return response.data;
					}, function(errResponse) {

						//console.error('Error while creating user');
						return $q.reject(errResponse);
					});
				},
			};

		} ]);