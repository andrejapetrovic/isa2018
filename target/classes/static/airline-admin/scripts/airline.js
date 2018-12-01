var app = angular.module('app');

app.controller('airlineCtrl', function($scope, $http, $window, $routeParams, airlineService, destService) {
  
	var name = $routeParams.name;
	console.log(name);
	airlineService.getAirline(name).then(function(airline) {
		console.log(airline);
		$scope.airline = airline;
		
		destService.getDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.dests = dests;
		});
		
		destService.getNotAddedDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.otherDests = dests;
		});
	});

	$scope.addDest = function(destToAdd) {
		$scope.dests.push(destToAdd);
		$scope.otherDests = $scope.otherDests.filter(dest => dest != destToAdd);
	}
	
	$scope.removeDest = function(destToRemove) {
		$scope.otherDests.push(destToRemove);
		$scope.dests = $scope.dests.filter(dest => dest != destToRemove);
	}
	
    /*  $scope.updFunc = function () {
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
			$scope.pending = $scope.pending.filter( req => req.sender.id !== reqId );
		  });
	}

	$scope.declineFriendReq = function(reqId) {
		friendService.declineReq(reqId).then(function(data) { 
			console.log(data);
			//$scope.nonFriends.push(data.sender);
			$scope.pending = $scope.pending.filter( req => req.sender.id === reqId );
		  });
	}*/
      
  });