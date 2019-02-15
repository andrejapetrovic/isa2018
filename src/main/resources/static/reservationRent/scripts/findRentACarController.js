var app = angular.module('app');

app.controller('findRentCtrl', function($scope, $http, $window, $stateParams, rentACarService,resRentService) {
	
	
	rentACarService.getAll().then(function(data){
		$scope.services = data;
	});
	
	
	var tripDatePicker = new datePicker({
	    start:  document.getElementsByClassName('startDate'),
	    end:    document.getElementsByClassName('endDate'),
	    months: 1,
	});
	
	$scope.findRent = function() {
		var searchParams = {
				
				nameOrLoc: $scope.searchRent.nameOrLoc,
				pickUpDate: $("#pickUp").val().split(' ').join('-') ,
				dropOfDate: $("#dropOf").val().split(' ').join('-')
			};
		
		console.log(searchParams);
		
		resRentService.findRent(searchParams).then(function(data){
			console.log(data);
			$scope.foundRent = data;
		});
		
	}	
		
		
		
});