var app = angular.module('app');
app.controller('searchFlightCtrl', function($scope, $http, $window, $state, destService, flightService) {
	var searchParams = 
		"?fromDest=BEG"
		+"&toDest=VIE"
		+"&departDate=29-December-2018"
		+"&returnDate=30-December-2018"
	console.log(searchParams);
	flightService.search(searchParams).then(function(data){
		console.log(data);
		$scope.flights = data;
	});
	
	
	$scope.fromDests = [];
	$scope.toDests = [];
	
	$("#from").on('keyup paste click', function () {
		if($("#from").val() == "") return;
	    destService.filter($scope.search.from).then(function(data){
	    	$scope.fromDests = filterData(data, $scope.fromDests);
	    });
	});
	
	$("#to").on('keyup paste click', function (e) {
		if($("#to").val() == "") return;
	    destService.filter($scope.search.to).then(function(data){
	    	$scope.toDests = filterData(data, $scope.toDests);
	    	console.log($scope.toDests);
	    });
	    //$(this).select();
	});
	
	$scope.swapFields = function(){
		var temp = $scope.search.to;
		$scope.search.to = $scope.search.from;
		$scope.search.from = temp;
	}

	var tripDatePicker = new datePicker({
	    start:  document.getElementsByClassName('startDate'),
	    end:    document.getElementsByClassName('endDate'),
	    months: 2,
	});
	
	/*flightService.getCriteria().then(function(data){
		console.log(data);
		$scope.classes = {"Economy, Premium economy, Bussines, First"};
		$scope.types = {"Round-trip, One-way"};
		$scope.passengers = {{type: 'Adults', num: '1'}, {type: 'Children', num: '0'}, {type: 'Infants', num: '0'}};
		$scope.fclass = $scope.classes[1];
		$scope.ftype = $scope.types[1];
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
		
	});*/
	
	$scope.search = function() {
		var searchParams = {
				dests: $scope.search.from.substring(1,4).concat('-').concat($scope.search.to.substring(1,4)),
				departDate: $("#depart").val().split(' ').join('-'),
				returnDate: $("#return").val().split(' ').join('-'),
				fclass: $scope.fclass.name,
				ftype: $scope.ftype.name,
				passNum: $scope.numOfPassengers
		}
		//console.log(searchParams);
		//$window.location.href = '#!/flights/' + searchParams;
		$state.go(".searchFlight", searchParams); 
	}
});