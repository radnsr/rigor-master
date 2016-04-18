'use strict';
App.controller('AppCtrl2', ['$scope', 'RestService', function($scope, RestService) {
	var self = this;
    self.user={id:null,name:'',address:'',email:''};
    self.users=[];
    
    self.fetchAllUsers = function(){
    	//RestService.fetchAllUsers()
    	//alert("FETCH!!");
           
    };
     
    self.createUser = function(user){
    	
    	RestService.createUser(user)
	              .then(
                self.fetchAllUsers, 
			              function(errResponse){
                	
				               console.log(errResponse.data);
			              }	
            );
    };
    self.submit = function() {
        if(self.user.id==null){
        	
            console.log('Saving New User', self.user);    
            self.createUser(self.user);
        }else{
            self.updateUser(self.user, self.user.id);
            console.log('User updated with id ', self.user.id);
        }
        self.reset();
    };
    self.reset = function(){
        self.user={id:null,username:'',address:'',email:''};
        $scope.myForm.$setPristine(); //reset Form
    };
	
}]);
/*
	var self=this;
	self.project={id:null,Name:'',Address:'',Email:''};
			$scope.submit = function(project) {
			alert("SUBMIT!");
				 RestService.createUser(project).then(
	                      function(){
	                    	  alert("Success in Con!!!");
	                      }, 
			              function(errResponse){
				             //  console.error('Error while creating User.');
	                    	  alert("ERROR in Con!!!");
	                      });
			};
			$scope.reset = function() {
				$scope.project = {
					Name : "",
					Address : "",
					Email : ""
				}
			};
			$scope.reset();

		} ]);
*/