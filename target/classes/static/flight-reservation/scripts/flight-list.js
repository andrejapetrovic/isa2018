var app = angular.module('app');
app.controller('flightListCtrl', function($scope, $http, $window, destService, flightService, $routeParams) {
	var dests = ($routeParams.dests).split('-');
	var searchParams = 
		"?fromDest=".concat(dests[0])
		.concat("&toDest=").concat(dests[1])
		.concat("&departDate=").concat($routeParams.departDate)
		.concat("&returnDate=").concat($routeParams.returnDate)
		.concat("&fclass=").concat($routeParams.fclass)
		.concat("&ftype=").concat($routeParams.ftype)
		.concat("&passNum=").concat($routeParams.passNum)
		.concat("&stopDests=BCN-VFA")
		//.concat("&airline=1-2")
		;
	console.log(searchParams);
	flightService.search(searchParams).then(function(data){
		console.log(data);
		$scope.flights = data;
	});
});