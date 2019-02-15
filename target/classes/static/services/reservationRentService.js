var app = angular.module('app');

app.factory('resRentService', function($http) {
	
    var findRent = function(data) {    
    	
    	console.log(data);
    	
     return $http.post('/reservationRent/search/', data).then(function(response) {
             return response.data;
         });
     }
    
    
	var findCars = function(data) {    
	    	
    	console.log(data);
    	
     return $http.post('/reservationRent/searchCar/', data).then(function(response) {
             return response.data;
         });
     }
    
	
	
	var addReservation = function(data) {    
    	
    	console.log(data);
    	
     return $http.post('/reservationRent/add/', data).then(function(response) {
             return response.data;
         });
     }
	
   
     return {
    	 findRent: findRent,
    	 findCars : findCars,
    	 addReservation : addReservation
       };
    
});