var app = angular.module('app');

app.controller('airlineCtrl', function($scope, $http, $window, $stateParams,$state, airlineService, destService, airplaneService, flightService) {
	
	var id = $stateParams.id;
	var stopDests = [];
	
	airlineService.getAirlineProfile(id).then(function(data) {
		console.log(data);
		$scope.airline = data["airline"];
		$scope.potentialDests = [];
		$scope.toDests = [];
		$scope.fromDests = [];
		$scope.stopDests = [];
		$scope.dests = data["destinations"];
		$scope.planes = data["aircrafts"];
		$scope.aircraftTypes = ['Wide body jet', 'Narrow body jet', 'Turbo prop plane', 'Regional jet'];
		$scope.aircraftType = $scope.aircraftTypes[0];
		$scope.flights = data["flights"];
		$scope.flightAircraft = $scope.planes[0];		
		$scope.loading = false;
		$scope.bag = data['airline'].pricelist;
		console.log($scope.bag);
		
		$(".nav-tabs").on("click", "a", function (e) {
			e.preventDefault();
			$(this).tab('show');
			var id = $(this).attr('href');
			$('.nav-tabs').find('.active').removeClass('active');
			$('.nav-tabs').find('a[href="'+id+'"]').parent().addClass('active');
		    localStorage.setItem('selectedTab', id);
		});
		
		$scope.saveChanges = function() {
			$scope.airline.id = id
			$("#x-info").addClass("hidden");
			$scope.updInfoSucces = "request sent";
			airlineService.updateAirline($scope.airline).then(function(data){
				console.log(data);
				$("#x-info").removeClass("hidden");
				$scope.updInfoSucces = data.name + " info successfully changed";
			});
		}
		
		$("#searchDest").on('keyup paste click', function () {
			if($("#searchDest").val() == "") return;
		    destService.filterNonAddedByAirline(id, $scope.searchDest).then(function(data){
		    	console.log(data);
		    	$scope.potentialDests = filterData(data, $scope.potentialDests);
		    });
		});
		
		$("#from").on('keyup paste click', function () {
			if($("#from").val() == "") return;
		    destService.filterByAirline(id, $scope.newFlight.from).then(function(data){
		    	$scope.fromDests = filterData(data, $scope.fromDests);
		    });
		});
		
		$("#to").on('keyup paste click', function (e) {
			if($("#to").val() == "") return;
		    destService.filterByAirline(id, $scope.newFlight.to).then(function(data){
		    	$scope.toDests = filterData(data, $scope.toDests);
		    });
		});
		
		$("#stop").on('keyup paste click', function (e) {
			console.log($scope.newFlight.stop);
			if($("#stop").val() == "") return;
		    destService.filterByAirline(id, $scope.newFlight.stop).then(function(data){
		    	$scope.stopDests = filterData(data, $scope.stopDests);
		    });
		});
		
		var tripDatePicker = new datePicker({
		    start:  document.getElementsByClassName('startDate'),
		    end:    document.getElementsByClassName('endDate'),
		    months: 2,
		});
		
		var timepicker = new TimePicker(['time-departure', 'time-landing'], {
			lang: 'en',
			theme: 'dark'
		});
		timepicker.on('change', function(evt) {
			var value = (evt.hour || '00') + ':' + (evt.minute || '00');
			evt.element.value = value;
		});
		
		$scope.addDest = function() {
			console.log($scope.searchDest);
			var data = {
					airportCode: $scope.searchDest.substring(1,4),
					airlineId: id	
			};
			destService.addDestToAirline(data).then(function(dest) {
				$scope.dests.push(dest);
				$scope.searchDest = "";
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
			console.log(destToRemove);
			destService.removeDest(destToRemove.id, id).then(function(dest) {
				$scope.dests = $scope.dests.filter(d => d.airportCode != dest.airportCode);
			});
		}
		
		$scope.addPlane = function() {
			$scope.plane.type = $scope.aircraftType.split(' ').join('_');
			$scope.plane.ownerId = id;
			console.log($scope.plane);
			airplaneService.addAirplane($scope.plane).then(function(data){
				$scope.planes.push(data);
				delete $scope.plane;
			});
		}
		
		$scope.removePlane = function(planeToRemove) {
			airplaneService.deleteAirplane(planeToRemove.id, id).then(function(retPlane){
				$scope.planes = $scope.planes.filter(plane => plane.id != retPlane.id);
			});
		}
		
		$scope.addFlight = function() {
			$scope.loading = true;
			$scope.newFlight.airlineId = id;
			$scope.newFlight.from = $scope.newFlight.from.substring(1,4);
			$scope.newFlight.to = $scope.newFlight.to.substring(1,4);
			$scope.newFlight.aircraftId = $scope.flightAircraft.id;
			$scope.newFlight.stopDestCodes = [];
			stopDests.forEach(function(dest){
				$scope.newFlight.stopDestCodes.push(dest.substring(1,4));
			});
			$scope.newFlight.stopCount = $scope.newFlight.stopDestCodes.length;
			$scope.newFlight.departureDate = parseDateTime($('#depart').val(), $('#time-departure').val());
			$scope.newFlight.landingDate = parseDateTime($('#landing').val(), $('#time-landing').val());
			console.log($scope.newFlight.departureDate);
			console.log($scope.newFlight);
			flightService.addFlight($scope.newFlight).then(function(data){
				console.log(data);
				$scope.flights.push(data);
				delete $scope.newFlight;
				stopDests = [];
				$('.stops').empty();
				$scope.loading = false;
				$scope.err = "";
			}, function(err){
				console.log(err.data.msg);
				$scope.addFlErr = err.data.msg
				delete $scope.newFlight;
				$scope.loading = false;
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
		
		$scope.parseAircraftType = function(type) {
			return type.split('_').join(' ');
		}
		
		$scope.formatDate = function(date) {
			var d = new Date(date);
			var retVal = d.toString();
			retVal = retVal.substring(0, retVal.indexOf(':')+3);
			return retVal;
		}
		
		$scope.addStop = function() {
			var txt = $('<span/>')
			.addClass('airline-table')
			.text($('#stop').val());
			
			var xBtn = $('<button/>')
			.text('x')
			.addClass('btn btn-link x-btn')
			.click(function () {
				stopDests = stopDests.filter(d => d != txt);
				$(this).prev().remove();
				$(this).next().remove();
				$(this).remove();
			});
			delete $scope.newFlight.stop;
			$(".stops").append(txt).append(xBtn).append('<br>');
			stopDests.push(txt.text());
		}
		
		$("#x-pl").click(function(e){
			$scope.priceListSucces = "";
			$(this).addClass("hidden");
		});
		
		$("#x-info").click(function(e){
			$scope.updInfoSucces = "";
			$(this).addClass("hidden");
		});
		
		$scope.baggageFees = function() {
			$scope.bag['airlineId'] = id;
			$("#x-pl").addClass("hidden");
			$scope.priceListSucces = "request sent";
			airlineService.addPrices($scope.bag).then(function(data){
				$scope.priceListSucces = "prices changed successfully";
				$("#x-pl").removeClass("hidden");
			});
		}
		
		var selectedTab = localStorage.getItem('selectedTab');
		if (selectedTab != null) {
			console.log(selectedTab);
			$('a[data-toggle="tab"][href="' + selectedTab + '"]').tab('show');
			$('.nav-tabs').find('.active').removeClass('active');
			$('.nav-tabs').find('a[href="'+selectedTab+'"]').parent().addClass('active');
		}
		
		function parseDateTime(date, time) {
			date = date.split(' ').join('-');
			return date += ' ' + time;
		}
		
		$scope.cabinConfig = function(flId){
			$state.go('flight-seats', {id: flId});
			//$window.location.href = '#!/airline-admin/flight-seats?id='+id;
		}
	});
  });