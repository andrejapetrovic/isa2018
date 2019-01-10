var app = angular.module('app');

app.controller('rootCtrl', function($scope, $http, $window, userService) {
    $scope.logFunc = function () {
    	console.log($scope.user);
    	userService.login($scope.user).then(function(user){
    		$window.location.reload();
    	}, function (err) {
    		$scope.resp = err.data.msg;
    	});
    }
    
    userService.getLogged().then(function(user){
    	console.log(user);
    	$scope.profileLink = user.name;
    	$('#logout').show();
		$('#profile-link').show();
		$('#log-btn').hide();
		$('#reg-btn').hide();
    }, function (err) {
    	console.log(err);
    	$('#logout').hide();
		$('#profile-link').hide();
		$('#log-btn').show();
		$('#reg-btn').show();
	});
      
  });

app.controller('regCtrl', function($scope, $http, $window, $state, userService) {
	  
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