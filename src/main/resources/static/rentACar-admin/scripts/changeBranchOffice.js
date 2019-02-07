var app = angular.module('app');

app.controller('changeBranchOfficeCtrl', function($scope, $http, $window, $stateParams, rentACarService){
	
	var id = $stateParams.id;
	
	rentACarService.getOneBranchOffice(id).then(function(data){
		$scope.changeBranchOffice = data;
	});
	
	$scope.saveChangeBranchOffice =  function() {
		$scope.changeBranchOffice.id = id;
		console.log($scope.changeBranchOffice);
		rentACarService.updateBranchOffice($scope.changeBranchOffice).then(function(data){
			console.log(data);
		});
		
	}
	
	$scope.goBack =	function() {
		$window.history.back();
	}
	
});