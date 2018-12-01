var app = angular.module('app');

app.factory('destService', function($http) {
	
    var getDestByCode = function(code) {    
     	return $http.get('/dest/' + code).then(function(response) {
             return response.data;
         });
     }
	
    var getDestByAirlineId = function(id) {    
     	return $http.get('/dest/airline/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var getNotAddedDestByAirlineId = function(id) {    
     	return $http.get('/dest/airline/not-added/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var addDest = function(dest) {
      	return $http.post('/dest/add/', dest).then(function(response) {
            return response.data;
        });
     }
    
    var addDestToAirline = function(destCode, airlineId) {
      	return $http.post('/dest/add-to-airline/' + id + '/' + destCode).then(function(response) {
            return response.data;
        });
     }
     
     return {
    	 getDestByAirlineId: getDestByAirlineId,
    	 getNotAddedDestByAirlineId: getNotAddedDestByAirlineId,
    	 getDestByCode: getDestByCode,
         addDest: addDest,
         addDestToAirline: addDestToAirline
       };
    
});