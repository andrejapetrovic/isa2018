var app = angular.module('app');

app.controller('branchOfficeListCtrl', function($scope, $http, $window, $stateParams, rentACarService){
	
	var id = $stateParams.id;
	
	rentACarService.getBranches(id).then(function(data){
		$scope.branches = data;
		$("#rentACar-branchOffice-link").removeClass("hidden");
		$("#rentACar-branchOffice-link").prop('href', '#!/addBranchOffice/'+id)
		$("#rentACar-cars-link").removeClass("hidden");
	});
	
	
	$scope.deleteBranchOffice = function(branchOfficeId,serviceId) {	
		console.log(serviceId);
		console.log(branchOfficeId);
		rentACarService.deleteBranchOffice(branchOfficeId,serviceId).then(function(data){
			console.log(data);
		});
		
		$window.location.reload();
	}
	
});