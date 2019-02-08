var app = angular.module('app');

app.controller('branchOfficeCarList', function($scope, $http, $window, $stateParams, carListService){
	
	var id = $stateParams.id;
	
	carListService.getCars(id).then(function(data){
		$scope.cars = data;
		
		$("#rentACar-addCar-link").removeClass("hidden");
		$("#rentACar-addCar-link").prop('href', '#!/addCar/'+id)
		console.log(data);
	});
	
	$scope.deleteCar = function(carId,branchOfficeId) {	
		console.log(carId);
		console.log(branchOfficeId);
		carListService.deleteCar(carId,branchOfficeId).then(function(data){
			console.log(data);
		});
		
		$window.location.reload();
	}
	
});