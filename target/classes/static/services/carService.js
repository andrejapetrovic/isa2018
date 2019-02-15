var app = angular.module('app');

app.factory('carListService', function($http) {
	
	var addCar = function(data) {
      	return $http.post('/car/add/' , data).then(function(response) {
            return response.data;
        });
	}
	
	
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
	 
	 var getCarFromS = function(id) {
	    	return $http.get('/car/getCarsFromService/'+id).then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var getOneCar = function(id) {
	    	return $http.get('/car/getOne/'+id).then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var updateCar = function(updateData) {
	      	return $http.post('/car/update/' , updateData).then(function(response) {
	            return response.data;
	        });
	  }
	 
	 var deleteCar = function(carId, branchOfficeId) {    
	     	return $http.delete('/car/delete/' + carId + '/' +  branchOfficeId).then(function(response) {
	             return response.data;
	         });
	 }
    
     return {
    	 getAll: getAll,
    	 getCars: getCars,
    	 getOneCar: getOneCar,
    	 updateCar: updateCar,
    	 deleteCar: deleteCar,
    	 addCar : addCar,
    	 getCarFromS : getCarFromS
       };
    
});