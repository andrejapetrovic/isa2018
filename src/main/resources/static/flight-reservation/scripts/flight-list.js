var app = angular.module('app');
app.controller('flightListCtrl', function($scope, $http, $window, destService, flightService, $location, $state, $window, $stateParams) {
	var params = $location.url().split('?')[1];
	console.log(params);
	flightService.search(params).then(function(data){
		console.log(data);
		$scope.flights = [];
		$scope.retFlights = [];
		var fromTo = " (" + $stateParams.fromDest + "-" + $stateParams.toDest + ")";
		$scope.sortOptions = [{code:"cheapest", val:"Cheapest"}, {code:"highest_price", val:"Highest price"},
			{code:"quickest", val:"Quickest"}, {code:"slowest", val:"Slowest"}, {code:"earliest_takeoff1", val:"Earliest takeoff" + fromTo},
			{code:"earliest_landing1", val:"Earliest landing" + fromTo}, {code:"latest_takeoff1", val:"Latest takeoff" + fromTo},
			{code:"latest_landing1", val:"Latest landing" + fromTo}];
		var priceArr = [];
		var tripDurrationArr;
		
		var airlines = [];
		data['airlines'].forEach(function(airline){
			airlines.push({id: airline.id, name: airline.name, selected: true});
		})
		$scope.airlines = airlines;
		
		tripDurrationArr = data.flights[0].map(f => dateDiff(f.landingDate, f.departureDate));
		
		if (data.flights.length == 1){
			$scope.flights = data.flights[0];
			$scope.ftype = 1;
			priceArr = data.flights[0].map(f => f.oneWayPrice);
		}
		else if (data.flights.length == 2){
			$scope.ftype = 2;
			//priceArr = data.flights[0].map(f => f.returnPrice);
			data.flights[0].forEach(function(flight1){
				data.flights[1].forEach(function(flight2){
					if(flight1.airline.name == flight2.airline.name) {
						$scope.flights.push(Object.assign({}, flight1));
						$scope.retFlights.push(Object.assign({}, flight2));
						priceArr.push(flight1.oneWayPrice + flight2.returnPrice);
					}
				});
			});
		}

		$scope.stops = data.stops.map(function(el) {
			  var o = Object.assign({}, el);
			  o.selected = true;
			  return o;
			});
		$scope.anyDest = true;
		console.log($scope.stops);
		
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
		
		var lowestPrice = Math.min.apply(Math, priceArr);
		var highestPrice = Math.max.apply(Math, priceArr);
		var lowestDurration = Math.min.apply(Math, tripDurrationArr);
		var highestDurration = Math.max.apply(Math, tripDurrationArr);
		
		$("#priceSlider").prop('min', lowestPrice);
		$("#priceSlider").prop('max', highestPrice);
		$("#tripDurrationSlider").prop('min', lowestDurration);
		$("#tripDurrationSlider").prop('max', highestDurration);
		
		$("#priceSlider").val(highestPrice);
		$("#tripDurrationSlider").val(highestPrice);

		$("#priceOutputMin").text(lowestPrice + "$ - ");
		$("#priceOutputMax").text($("#priceSlider").val()+ "$");
		$("#tripDurrationMin").text(lowestDurration + " hours - ");
		$("#tripDurrationMax").text($("#tripDurrationSlider").val()+ " hours");
		
		$("#priceSlider").on('input', function(e){
			$("#priceOutputMax").text($(this).val() + "$");
		});
		$("#tripDurrationSlider").on('input', function(e){
			$("#tripDurrationMax").text($(this).val() + " hours");
		});
		$("#priceSlider").on('change', function(e){
			updateParam("price", lowestPrice + "-" + $(this).val());
		})
		$("#tripDurrationSlider").on('change', function(e){
			updateParam("duration1", lowestDurration + "-" + $(this).val());
		})
		
		$scope.selectSort = function() {
			updateParam("sort", $scope.sortOption.code);
		}
		
		$scope.toggleAirline = function(idx) {
			var q = $scope.airlines.filter(a => a.selected);
			q = q.map(a => a.id);
			if (q.length > 0)
				updateParam("airlines", q.join("-"));
			else {
				$scope.flights = [];
				$scope.retFlights = [];
			}
		}
		
		$scope.toggleStopDest = function(e) {
			extractStopCodes();
		}
		
		$scope.toggleAnyDest = function(){
			if($scope.anyDest == false) {
				angular.forEach($scope.stops, function(stop) {
					stop.selected = false;
				});
			} else {
				angular.forEach($scope.stops, function(stop) {
					stop.selected = true;
				});
			}
			extractStopCodes();
		}
		
		function extractStopCodes(){
			var filtered = $scope.stops.filter(s => s.selected)
			var codes = filtered.map(s => s.airportCode);
			codes = codes.join('-');
			console.log(codes);
			
			if ($scope.stops.length == filtered.length) {
				updateParam("stops", -1);
			} else if (codes != "") {
				updateParam("stopDests", codes);
			} else {
				updateParam("stops", 0);
			}
		}
		
		$("[type=radio]").change(function(e){
			updateParam("stops", $scope.stopNum);
		});
		
		var updatedParams = params;
		
		function updateParam(key, val) {
			var l1 = updatedParams.length;
			updatedParams += ("&"+key+"="+val);
			console.log(updatedParams);
			var l2 = updatedParams.length;
			flightService.search(updatedParams).then(function(data){
				
				if (data.flights.length == 1){
					$scope.flights = data.flights[0];
				}
				else if (data.flights.length == 2){
					$scope.flights = [];
					$scope.retFlights = [];
					data.flights[0].forEach(function(flight1){
						data.flights[1].forEach(function(flight2){
							if(flight1.airline.name == flight2.airline.name) {
								$scope.flights.push(Object.assign({}, flight1));
								$scope.retFlights.push(Object.assign({}, flight2));
							}
						});
					});
				}
				
			}, function(err) {
				$scope.flights = [];
			});
			updatedParams = updatedParams.substring(0, l1);
		}
		
		$scope.makeRes = function(flightId, retFlightId) {
			console.log(retFlightId);
			$state.go('seats', {
				fl: flightId, 
				ret: retFlightId, 
				fclass: $stateParams.fclass,
				adults: $stateParams.adults,
				children: $stateParams.children,
				infants: $stateParams.infants
				});
		};
		
	});
	
	function dateDiff(date1, date2) {
		var d1 = new Date(date1);
		var d2 = new Date(date2);
		var difference = Math.abs( (d2 - d1)/(60*60*1000));
		console.log(difference);
		return difference;
	}
	

});