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
    
	var search = function(params) {
		return $http.get('/flight/search?' + params).then(function(response){
			return response.data;
		});
	}
	
    var addPrice = function(data) {    
     	return $http.post('/flight/add-price/', data).then(function(response) {
             return response.data;
         });
     }
    
    var prices = function(flightId, fclass) {
		return $http.get('/flight/prices/' + flightId + "/" + fclass).then(function(response){
			return response.data;
		});
    }
	
     return {
    	 getFlightsByAirline: getFlightsByAirline,
    	 addFlight: addFlight,
    	 deleteFlight: deleteFlight,
    	 search: search,
    	 addPrice: addPrice,
    	 prices: prices
     };
    
});