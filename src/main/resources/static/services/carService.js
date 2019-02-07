var app = angular.module('app');

app.factory('carListService', function($http) {
	
	 var getAll = function() {
	    	return $http.get('/car/all').then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var getCars = function(id) {
	    	return $http.get('/car/'+id).then(function(response){
	    		return response.data;
	    	});
	 }
    
     return {
    	 getAll: getAll,
    	 getCars: getCars
       };
    
});