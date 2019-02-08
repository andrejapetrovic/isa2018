var app = angular.module('app');

app.factory('reservationService', function($http) {
	
    var submitReservation = function(data) {    
     	return $http.post('/reservation/submit/', data).then(function(response) {
             return response.data;
         });
     }
    var getReservations = function(email) {    
     	return $http.get('/reservation/get/'+email).then(function(response) {
             return response.data;
         });
     }
    
    var getFast = function() {    
     	return $http.get('/reservation/get/fast').then(function(response) {
             return response.data;
         });
     }
     return {
    	 submitReservation : submitReservation,
    	 getReservations : getReservations,
    	 getFast: getFast
       };
    
});