var app = angular.module('app');
app.controller('resCtrl', function($scope, $http, $window, $location,
		 $state, $window, $stateParams, seatService, reservationService) {
	var params = $location.url().split('?')[1];
	var infantNum = params.includes('infants') ? $stateParams.infants : 0;
	console.log(params);
	$scope.roundTrip = false;
	if(params.includes("retFl=")) $scope.roundTrip = true;
	seatService.getFlightSeatsByIds(params).then(function(data){
		console.log(data);
		var flightSeats = data[0];
		if($scope.roundTrip) $scope.retFlights = data[1];
		var passNum = flightSeats.length;
		$scope.passengers = [];
		for (var i = 0; i < passNum; i++) {
			$scope.passengers.push({
				passId : 'Passenger ' + (i+1),
				flightSeat : flightSeats[i],
				flightType : "One_way",
				returnTicket: false,
			});
		}
		$scope.flightInfo = [];
		var flightInfo = data[0][0].flight;
		flightInfo['type'] = 'Flight';
		$scope.flightInfo.push(flightInfo);
		if(params.includes('retSeats=')) {
			var retFlightInfo = data[1][0].flight;
			retFlightInfo['type'] = 'Return flight';
			$scope.flightInfo.push(data[1][0].flight);
		}
	});
	
	$scope.fillUserData = function(passenger) {
		$.extend(passenger, $scope.loggedUser);
	}
	
	$scope.parseDate = function(date1, date2) {
		var d1 = new Date(date1);
		var d2 = new Date(date2);
		var ret1 = d1.toString().split(':');
		ret1 = ret1[0] + ":" + ret1[1];
		var ret2;
		if(d1.getDate() == d2.getDate()) {
			ret2 = d2.toString().split(" ");
			ret2 = ret2[4].split(":");
			ret2 = ret2[0] + ":" + ret2[1];
		} else {
			ret2 = d2.toString().split(':');
			ret2 = ret2[0] + ":" + ret2[1];
		}
		return ret1 + " - " + ret2;
	} 
	
	$scope.makeRes = function() {
		angular.forEach($scope.passengers, function(passenger){
				passenger['dateOfBirth'] = passenger.year + "-" + passenger.month + "-" + passenger.day;
				passenger['flightSeatId'] = passenger.flightSeat.id;
				if(passenger.retFl != null) {
					passenger['retFlightSeatId'] = passenger.retFl.id;
				}
				delete passenger['flightSeat'];
				delete passenger['retFl'];
				
		});
		console.log($scope.passengers);
		reservationService.submitReservation($scope.passengers).then(function(data){
			console.log(data);
		});
	}
	
	$scope.selectRetSeat = function(index, selected) {
		$scope.passengers[index].returnTicket = true;
		$scope.passengers[index]['retFl'] = selected;
	}
	
});

