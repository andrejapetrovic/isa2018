var app = angular.module('app');

app.factory('airlineService', function($http) {
	
    var getAirline = function(id) {    
     	return $http.get('/airline/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var updateAirline = function(updateData, id) {
      	return $http.post('/airline/update/' + id, updateData).then(function(response) {
            return response.data;
        });
     }
     
     return {
    	 getUser: getUser,
         updateUser: updateUser
       };
    
)};