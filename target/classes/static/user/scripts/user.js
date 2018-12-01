var app = angular.module('app');

app.controller('uCtrl', function($scope, $http, $window, $routeParams, friendService, userService) {
  
	var id = $routeParams.id;
	
	userService.getUser(id).then(function(data) {
		console.log(data);
		$scope.user = data;
	});
	
      $scope.updFunc = function () {
          var user = JSON.parse(JSON.stringify($scope.user));
          userService.updateUser(user).then(function(data) {
              $scope.user = data;
              $window.location.href = '#!/user/' + user.id;
           });
      }
      
	friendService.getNonFriends(id).then(function(data) { 
		console.log(data);
		$scope.nonFriends = data;
	  });
	
	friendService.getFriends(id).then(function(data) { 
		console.log('friends');
		console.log(data);
		$scope.friends = data;
	  });
	
	friendService.getPendingRequests(id).then(function(data) { 
		console.log(data);
		$scope.pending = data;
	  });
	
	$scope.sendReq = function(friendId) {
		friendService.sendRequest(id, friendId).then(function(data) { 
			console.log(data);
		  });
	}
	
	$scope.acceptFriendReq = function(reqId) {
		friendService.acceptReq(reqId).then(function(data) { 
			console.log(data);
			$scope.friends.push(data);
			$scope.pending = $scope.pending.filter( req => req.sender.id == reqId );
			$scope.friends = $scope.friends.filter(f => $scope.pending.filter( req => req.sender.id == f.id));
		  });
	}

	$scope.declineFriendReq = function(reqId) {
		friendService.declineReq(reqId).then(function(data) { 
			console.log(data);
			//$scope.nonFriends.push(data.sender);
			$scope.pending = $scope.pending.filter( req => req.sender.id === reqId );
		  });
	}
      
  });