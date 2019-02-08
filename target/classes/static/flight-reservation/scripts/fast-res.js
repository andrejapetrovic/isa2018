var app = angular.module('app');

app.controller('fastResCtrl', function($scope, $http, $window, $stateParams, reservationService, $state) {
	
	reservationService.getFast().then(function(data){
		$scope.fasts = data;
		
		$scope.formatDate = function(date) {
			var d = new Date(date);
			var retVal = d.toString();
			retVal = retVal.substring(0, retVal.indexOf(':')+3);
			return retVal;
		}
		$scope.parseType = function(aircraftType) {
			return aircraftType.split('_').join(' ');
		}
		
		$scope.makeRes = function(fs){
			var passenger = $scope.loggedUser;
			if(passenger == null) {
				alert("You have to be logged in to make a reservation");
				return;
			}
			passenger['flightSeatId'] = fs.id;
			console.log(passenger);
			reservationService.submitReservation([passenger]).then(function(data){
				console.log(data);
				$scope.fasts = $scope.fasts.filter(f => f.id != data[0].flightSeat[0].id);
			}, function(err){
				console.log(err);
				if(err.status == "400") {
					alert("You have to be logged in to make a reservation");
				}
			});
		}
		
	});
	
});