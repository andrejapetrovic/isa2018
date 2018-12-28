var app = angular.module('app');
app.controller('flightListCtrl', function($scope, $http, $window, destService, flightService, $stateParams) {
	console.log($stateParams);
	/*var dests = ($stateParams.dests).split('-');
	var searchParams = 
		"?fromDest=".concat(dests[0])
		.concat("&toDest=").concat(dests[1])
		.concat("&departDate=").concat($stateParams.departDate)
		.concat("&returnDate=").concat($stateParams.returnDate)
		.concat("&fclass=").concat($stateParams.fclass)
		.concat("&ftype=").concat($stateParams.ftype)
		.concat("&passNum=").concat($stateParams.passNum)
		.concat("&stopDests=BCN-VFA")
		//.concat("&airline=1-2")
		;*/
	searchParams = 
		"?fromDest=BEG"
		+"&toDest=VIE"
		+"&departDate=29-December-2018"
		+"&returnDate=30-December-2018"
	console.log(searchParams);
	flightService.search(searchParams).then(function(data){
		console.log(data);
		$scope.flights = data;
	});
});