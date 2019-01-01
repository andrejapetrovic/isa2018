var app = angular.module('app');

app.factory('cabinService', function($http) {
	
    var addCabin = function(data) {    
     	return $http.post('/cabin/add/', data).then(function(response) {
             return response.data;
         });
     }
    
     return {
    	 addCabin: addCabin
       };
    
});