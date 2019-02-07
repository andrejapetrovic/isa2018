var app = angular.module('app');

app.controller('carListCtrl', function($scope, $http, $window, $stateParams, carListService){
	
	var id = $stateParams.id;
	
	carListService.getAll().then(function(data){
		$scope.cars = data;
		console.log(data);
	});
	
	
});