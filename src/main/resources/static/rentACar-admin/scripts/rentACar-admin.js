var app = angular.module('app');

app.controller('rentACarCtrl', function($scope, $http, $window, $stateParams, rentACarService){
	
	
	$scope.data = {
		    availableOptions: [
		      {id: '1', name: 'Sort by name [A - Z]', value :'nameOfRentACar'},
		      {id: '2', name: 'Sort by name [Z - A]', value :'-nameOfRentACar'},
		      {id: '3', name: 'Sort by Adress [A - Z]', value :'address'},
		      {id: '4', name: 'Sort by Adress [Z - A]', value :'-address'},
		      {id: '5', name: 'Sort by rating descending', value: 'rating'},
		      {id: '6', name: 'Sort by rating ascending',  value: '-rating'}
		    ],
		    selectedOption: {id: '3', name: 'Sort by Adress [A - Z]', value :'nameOfRentACar'} //This sets the default value of the select in the ui
	};

	$scope.SaveRentACar = function() {
		rentACarService.addRentACar($scope.addRentACar).then(function(data){
			console.log(data);
		});
	}
	
	rentACarService.getAll().then(function(data){
		$("#rentACar-addCar-link").addClass("hidden");
		$("#rentACar-branchOffice-link").addClass("hidden");
		$("#rentACar-cars-link").addClass("hidden");
		$scope.services = data;
	});

	
});


