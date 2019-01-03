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
    
    var filterNonAddedByAirline = function(id, input) {    
     	return $http.get('/dest/airline/not-added/' + id + '/' + input).then(function(response) {
             return response.data;
         });
     }
    
    var addDestToAirline = function(data) {
      	return $http.post('/dest/add-to-airline/', data).then(function(response) {
            return response.data;
        });
     }
     
    var removeDest = function(destId, airlineId) {
    	return $http.delete('/dest/delete-from-airline/' + destId + '/' + airlineId).then(function(response){
    		return response.data;
    	})
    }
    
    var filter = function(input) {
    	return $http.get('dest/filter/' + input).then(function(response) {
    		return response.data;
    	});
    }
    
     return {
    	 getDestByAirlineId: getDestByAirlineId,
    	 filterNonAddedByAirline: filterNonAddedByAirline,
    	 getDestByCode: getDestByCode,
         addDestToAirline: addDestToAirline,
         removeDest: removeDest,
         filter: filter
       };
    
});