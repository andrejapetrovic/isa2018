var app = angular.module('app');

app.controller('airlineCtrl', function($scope, $http, $window, $routeParams, airlineService, destService, airplaneService, flightService) {
	
	var name = $routeParams.name;
	var id;

	airlineService.getAirline(name).then(function(airline) {
		console.log(airline);
		$scope.airline = airline;
		id = airline.id;
		
		destService.getDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.dests = dests;
			$scope.stopDests = [];
			$scope.potentialStopDests = dests;
		});
		
		destService.getNotAddedDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.otherDests = dests;
		});
		
		airplaneService.getAirplaneByOwner(airline.id).then(function(planes) {
			console.log(planes);
			$scope.planes = planes;
		});
		
		flightService.getFlightsByAirline(airline.id).then(function(data) {
			console.log("flights");
			console.log(data);
			$scope.flights = data;
		});
			
	});

	$scope.addDest = function(destToAdd) {
		var data = _.merge(destToAdd, {airlineId:id});
		destService.addDestToAirline(data).then(function(dest) {
			$scope.dests.push(dest);
			$scope.otherDests = $scope.otherDests.filter(d => d != destToAdd);
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
		var data = _.merge(destToRemove, {airlineId:id});
		console.log(data);
		destService.removeDest(data).then(function(dest) {
			$scope.otherDests.push(dest);
			$scope.dests = $scope.dests.filter(d => d != destToRemove);
		});
	}

	$scope.addPlane = function() {
		var plane = {modelName:$scope.plane.modelName, modelNumber:$scope.plane.modelNumber, ownerId:id};
		airplaneService.addAirplane(plane).then(function(data){
			$scope.planes.push(data);
			delete $scope.plane;
		});
	}
		
	$scope.removePlane = function(planeToRemove) {
		var plane = {modelName: planeToRemove.modelName, modelNumber:planeToRemove.modelNumber, ownerId:id};
		airplaneService.deleteAirplane(plane).then(function(data){
			$scope.planes = $scope.planes.filter(plane => plane != planeToRemove);
		});
	}
	
	$scope.saveChanges = function() {
		$scope.airline.id = id
		var airline = angular.toJson($scope.airline);
		console.log(airline);
		airlineService.updateAirline(airline).then(function(data){
			console.log(data);
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
	
  });