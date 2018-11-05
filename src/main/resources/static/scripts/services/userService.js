var app = angular.module('app');

app.factory('userService', function($http) {
	
    var getUser = function(id) {    
     	return $http.get('/user/details/' + id).then(function(response) {
             return response.data;
         });
     }

     var updateUser = function(updateData) {
      	return $http.post('/user/update/', updateData).then(function(response) {
            return response.data;
        });
     }
     
     return {
    	 getUser: getUser,
         updateUser: updateUser
       };
});