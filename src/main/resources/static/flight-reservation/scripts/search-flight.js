var app = angular.module('app');
app.controller('searchFlightCtrl', function($scope, $http, $window, destService, flightService) {
	
	$scope.fromDests = [];
	$scope.toDests = [];
	
	$("#from").on('change keyup paste click', function () {
		if($("#from").val() == "") return;
	    destService.filter($scope.search.from).then(function(data){
	    	$scope.fromDests = filterData(data, $scope.fromDests);
	    });
	});
	
	$("#to").on('change keyup paste click', function () {
		if($("#to").val() == "") return;
	    destService.filter($scope.search.to).then(function(data){
	    	$scope.toDests = filterData(data, $scope.toDests);
	    	console.log($scope.toDests);
	    });
	});
	
	$scope.swapFields = function(){
		var btn = $("#swapDiv");
		var el1 = btn.prev("div");
		var el2 = btn.next("div");
		el1.before(el2);
		el2.after(btn);
	}

	var tripDatePicker = new datePicker({
	    start:  document.getElementsByClassName('startDate'),
	    end:    document.getElementsByClassName('endDate'),
	    months: 2,
	});
	
	flightService.getCriteria().then(function(data){
		console.log(data);
		$scope.classes = data.classes;
		$scope.types = data.types;
	});
});