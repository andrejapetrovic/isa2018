var app = angular.module('app', [ 'ngRoute' ]);
app.config(function($routeProvider) {
	$routeProvider
	.when('/user/:id', {
		templateUrl : 'user.html',
		controller : 'uCtrl'
	}).when('/update-user/:id', {
		templateUrl : 'update-form.html',
		controller : 'uCtrl'
	}).when('/seat-config', {
		templateUrl : 'seat-config.html',
		controller : 'seatConfigCtrl'
	})
});

