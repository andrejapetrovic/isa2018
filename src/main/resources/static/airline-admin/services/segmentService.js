var app = angular.module('app');

app.factory('segmentService', function($http) {
	
    var getSegments = function(id) {    
     	return $http.get('/segment/get/' + id).then(function(response) {
             return response.data;
         });
     }

     return {
    	 getSegments: getSegments
       };
	
});