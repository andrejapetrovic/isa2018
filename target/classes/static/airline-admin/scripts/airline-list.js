var app = angular.module('app');

app.controller('airlineListCtrl', function($scope, $http, $window, $stateParams, airlineService) {
	
	airlineService.getAll().then(function(data){
		$scope.airlines = data;
	});
	
});