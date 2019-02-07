var app = angular.module('app');

app.controller('addBranchOfficerCtrl', function($scope, $http, $window, $stateParams, rentACarService){
	
	$scope.saveBranchOffice = function() {
		$scope.addBranchOffice.id = $stateParams.id;
		
		$("#addBranchOffice").prop('href', '#!/branches/'+$stateParams.id);
		
		console.log($scope.addBranchOffice);
		rentACarService.addBranchOffice($scope.addBranchOffice).then(function(data){
			console.log(data);
		});
	}	
});


