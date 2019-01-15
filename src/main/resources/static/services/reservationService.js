var app = angular.module('app');

app.factory('reservationService', function($http) {
	
    var submitReservation = function(data) {    
     	return $http.post('/reservation/submit/', data).then(function(response) {
             return response.data;
         });
     }
    
     return {
    	 submitReservation : submitReservation 
       };
    
});