var app = angular.module('app');
app.controller('flightListCtrl', function($scope, $http, $window, destService, flightService, $location, $state, $window, $stateParams) {
	var params = $location.url().split('?')[1];
	console.log(params);
	flightService.search(params).then(function(data){
		console.log(data);
		$scope.flights = [];
		$scope.retFlights = [];
		$scope.sortOptions = ["Cheapest", "Highest price", "Quickest", "Slowest", "Earliest takeoff", "Latest takeoff"];
		var priceArr;
		
		if (data.flights.length == 1){
			$scope.flights = data.flights[0];
			$scope.ftype = 1;
			priceArr = data.flights[0].map(f => f.oneWayPrice);
		}
		else if (data.flights.length == 2){
			$scope.ftype = 2;
			$scope.sortOptions.concat(["Earliest takeoff (return)", "Latest takeoff (return)"]);
			priceArr = data.flights[0].map(f => f.returnPrice);
			data.flights[0].forEach(function(flight1){
				data.flights[1].forEach(function(flight2){
					if(flight1.airline.name == flight2.airline.name) {
						$scope.flights.push(Object.assign({}, flight1));
						$scope.retFlights.push(Object.assign({}, flight2));
					}
				});
			});
		}
		var lowestPrice = Math.min.apply(Math, priceArr);
		var highestPrice = Math.max.apply(Math, priceArr);
		
		$scope.formatTime = function(time) {
			var d = new Date(time);
			var retVal = d.toString().split(' ')[4].split(':');
			return retVal[0] + ":" + retVal[1];
		}
		
		$scope.duration = function(takeoff, landing) {
			//timestamp u milisekundama
			var diff = landing - takeoff;
			var minutes = Math.round(diff/60000);
			var days = Math.floor(minutes/(60*24));
			var hours = Math.floor(minutes/60);
			hours = hours % 24;
			minutes = minutes % 60;
			var retVal = "";
			if(days>0) retVal += days + "d ";
			if(hours>0) retVal += hours + "h ";
			if(minutes>0) retVal += minutes + "min"; 
			return retVal;
		}
		
		$("#priceSlider").prop('min', lowestPrice);
		$("#priceSlider").prop('max', highestPrice);
		if(params.includes("price=")) {
			$("#priceSlider").val($state.params.price.split('-')[1]);
		}
		else {
			$("#priceSlider").val(highestPrice);
		}
		$("#priceOutputMin").text(lowestPrice + "$ - ");
		$("#priceOutputMax").text($("#priceSlider").val()+ "$");
		
		$("#priceSlider").on('input', function(e){
			$("#priceOutputMax").text($(this).val() + "$");
		});
		$("#priceSlider").on('change', function(e){
			updateParam("price", lowestPrice + "-" + $(this).val());
		})
		
		function updateParam(key, val) {
			var params = Object.assign({}, $stateParams);
			params[key] = val;
			console.log(params);
			flightService.search(params).then(function(data){
			});
			//$state.go($state.current, params);
		}
		
		$scope.makeRes = function(flightId, retFlightId) {
			$state.go('reservation', {
				fl: flightId, 
				retFl: retFlightId, 
				fclass: $stateParams.fclass,
				adults: $stateParams.adults,
				children: $stateParams.children,
				infants: $stateParams.infants
				});
		};
		
	});
});