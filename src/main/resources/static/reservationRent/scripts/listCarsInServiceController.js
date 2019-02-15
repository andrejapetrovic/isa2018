var app = angular.module('app');

app.controller('listCarsInSerCtrl', function($scope, $http, $window, $stateParams, rentACarService,resRentService, carListService) {
	
	var id = $stateParams.id;

	
	carListService.getCarFromS(id).then(function(data){
		$scope.cars = data;
		
	});
	
	
	
	
	var tripDatePicker = new datePicker({
	    start:  document.getElementsByClassName('startDate'),
	    end:    document.getElementsByClassName('endDate'),
	    months: 1,
	});
	
	
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
	
	$scope.findCar = function() {
		var searchParams = {
				
				pickUpLocation : $scope.serchParams.pickUpLocation,
				dropOfLocation : $scope.serchParams.dropOffLocation,
				
				pickUpDateTime : $("#pickUp").val().split(' ').join('-') +" " + $scope.serchParams.pickUpTime,
				dropOfDateTime : $("#dropOf").val().split(' ').join('-') + " " + $scope.serchParams.dropOffTime,
				
				passengers     : $scope.serchParams.passengers,
				
				carType        : $scope.data.selectedOption.name,
			
			};
		
		resRentService.findCars(searchParams).then(function(data){
			console.log(data);
			$scope.foundCars = data;
		});
		
		console.log(searchParams);	
	}
	
	
	
	$scope.BookIt = function(modelNum) {
		var bookParams = {
				
				pickUpLoc : $scope.serchParams.pickUpLocation,
				dropOfLoc : $scope.serchParams.dropOffLocation,
				
				pickUpDate : $("#pickUp").val().split(' ').join('-') +" " + $scope.serchParams.pickUpTime,
				dropOfDate : $("#dropOf").val().split(' ').join('-') + " " + $scope.serchParams.dropOffTime,
				
				passengers     : $scope.serchParams.passengers,
				
				carType        : $scope.data.selectedOption.name,
				modelNumber : modelNum,
				userId: $scope.loggedUser.id
			};
		
		resRentService.addReservation(bookParams).then(function(data){
			console.log(data);
		});
		
		console.log(bookParams);	
	}
	
		
});