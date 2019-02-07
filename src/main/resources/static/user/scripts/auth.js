var app = angular.module('app');

app.controller('rootCtrl', function($scope, $http, $window, userService) {
    $scope.logFunc = function () {
    	userService.login($scope.login).then(function(user){
    		$window.location.reload();
    	}, function (err) {
    		$scope.resp = err.data.msg;
    	});
    }
    
    $scope.regFunc = function () {
    	console.log($scope.reg);
    	userService.reg($scope.reg).then(function(data){
    		$('.reg-form').hide();
    		$scope.success = data.msg;
    		console.log(data);
    	}, function (err) {
    		$scope.resp = err.data.msg;
    		console.log(err)
    	});
    }
    
    userService.getLogged().then(function(user){
    	$scope.loggedUser = user;
    	console.log(user);
    	$scope.profileLink = user.name;
    	$("#logout").removeClass("hidden");
    	$("#user-link").removeClass("hidden");
    	if (user.roles.includes('AirlineAdmin')) 
    		$("#airline-admin-link").removeClass("hidden");
    	else if( user.roles.includes('RentACarAdmin'))
    	{
    		//
    		$("#rentACar-add-link").removeClass("hidden");
    		$("#rentACar-list-link").removeClass("hidden");
    		
    		
    	}
    }, function (err) {
    	console.log(err);
    	$("#log-btn").removeClass("hidden");
    	$("#reg-btn").removeClass("hidden");
	});
      
  });

app.controller('activationCtrl', function($scope, $stateParams, $http, userService) {
	var token = $stateParams.code;
	console.log(token);
	$scope.activated = "verification status"
    userService.activation(token).then(function(data){
    		console.log(data);
    		$scope.activated = data;
    }, function (err) {
		$scope.activated = err.data;
	});
});