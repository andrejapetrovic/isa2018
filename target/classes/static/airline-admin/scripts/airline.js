var app = angular.module('app');

app.controller('airlineCtrl', function($scope, $http, $window, $stateParams, airlineService, destService, airplaneService, flightService) {
	
	var id = $stateParams.id;
	airlineService.getAirline(id).then(function(airline) {
		console.log(airline);
		$scope.airline = airline;
		$scope.potentialDests = [];

		$(".nav-tabs").on("click", "a", function (e) {
			e.preventDefault();
			$(this).tab('show');
			var id = $(this).attr('href');
			$('.nav-tabs').find('.active').removeClass('active');
			$('.nav-tabs').find('a[href="'+id+'"]').parent().addClass('active');
		    localStorage.setItem('selectedTab', id);
		});
		
		$scope.saveChanges = function() {
			$scope.airline.id = id
			var airline = angular.toJson($scope.airline);
			console.log(airline);
			airlineService.updateAirline(airline).then(function(data){
				console.log(data);
			});
		}
		
		$("#searchDest").on('keyup paste click', function () {
			if($("#searchDest").val() == "") return;
		    destService.filterNonAddedByAirline(id, $scope.searchDest).then(function(data){
		    	console.log(data);
		    	$scope.potentialDests = filterData(data, $scope.potentialDests);
		    });
		});
		
		destService.getDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.dests = dests;
			$scope.stopDests = [];
			$scope.potentialStopDests = dests;
		});
		
		
		airplaneService.getAirplaneByOwner(airline.id).then(function(planes) {
			console.log(planes);
			$scope.planes = planes;
			$scope.aircraftTypes = ['Wide body jet', 'Narrow body jet', 'Turbo prop plane', 'Regional jet'];
			$scope.aircraftType = $scope.aircraftTypes[0];
		});
		
		flightService.getFlightsByAirline(airline.id).then(function(data) {
			console.log("flights");
			console.log(data);
			$scope.flights = data;
		});
			
	});

	$scope.addDest = function() {
		console.log($scope.searchDest);
		var data = {
			airportCode: $scope.searchDest.substring(1,4),
			airlineId: id	
		};
		destService.addDestToAirline(data).then(function(dest) {
			$scope.dests.push(dest);
			$scope.searchDest = "";
		});
	}
	
	$scope.addNewDest = function() {
		$scope.newDest.airlineId = id;
		var dest = angular.toJson($scope.newDest);
		console.log(dest);
		destService.addDestToAirline(dest).then(function(dest) {
			$scope.dests.push(dest);
			delete $scope.newDest;
		});
	}
	
	$scope.removeDest = function(destToRemove) {
		console.log(destToRemove);
		destService.removeDest(destToRemove.id, id).then(function(dest) {
			$scope.dests = $scope.dests.filter(d => d.airportCode != dest.airportCode);
		});
	}

	$scope.addPlane = function() {
		$scope.plane.type = $scope.aircraftType.split(' ').join('_');
		$scope.plane.ownerId = id;
		console.log($scope.plane);
		airplaneService.addAirplane($scope.plane).then(function(data){
			$scope.planes.push(data);
			delete $scope.plane;
		});
	}
		
	$scope.removePlane = function(planeToRemove) {
		airplaneService.deleteAirplane(planeToRemove.id, id).then(function(retPlane){
			$scope.planes = $scope.planes.filter(plane => plane.id != retPlane.id);
		});
	}
	
	$scope.addNewFlight = function() {
		$scope.newFlight.airlineId = id;
		$scope.newFlight.from = $scope.newFlight.from.airportCode;
		$scope.newFlight.to = $scope.newFlight.to.airportCode
		$scope.newFlight.airplaneModelName = $scope.newFlight.airplane.modelName;
		$scope.newFlight.airplaneModelNumber = $scope.newFlight.airplane.modelNumber;
		var flight = $scope.newFlight;
		$scope.newFlight.stopDestCodes = [];
		if($scope.stopDests != null) {
			$scope.stopDests.forEach(function(dest){
				$scope.newFlight.stopDestCodes.push(dest.airportCode);
			});
		}
		$scope.newFlight.stopCount = $scope.newFlight.stopDestCodes.length;
		console.log(flight);
		flightService.addFlight(flight).then(function(data){
			console.log(data);
			$scope.flights.push(data);
			delete $scope.newFlight;
			delete $scope.stopDests;
		});
	}
	
	$scope.addStopDest = function(dest) {
		$scope.stopDests.push(dest);
		$scope.potentialStopDests = $scope.potentialStopDests.filter(d => d != dest);
	}
	
	$scope.removeStopDest = function(dest) {
		$scope.stopDests = $scope.stopDests.filter(d => d != dest);
		$scope.potentialStopDests.push(dest);
	}
	
	$scope.parseAircraftType = function(type) {
		return type.split('_').join(' ');
	}
	
	$scope.formatDate = function(date) {
		var d = new Date(date);
		var retVal = d.toString();
		retVal = retVal.substring(0, retVal.indexOf(':')+3);
		return retVal;
	}
	
	var selectedTab = localStorage.getItem('selectedTab');
		if (selectedTab != null) {
			console.log(selectedTab);
			$('a[data-toggle="tab"][href="' + selectedTab + '"]').tab('show');
			$('.nav-tabs').find('.active').removeClass('active');
			$('.nav-tabs').find('a[href="'+selectedTab+'"]').parent().addClass('active');
		}
  });