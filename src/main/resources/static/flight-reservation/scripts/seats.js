var app = angular.module('app');
app.controller('seatCtrl', function($scope, $http, $window, $location,
		 $state, $window, $stateParams, seatService) {
	var selectedSeats = [];
	
	$(".nav-tabs").on("click", "a", function (e) {
		e.preventDefault();
		$(this).tab('show');
		var id = $(this).attr('href');
		$('.nav-tabs').find('.active').removeClass('active');
		$('.nav-tabs').find('a[href="'+id+'"]').parent().addClass('active');
	});
	
	var params = $location.url().split('?')[1];
	seatService.getFlightSeats(params).then(function(data){
		var seats = _.groupBy(data[0], "flightClass");
		getFclasses(Object.keys(seats), "flight");
		drawSeats(seats, "flight");
		if(params.includes("ret=")){
			var retSeats = _.groupBy(data[1], "flightClass");
			getFclasses(Object.keys(retSeats), "ret-flight");
			drawSeats(retSeats, "ret-flight");
		}
	});
	
	function getFclasses(fclasses, flight) {
		fclasses.forEach(function(fclass){
			var id = "#" + flight + "-" + fclass.toLowerCase();
			$('.nav-tabs').find('a[href="'+id+'"]').parent().removeClass('hidden');
		});
		var selectId = "#" + flight + "-" + $stateParams.fclass.toLowerCase();
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
			ctx.fillStyle = "#FFFFFF";
			ctx.strokeStyle = "#000000";
			seats[key].forEach(function(el){
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
							  if (hex == "#a6ea79"){
								  $(id).find("#status").text(" Selected").css("color", "green"); 
							  } else 
								  $(id).find("#status").text(" Open").css("color", "green"); 
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
						  if (hex == "#ffffff"){
							  if (retCountCheck(el.flight.id)) {
								  $("#ret-err")
								  	.text("You can not reserve more return flight seats than departing flight seats")
								  	.css("color", "red");
								  return;
							  }
							  ctx.fillStyle = "#a6ea79";
							  ctx.fillRect(el.seat.x, el.seat.y, 20, 20);
							  selectedSeats.push(el);
						  }
						  else if (hex == "#a6ea79"){
							  ctx.fillStyle = "#ffffff";
							  ctx.fillRect(el.seat.x, el.seat.y, 20, 20);
							  selectedSeats = selectedSeats.filter(ss => ss != el);
						  }
						  
						  selectedSeats.length == 0 ? $(".next").prop("disabled", true) : $(".next").prop("disabled", false);
						  console.log($scope.disableNextBtn);
						  console.log(selectedSeats);
					  }
				  });
			});
			
			function retCountCheck(elId) {
				var seats = selectedSeats.filter(ss => ss.flight.id == $stateParams.fl);
				var retSeats = selectedSeats.filter(ss => ss.flight.id == $stateParams.ret);
				var sNum = seats.length;
				var rsNum = retSeats.length;
				elId == $stateParams.fl ? ++sNum: ++rsNum;
				if(sNum < rsNum){
					return true;
				}
				return false;
			}
			
			$scope.next = function() {
				var seats = selectedSeats
					.filter(ss => ss.flight.id == $stateParams.fl);
				var seatIds = seats
					.map(ss => ss.id);
				var retIds = selectedSeats
					.filter(ss => ss.flight.id == $stateParams.ret)
					.map(ss => ss.id);
				var queryParams = {
						fl: $stateParams.fl,
						retFl: $stateParams.ret,
						seats: seatIds.join("-"),
						retSeats: retIds.join("-"),
						infants: $stateParams.infants
				}
				console.log(queryParams);
				$state.go("reservation", queryParams);
			}
		});
	}
});
