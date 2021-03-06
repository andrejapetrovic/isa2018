var app = angular.module('app');

app.factory('airlineService', function($http) {
	
    var getAirline = function(id) {    
     	return $http.get('/airline/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var getAirlineProfile = function(id) {    
     	return $http.get('/airline/profile/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var updateAirline = function(updateData) {
      	return $http.post('/airline/update/' , updateData).then(function(response) {
            return response.data;
        });
     }
     
    var getAll = function() {
    	return $http.get('/airline/get-all').then(function(response){
    		return response.data;
    	});
    }
    
    var addPrices = function(data) {
      	return $http.post('/prices/add/', data).then(function(response) {
            return response.data;
        });
    }
    
    
     return {
    	 getAirline: getAirline,
    	 getAirlineProfile: getAirlineProfile,
         updateAirline: updateAirline,
         getAll: getAll,
         addPrices: addPrices
       };
    
});