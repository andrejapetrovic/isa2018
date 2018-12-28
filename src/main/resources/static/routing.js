var app = angular.module('app', [ 'ui.router' ]);
app.config(function($stateProvider, $urlRouterProvider) {
	 $urlRouterProvider.otherwise("/home")
	/*$routeProvider
	.when('/user/:id', {
		templateUrl : 'user/user.html',
		controller : 'uCtrl'
	}).when('/', {
		redirectTo: '/search-flight'
	}).when('/update-user/:id', {
		templateUrl : 'user/update-form.html',
		controller : 'uCtrl'
	}).when('/airplane-seat-config/:modelName/:modelNumber', {
		templateUrl : 'airline-admin/seat-config.html',
		controller : 'seatConfigCtrl'
	}).when('/update-airline/:name', {
		templateUrl : 'airline-admin/airline-profile.html',
		controller : 'airlineCtrl'
	}).when('/search-flight', {
		templateUrl : 'flight-reservation/search-flight.html',
		controller : 'searchFlightCtrl'
	}).when('/flights/:dests/:departDate/:returnDate/:fclass/:ftype/:passNum', {
		templateUrl : 'flight-reservation/flight-list.html',
		controller : 'flightListCtrl'
	})*/
	$stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "flight-reservation/search-flight.html",
            controller: "searchFlightCtrl"
        })
          .state('home.searchFlight', {
              url: "/search/",
              templateUrl: "flight-reservation/flight-list.html",
              controller: "flightListCtrl"
          })
	
});

