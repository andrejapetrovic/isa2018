var app = angular.module('app');
app.controller('searchFlightCtrl', function($scope, $http, $window, destService) {
	
	$scope.fromDests = [];
	$scope.toDests = [];
	
	$("#from").on('change keyup paste click', function () {
		if($("#from").val() == "") return;
	    destService.filter($scope.search.from).then(function(data){
	    	$scope.fromDests = filterData(data, $scope.fromDests);
	    });
	});
	
	$("#to").on('change keyup paste click', function () {
		if($("#to").val() == "") return;
	    destService.filter($scope.search.to).then(function(data){
	    	$scope.toDests = filterData(data, $scope.toDests);
	    	console.log($scope.toDests);
	    });
	});
	
	$scope.swapFields = function(){
		var btn = $("#swapDiv");
		var el1 = btn.prev("div");
		var el2 = btn.next("div");
		el1.before(el2);
		el2.after(btn);
	}

	function filterData(data, scopeData) {
    	var destCodes = data.map(d => d.airportCode)
    	var scopeDestCodes = scopeData.map(d => d.airportCode);
    	var toRemove = scopeData.filter(function(d){
    		return destCodes.indexOf(d.airportCode) == -1;
    	});
    	var toAdd = data.filter(function(d){
    		return scopeDestCodes.indexOf(d.airportCode) == -1;
    	});
    	scopeData = scopeData.filter(d => !toRemove.includes(d));
    	scopeData = scopeData.concat(toAdd);
     	return scopeData;
	}
});