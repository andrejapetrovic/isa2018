var app = angular.module('app');

app.factory('rentACarService', function($http) {
	
	 var addRentACar = function(data) {
	      	return $http.post('/RentACar/add/' , data).then(function(response) {
	            return response.data;
	        });
	 }
	 
	 var getAll = function() {
	    	return $http.get('/RentACar/get-all').then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var getBranches = function(id) {
	    	return $http.get('/branchOffice/'+id).then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var getOneBranchOffice = function(id) {
	    	return $http.get('/branchOffice/getOne/'+id).then(function(response){
	    		return response.data;
	    	});
	 }
	 
	 var addBranchOffice = function(data) {
	      	return $http.post('/branchOffice/add/' , data).then(function(response) {
	            return response.data;
	        });
	 }
	 
	 var updateBranchOffice = function(updateData) {
	      	return $http.post('/branchOffice/update/' , updateData).then(function(response) {
	            return response.data;
	        });
	  }
	 
	 var deleteBranchOffice = function(branchOfficeId, serviceId) {    
	     	return $http.delete('/branchOffice/delete/' + branchOfficeId + '/' +  serviceId).then(function(response) {
	             return response.data;
	         });
	     }
	 
	 
	 return {
		 addRentACar: addRentACar,
		 getAll: getAll,
		 getBranches: getBranches,
		 addBranchOffice : addBranchOffice,
		 updateBranchOffice : updateBranchOffice,
		 getOneBranchOffice : getOneBranchOffice,
		 deleteBranchOffice : deleteBranchOffice
	 };
	 
});