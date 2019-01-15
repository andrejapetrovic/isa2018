var app = angular.module('app');

app.factory('seatService', function($http) {
	
    var getFlightSeats = function(params) {    
     	return $http.get('/flight-seat/get?' + params).then(function(response) {
             return response.data;
         });
     }
    
    var getFlightSeatsByIds = function(params) {    
     	return $http.get('/flight-seat/ids?' + params).then(function(response) {
             return response.data;
         });
     }
    
     return {
    	 getFlightSeats: getFlightSeats,
    	 getFlightSeatsByIds: getFlightSeatsByIds
       };
    
});