var app = angular.module('app');

app.factory('flightService', function($http) {
	
	var getFlightsByAirline = function(airlineId) {
     	return $http.get('/flight/get-by-airline/' + airlineId).then(function(response) {
            return response.data;
        });
	}
	
    var addFlight = function(data) {    
     	return $http.post('/flight/add/', data).then(function(response) {
             return response.data;
         });
     }
    
    var deleteFlight = function(data) {    
     	return $http.post('/flight/delete/', data).then(function(response) {
             return response.data;
         });
     }
    
	var getCriteria = function() {
     	return $http.get('/flight/get-aditional-criteria').then(function(response) {
            return response.data;
        });
	}
	
	var search = function(data) {
		return $http.post('/flight/search', data).then(function(response){
			return response.data;
		});
	}
	
     return {
    	 getFlightsByAirline: getFlightsByAirline,
    	 addFlight: addFlight,
    	 deleteFlight: deleteFlight,
    	 getCriteria: getCriteria
     };
    
});