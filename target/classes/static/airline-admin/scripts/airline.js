var app = angular.module('app');

app.controller('airlineCtrl', function($scope, $http, $window, $routeParams, airlineService, destService, airplaneService) {
	
	var name = $routeParams.name;
	var id;

	airlineService.getAirline(name).then(function(airline) {
		console.log(airline);
		$scope.airline = airline;
		id = airline.id;
		
		destService.getDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.dests = dests;
		});
		
		destService.getNotAddedDestByAirlineId(airline.id).then(function(dests) {
			console.log(dests);
			$scope.otherDests = dests;
		});
		
		airplaneService.getAirplaneByOwner(airline.id).then(function(planes) {
			console.log(planes);
			$scope.planes = planes;
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
			$scope.planes.push(plane);
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
  });