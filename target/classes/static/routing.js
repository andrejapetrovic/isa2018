var app = angular.module('app', [ 'ngRoute' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/user/:id', {
		templateUrl : 'user/user.html',
		controller : 'uCtrl'
	}).when('/update-user/:id', {
		templateUrl : 'user/update-form.html',
		controller : 'uCtrl'
	}).when('/airplane-seat-config/:modelName/:modelNumber', {
		templateUrl : 'airline-admin/seat-config.html',
		controller : 'seatConfigCtrl'
	}).when('/update-airline/:name', {
		templateUrl : 'airline-admin/airline-profile.html',
		controller : 'airlineCtrl'
	})
});

