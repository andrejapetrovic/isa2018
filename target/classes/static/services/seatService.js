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
    
    var getByFlight = function(id) {    
     	return $http.get('/flight-seat/get/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var deleteSeat = function(data) {    
     	return $http.post('/flight-seat/delete', data).then(function(response) {
             return response.data;
         });
     }
    
    var addSeat = function(data) {    
     	return $http.post('/flight-seat/admin-add', data).then(function(response) {
             return response.data;
         });
     }
    
    var resSeat = function(data) {    
     	return $http.post('/flight-seat/admin-add-res', data).then(function(response) {
             return response.data;
         });
     }
    var fastResSeat = function(data) {    
     	return $http.post('/flight-seat/admin-add-fast-res', data).then(function(response) {
             return response.data;
         });
     }
    
     return {
    	 getFlightSeats: getFlightSeats,
    	 getFlightSeatsByIds: getFlightSeatsByIds,
    	 getByFlight: getByFlight,
    	 deleteSeat: deleteSeat,
    	 addSeat: addSeat,
    	 resSeat: resSeat,
    	 fastResSeat: fastResSeat
       };
    
});