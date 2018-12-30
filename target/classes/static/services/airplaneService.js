var app = angular.module('app');

app.factory('airplaneService', function($http) {
	
	var getAirplane = function(id) {
     	return $http.get('/airplane/'+ id).then(function(response) {
            return response.data;
        });
	}
	
    var getAirplaneByOwner = function(id) {    
     	return $http.get('/airplane/owner/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var addAirplane = function(data) {    
     	return $http.post('/airplane/add/', data).then(function(response) {
             return response.data;
         });
     }
    
    var deleteAirplane = function(data) {    
     	return $http.post('/airplane/delete/', data).then(function(response) {
             return response.data;
         });
     }
    
     return {
    	 getAirplane: getAirplane,
    	 getAirplaneByOwner: getAirplaneByOwner,
    	 addAirplane: addAirplane,
    	 deleteAirplane: deleteAirplane 
       };
    
});