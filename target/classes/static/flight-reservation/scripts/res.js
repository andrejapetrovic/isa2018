var app = angular.module('app');
app.controller('resCtrl', function($scope, $http, $window, $location,
		 $state, $window, $stateParams, seatService, reservationService) {
	if($scope.loggedUser == null){
		alert("You have to be logged in to make a reservation");
		return;
	}
	
	var params = $location.url().split('?')[1];
	var infantNum = params.includes('infants') ? $stateParams.infants : 0;
	console.log(params);
	$scope.roundTrip = false;
	$scope.selectingSeats = "";
	if(params.includes("retFl=")) $scope.roundTrip = true;
	seatService.getFlightSeatsByIds(params).then(function(data){
		console.log(data);
		var flightSeats = data[0];
		if($scope.roundTrip) {
			$scope.retFlight = data[1][0].flight;
			$scope.retFlights = data[1];
			$scope.selectSeatModal = {
					user: "", flight: $scope.retFlight, nonSelectedSeats: $scope.retFlights
			}
			console.log($scope.selectSeatModal);
		}
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
		
		var prices = data[0].map(fs => fs.flight.oneWayPrice);
		$scope.totalPrice = 0;
		prices.forEach(price => $scope.totalPrice += price);
		$scope.pricing = $scope.flightInfo[0].airline.pricelist;
		console.log($scope.pricing);
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
				
		});
		console.log($scope.passengers);
		reservationService.submitReservation($scope.passengers).then(function(data){
			console.log(data);
			$state.go('user');
		}, function(err){
			console.log(err.data.msg);
			$scope.err = "Unable to make reservation, " + err.data.msg;
		});
	}
	
	
	$scope.selectSeat = function(idx){
		$scope.selectSeatModal.user = idx;
	}
	$scope.addRetSeat = function(fs, index) {
		if($scope.passengers[index].returnTicket == false) {
			$scope.passengers[index].returnTicket = true;
			$scope.totalPrice += $scope.retFlight.returnPrice;
		} else {
			$scope.selectSeatModal.nonSelectedSeats.push($scope.passengers[index]['retFl']);
		}
		$scope.passengers[index]['retFl'] = fs;
		$scope.selectSeatModal.nonSelectedSeats = 
			$scope.selectSeatModal.nonSelectedSeats.filter(seat => seat != fs);
	}
	$scope.removeSeat = function(passenger){
		$scope.selectSeatModal.nonSelectedSeats.push(passenger['retFl']);
		$scope.totalPrice -= $scope.retFlight.returnPrice;
		passenger.returnTicket = false;
		passenger['retFl'] = undefined;
	}
});

