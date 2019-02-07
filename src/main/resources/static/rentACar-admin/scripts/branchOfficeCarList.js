var app = angular.module('app');

app.controller('branchOfficeCarList', function($scope, $http, $window, $stateParams, carListService){
	
	var id = $stateParams.id;
	
	carListService.getCars(id).then(function(data){
		$scope.cars = data;
		console.log(data);
	});
	
});