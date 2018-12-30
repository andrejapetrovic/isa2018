var app = angular.module('app');
app.controller('searchFlightCtrl', function($scope, $http, $window, $state, destService, flightService) {
	$scope.fromDests = [];
	$scope.toDests = [];
	var maxTravelers = 9;
	
	$scope.classes = ["Economy", "Premium economy", "Bussines", "First"];
	$scope.types = ["Round-trip", "One-way"];
	$scope.passengers = [{type: 'Adults', num: 1}, {type: 'Children', num: 0}, {type: 'Infants', num: 0}];
	$scope.fclass = $scope.classes[0];
	$scope.ftype = $scope.types[0];
	angular.forEach($scope.passengers, function(value,index){
		if(value.type == 'Adults')
			$scope.passengers[index].num = 1;
		else
			$scope.passengers[index].num = 0;
		$scope.numOfPassengers = 1;
		$scope.sufix = " Adult"
	});
	$scope.incrementPassengers = function(passenger) {
		++passenger.num;
		$scope.numOfPassengers++;
		$scope.sufix = ' Travelers';
	}
	$scope.decrementPassengers = function(passenger) {
		if(passenger.type == 'Adults' && passenger.num == 1) {
			return;
		}
		if(passenger.num > 0){
			$scope.numOfPassengers--;
			--passenger.num;
		}
		if($scope.numOfPassengers == 1)
			$scope.sufix = ' Adult';
	}
	
	
	$("#from").on('keyup paste click', function () {
		if($("#from").val() == "") return;
	    destService.filter($scope.from).then(function(data){
	    	$scope.fromDests = filterData(data, $scope.fromDests);
	    });
	});
	
	$("#to").on('keyup paste click', function (e) {
		if($("#to").val() == "") return;
	    destService.filter($scope.to).then(function(data){
	    	$scope.toDests = filterData(data, $scope.toDests);
	    	console.log($scope.toDests);
	    });
	    //$(this).select();
	});
	
	$scope.swapFields = function(){
		var temp = $scope.to;
		$scope.to = $scope.from;
		$scope.from = temp;
	}

	var tripDatePicker = new datePicker({
	    start:  document.getElementsByClassName('startDate'),
	    end:    document.getElementsByClassName('endDate'),
	    months: 2,
	});
	
	$scope.search = function() {
		console.log($scope.fclass)
		var searchParams = {
			fromDest: $scope.from.substring(1,4),
			toDest: $scope.to.substring(1,4),
			departDate: $("#depart").val().split(' ').join('-') 
		};
		if($scope.ftype == "Round-trip"){
			searchParams["returnDate"] = $("#return").val().split(' ').join('-');
		}
		searchParams["fclass"] = $scope.fclass.split(' ').join('_');
		searchParams["ftype"] = $scope.ftype.split('-').join('_');
			if($scope.numOfPassengers > 1) {
				angular.forEach($scope.passengers, function(value,index){
					if(value.type == 'Adults')
						searchParams["adults"] =  value.num;
					else if(value.type == 'Children')
						searchParams["children"] = value.num;
					else if(value.type == 'Infants')
						searchParams["infants"] = value.num;
				});
			}
		console.log(searchParams);
		//$window.location.href = '#!/flights/' + searchParams;
		$state.go(".searchFlight", searchParams); 
	}
	
	$('.search-ft').on('change', function (e) {
	    var valueSelected = this.value;
	    if(valueSelected == 'string:One-way'){
	    	$(".ret-date-div").hide();
	    }
	    else if(valueSelected == 'string:Round-trip' && $(".ret-date-div").is(":hidden")) {
	    	$(".ret-date-div").show();
	    }
	});
});