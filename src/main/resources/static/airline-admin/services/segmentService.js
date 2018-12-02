var app = angular.module('app');

app.factory('segmentService', function($http) {
	
    var getSegments = function(id) {    
     	return $http.get('/segment/get/' + id).then(function(response) {
             return response.data;
         });
     }
    
    var save = function(airplaneId, data) {
     	return $http.post('/segment/save/' + airplaneId, data).then(function(response) {
            return response.data;
        });
    }

     return {
    	 getSegments: getSegments,
    	 save: save
       };
	
});