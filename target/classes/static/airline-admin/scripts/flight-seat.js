var app = angular.module('app');
app.controller('flightSeatCtrl', function($scope, $http, $window, $location,
		 $state, $window, $stateParams, seatService) {
	
	$(".nav-tabs").on("click", "a", function (e) {
		e.preventDefault();
		$(this).tab('show');
		var id = $(this).attr('href');
		$('.nav-tabs').find('.active').removeClass('active');
		$('.nav-tabs').find('a[href="'+id+'"]').parent().addClass('active');
	});
	
	seatService.getByFlight($stateParams.id).then(function(data){
		var seats = _.groupBy(data, "flightClass");
		console.log(data);
		getFclasses(Object.keys(seats), "flight");
		drawSeats(seats, "flight");
		
		$scope.flight = data[0].flight;
		console.log($scope.flight);
		
		
		
		$scope.selectedTab = "flight-economy";
		var fclass = $scope.selectedTab.charAt(0).toUpperCase();
		console.log(fclass);
	});
	
	function getFclasses(fclasses, flight) {
		fclasses.forEach(function(fclass){
			var id = "#" + flight + "-" + fclass.toLowerCase();
			console.log(id);
			$('.nav-tabs').find('a[href="'+id+'"]').parent().removeClass('hidden');
		});
		var selectId = "#" + flight + "-economy";
		$('.nav-tabs').find('a[href="' + selectId + '"]').parent().addClass('active');
		$(selectId).addClass('fade in active');
		$("." + flight + "-title").removeClass("hidden");
	}
	
	function drawSeats(seats, flight) {
		Object.keys(seats).forEach(function(key) {
			var canvas = document.createElement('canvas');
			var id = "#" + flight + "-" + key.toLowerCase();
			$(id).append("<\div id='seat-status'>");
			$(id).find("#seat-status")
			.append("<label>Row:</label><span id='row'> 0</span>&nbsp")
			.append("<label>Column:</label><span id='col'> 0</span>&nbsp")
			.append("<label>Status:</label><span id='status'> Seat not selected</span>");
			$(id).find("#status").css("color", "red");
			$(id).append("<br>").append(canvas);
			var ctx = canvas.getContext('2d');
			var first = seats[key][0].seat;
			var last = seats[key][seats[key].length-1].seat;
			canvas.width = first.x + last.x + 20;
			canvas.height = first.y + last.y + 20;
			ctx.strokeStyle = "#000000";
			seats[key].forEach(function(el){
				if(el.deleted) {
					ctx.fillStyle = "#ff0000";
				} else if (el.reserved) {
					ctx.fillStyle = "#e6e6fa";
				} else if (el.fastReservation) {
					ctx.fillStyle = "#00ff00";
				} else 
					ctx.fillStyle = "#FFFFFF";
				ctx.strokeRect(el.seat.x, el.seat.y, 20, 20);
				ctx.fillRect(el.seat.x, el.seat.y, 20, 20);	
			});
			canvas.addEventListener("mousemove", function(e) {
				  var r = this.getBoundingClientRect(),
			      x = e.clientX - r.left, //kursor,x
			      y = e.clientY - r.top;  // y
				  seats[key].forEach(function(el){
					  if (x >= el.seat.x && x <= el.seat.x + 20 && y >= el.seat.y && y <= el.seat.y + 20 ){
						  $(id).find("#row").text(" " + el.seat.row);
						  $(id).find("#col").text(" " + el.seat.col);
						  if(el.reserved){
							  $(id).find("#status").text(" Reserved").css("color", "red");
						  }
						  else {
							  var p = ctx.getImageData(el.seat.x, el.seat.y ,20, 20).data;
							  var hex = "#" + ("000000" + rgbToHex(p[0], p[1], p[2])).slice(-6);
							  if (hex == "#ff0000"){
								  $(id).find("#status").text(" Deleted").css("color", "red"); 
							  } else if (hex == "#ffffff") {
								  $(id).find("#status").text(" Open").css("color", "green"); 
							  } else if (hex == "#e6e6fa") {
								  $(id).find("#status").text(" Reserved").css("color", "grey"); 
							  } else if (hex == "#00ff00") {
								  $(id).find("#status").text(" Fast reservation").css("color", "green"); 
							  }
						  }
					  }
				  });
			});
			canvas.addEventListener("mouseleave", function(e) {
				  $(id).find("#row").text(" 0");
				  $(id).find("#col").text(" 0");
				  $(id).find("#status").text(" Seat not selected").css("color", "red");
				  $("#ret-err").text("");
			});
			canvas.addEventListener("click", function(e) {
				  var r = this.getBoundingClientRect(),
			      x = e.clientX - r.left, //kursor,x
			      y = e.clientY - r.top;  // y
				  seats[key].forEach(function(el){
					  if (x >= el.seat.x && x <= el.seat.x + 20 && y >= el.seat.y && y <= el.seat.y + 20 ){
						  var p = ctx.getImageData(el.seat.x, el.seat.y ,20, 20).data;
						  var hex = "#" + ("000000" + rgbToHex(p[0], p[1], p[2])).slice(-6);
						  if (hex == "#ffffff" && $scope.mode == "del"){
							  seatService.deleteSeat(el).then(function(data){
								  console.log(data);
								  ctx.fillStyle = "#ff0000";
								  el.deleted = data.deleted
								  ctx.fillRect(data.seat.x, data.seat.y, 20, 20);
							  }, function(err){
								  console.log(err.data.msg);
								  $scope.err = err.data.msg
							  });
						  }
						  else if (hex == "#ff0000" && $scope.mode == "add"){
							  seatService.addSeat(el).then(function(data){
								  ctx.fillStyle = "#ffffff";
								  el.deleted = data.deleted;
								  ctx.fillRect(data.seat.x, data.seat.y, 20, 20);
							  });
						  }
						  else if (hex == "#ffffff" && $scope.mode == "res"){
							  seatService.resSeat(el).then(function(data){
								  ctx.fillStyle = "#E6E6FA";
								  el.reserved = data.reserved;
								  ctx.fillRect(data.seat.x, data.seat.y, 20, 20);
							  });
						  }
						  else if (hex == "#ffffff" && $scope.mode == "fastRes"){
							  seatService.fastResSeat(el).then(function(data){
								  ctx.fillStyle = "#00ff00";
								  el.fastReservation = data.fastReservation;
								  ctx.fillRect(data.seat.x, data.seat.y, 20, 20);
							  });
						  }
					  }
				  });
			});
			
			$(".nav-tabs").on("click", "a", function (e) {
				console.log($(this).attr('href'));
				$scope.selectedTab = $(this).attr('href');
			});
		});
	}
	
});