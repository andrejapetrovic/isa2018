var app = angular.module('app');

app.factory('friendService', function($http) {
	
    var friends = function(id) {    
     	return $http.get("/user/get/friends/" + id).then(function(response) {
             return response.data;
         });
     }
     var nonFriends = function(id) {    
     	return $http.get("/user/get/non/friends/" + id).then(function(response) {
             return response.data;
         });
     }
     var pending = function(id) {    
      	return $http.get("/friendReq/get/pending/requests/" + id).then(function(response) {
              return response.data;
          });
      }
     
     var sendRequest = function(senderId, reciverId) {
    	var reqObject = {senderId: senderId, reciverId: reciverId};
      	return $http.post("/friendReq/send/", reqObject).then(function(response) {
            return response.data;
        });
     }
     
     var acceptReq = function(id) {    
       	return $http.post("/friendReq/accept/" + id).then(function(response) {
               return response.data;
           });
       }
     
     var declineReq = function(id) {    
        	return $http.post("/friendReq/deny/" + id).then(function(response) {
                return response.data;
            });
        }
     
     return {
         getFriends: friends,
         getNonFriends: nonFriends,
         getPendingRequests: pending,
         sendRequest: sendRequest,
         acceptReq: acceptReq,
         declineReq: declineReq
       };
});