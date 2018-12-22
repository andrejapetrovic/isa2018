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
    
    var addDestToAirline = function(data) {
      	return $http.post('/dest/add-to-airline/', data).then(function(response) {
            return response.data;
        });
     }
     
    var removeDest = function(data) {
    	return $http.post('/dest/delete-from-airline/', data).then(function(response){
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
    	 getNotAddedDestByAirlineId: getNotAddedDestByAirlineId,
    	 getDestByCode: getDestByCode,
         addDestToAirline: addDestToAirline,
         removeDest: removeDest,
         filter: filter
       };
    
});