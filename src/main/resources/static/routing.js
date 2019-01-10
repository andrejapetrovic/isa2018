var app = angular.module('app', [ 'ui.router' ]);
app.config(function($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise("/home")
	$stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "flight-reservation/search-flight.html",
            controller: "searchFlightCtrl"
        })
          .state('home.searchFlight', {
              url: "/search?fromDest&toDest&departDate&returnDate&fclass&ftype&adults&children&infants" +
              		"&sort&airlines&stopDests&price",
              templateUrl: "flight-reservation/flight-list.html",
              controller: "flightListCtrl"
          })
          .state('registration', {
        	  url: "/registration",
        	  templateUrl: "user/registration.html",
        	  controller: "regCtrl"
          })
         .state('seatConf', {
            url: "/airline-admin/seat-config/:id",
            templateUrl: "airline-admin/seat-config.html",
            controller: "seatConfigCtrl"
        })
        .state('airline', {
            url: "/airline-admin/airline/:id",
            templateUrl: "airline-admin/airline-profile.html",
            controller: "airlineCtrl"
        })
        .state('activate-acc', {
            url: "/activation/:code",
            templateUrl: "user/activation-page.html",
            controller: "activationCtrl"
        })
        .state('user', {
            url: "/user-profile",
            templateUrl: "user/user.html",
            controller: "uCtrl"
        })
	
});

