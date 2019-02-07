var app = angular.module('app', [ 'ui.router' ]);
app.config(function($stateProvider, $urlRouterProvider) {
	$stateProvider
        .state('home', {
            url: "/home",
            templateUrl: "flight-reservation/search-flight.html",
            controller: "searchFlightCtrl"
        })
          .state('searchFlight', {
              url: "/search?fromDest&toDest&departDate&returnDate&fclass&ftype&adults&children&infants" +
              		"&sort&airlines&stopDests&price",
              templateUrl: "flight-reservation/flight-list.html",
              controller: "flightListCtrl"
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
        .state('airline-admin', {
            url: "/airline-admin/airlines",
            templateUrl: "airline-admin/airlines.html",
            controller: "airlineListCtrl"
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
        .state('reservation', {
            url: "/res?fl&retFl&seats&retSeats&infants",
            templateUrl: "flight-reservation/res.html",
            controller: "resCtrl"
        })
        .state('seats', {
            url: "/seats?fl&ret&fclass&adults&children&infants",
            templateUrl: "flight-reservation/seats.html",
            controller: "seatCtrl"
        })
        .state('addRentACar-admin', {
            url: "/addRentACar-admin",
            templateUrl: "rentACar-admin/addRentACar.html",
            controller: "rentACarCtrl"
        })
        .state('rentACarList', {
            url: "/rentACarList",
            templateUrl: "rentACar-admin/services.html",
            controller: "rentACarCtrl"
        })
        .state('branches', {
            url: "/branches/:id",
            templateUrl: "rentACar-admin/branches.html",
            controller: "branchOfficeListCtrl"
        })
        .state('addBranchOffice', {
            url: "/addBranchOffice/:id",
            templateUrl: "rentACar-admin/addBranchOffice.html",
            controller: "rentACarCtrl"
        })
        .state('changeBranchOffice', {
            url: "/changeBranchOffice/:id",
            templateUrl: "rentACar-admin/changeBranchOffice.html",
            controller: "changeBranchOfficeCtrl"
        })
         .state('carList', {
            url: "/carList",
            templateUrl: "rentACar-admin/car-list.html",
            controller: "carListCtrl"
        })
        .state('branchOfficeCarList', {
            url: "/carList/:id",
            templateUrl: "rentACar-admin/car-list.html",
            controller: "branchOfficeCarList"
        })
        
        
        $urlRouterProvider.when('', '/home');
});

