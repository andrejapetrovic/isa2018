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
     
     var login = function(data){
    	 return $http.post('/user/login/', data).then(function(response){
    		return response.data; 
    	 });
     }
     
     var reg = function(data){
    	 return $http.post('/user/signup/', data).then(function(response){
    		return response.data; 
    	 });
     }
     
     var activation = function(code){
    	 return $http.post('/user/activation/' + code).then(function(response){
    		return response.data; 
    	 });
     }
     
     return {
    	 getUser: getUser,
         updateUser: updateUser,
         login: login,
         reg: reg,
         activation: activation
       };
});