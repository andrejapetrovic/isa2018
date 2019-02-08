var app = angular.module('app');

app.controller('carListCtrl', function($scope, $http, $window, $stateParams, carListService){
	
	var id = $stateParams.id;
	
	carListService.getAll().then(function(data){
		$scope.cars = data;
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
	
	$scope.carType = {
		    availableOptions: [
		      {id: '1', name: 'Small_Economy'},
		      {id: '2', name: 'Medium_Size'},
		      {id: '3', name: 'Full_Size'},
		      {id: '4', name: 'Automatic'},
		      {id: '5', name: 'SUV_Convertible'},
		      {id: '6', name: 'Stationwagon_Minibus'},
		      {id: '7', name: 'Prestige_Cars'}
		      ],
		    selectedType: {id: '3', name: 'Full_Size'} //This sets the default value of the select in the ui
	};
	
	
	$scope.SaveCar = function() {
		$scope.addCar.id = $stateParams.id;
		
		$("#addCar").prop('href', '#!/carList/'+$stateParams.id);
		
		$scope.addCar.carType = $scope.carType.selectedType.name;
		carListService.addCar($scope.addCar).then(function(data){
			console.log(data);
		});
	}
	
});