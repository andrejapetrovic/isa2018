var app = angular.module('app');

app.controller('findRentCtrl', function($scope, $http, $window, $stateParams, rentACarService) {
	rentACarService.getAll().then(function(data){
		$scope.services = data;
	});
});