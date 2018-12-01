var app = angular.module('app');

app.factory('airlineService', function($http) {
	
    var getAirline = function(name) {    
     	return $http.get('/airline/' + name).then(function(response) {
             return response.data;
         });
     }
    
    var updateAirline = function(updateData, id) {
      	return $http.post('/airline/update/' + id, updateData).then(function(response) {
            return response.data;
        });
     }
     
     return {
    	 getAirline: getAirline,
         updateAirline: updateAirline
       };
    
});