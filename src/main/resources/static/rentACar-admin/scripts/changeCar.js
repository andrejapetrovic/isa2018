var app = angular.module('app');

app.controller('changeCarCtrl', function($scope, $http, $window, $stateParams, carListService){
	
	var id = $stateParams.id;
	
	
	$scope.data = {
		    availableOptions: [
		      {id: '1', name: 'Small_Economy'},
		      {id: '2', name: 'Medium_Size'},
		      {id: '3', name: 'Full_Size'},
		      {id: '4', name: 'Automatic'},
		      {id: '5', name: 'SUV_Convertible'},
		      {id: '6', name: 'Stationwagon_Minibus'},
		      {id: '7', name: 'Prestige_Cars'}
		      ],
		    selectedOption: {id: '3', name: 'Full_Size'} //This sets the default value of the select in the ui
	};
	
	$scope.saveChangeCar = function() {
		$scope.changeCar.id = $stateParams.id;
		$scope.changeCar.carType = $scope.data.selectedOption.name;
		
		console.log($scope.changeCar);
		carListService.updateCar($scope.changeCar).then(function(data){
			console.log(data);
		});
		
	}	
	
	carListService.getOneCar(id).then(function(data){
		$scope.changeCar = data;
	});
	
	
	$scope.goBack =	function() {
		$window.history.back();
	}
});