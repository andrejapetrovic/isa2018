var app = angular.module('app');

app.controller('seatConfigCtrl', function($scope, $http, $window, $stateParams, airplaneService, cabinService) {

	
	$(".nav-tabs").on("click", "a", function (e) {
		e.preventDefault();
		$(this).tab('show');
		var id = $(this).attr('href');
		$('.nav-tabs').find('.active').removeClass('active');
		$('.nav-tabs').find('a[href="'+id+'"]').parent().addClass('active');
	});
	
	
		airplaneService.getAirplane($stateParams.id).then(function(data){
			$scope.data = data;
			$scope.aircraft = data.aircraft;
			
			
			var	cabins = {
					economy: {configured: false},
					business: {configured: false},
					premium: {configured: false},
					first: {configured: false}
			};
			
			var seats = data.seats.map(function(el) {
				  var o = Object.assign({}, el);
				  o.flightClass = el.cabin.flightClass;
				  return o;
				})
			seats = _.groupBy(seats, "flightClass");
			console.log(seats);
			getFclasses(Object.keys(seats), "flight");
			drawSeats(seats, "flight");
			$scope.selectedTab = "#flight-economy";
			
			function getFclasses(fclasses, flight) {
				fclasses.forEach(function(fclass){
					var id = "#" + flight + "-" + fclass.toLowerCase();
					console.log(id);
					$('.nav-tabs').find('a[href="'+id+'"]').parent().removeClass('hidden');
					cabins[fclass.toLowerCase()].configured = true;
				});
				var selectId = "#" + flight + "-economy";
				$('.nav-tabs').find('a[href="' + selectId + '"]').parent().addClass('active');
				$(selectId).addClass('fade in active');
			}
			
			function drawSeats(seats, flight) {
				Object.keys(seats).forEach(function(key) {
					var canvas = document.createElement('canvas');
					var id = "#" + flight + "-" + key.toLowerCase();
					$(id).append("<br>").append(canvas);
					var ctx = canvas.getContext('2d');
					var first = seats[key][0];
					var last = seats[key][seats[key].length-1];
					canvas.width = first.x + last.x + 20;
					canvas.height = first.y + last.y + 20;
					ctx.strokeStyle = "#000000";
					ctx.fillStyle = "#ffffff";
					seats[key].forEach(function(el){
						ctx.strokeRect(el.x, el.y, 20, 20);
						ctx.fillRect(el.x, el.y, 20, 20);	
					});
				console.log(key);
				cabins[key.toLowerCase()].configured = true;
				});
			}
			
			
			
			
			
			var selectedCanvasId;
			var cabinClass; 
				
				$scope.rows = 40;
				$scope.cols = 10;
				$scope.sep = "3-4-3";
				$scope.sepSize = 40;
				$scope.dx = 10;
				$scope.dy = 3;
				$scope.d = 20
			
			
			
			function createCanvas(id) {
				var canvas = document.createElement('canvas');
				canvas.id = id + "-canvas";
				$("#" + id).append(canvas);
				return canvas;
			}
			
			$scope.addSeats = function() {
				var canvasRmv = document.getElementById($scope.selectedTab-'canvas');
				if(canvasRmv != null){
					var parent = canvasRmv.parentNode;
					while (parent.firstChild) {
					    parent.removeChild(parent.firstChild);
					}
				}
				var cabinClass = $scope.selectedTab.split('-')[1];
				var canvas = document.createElement('canvas');
				canvas.id = $scope.selectedTab-'canvas';
				$($scope.selectedTab).append(canvas);
				var ctx = canvas.getContext('2d');
				var rows = $scope.rows;
				var cols = $scope.cols;
				var sep = $scope.sep.split('-').map(function(t){return parseInt(t)});
				sep.pop();
				var sepAtCols = [];
				for (var a = 0; a<sep.length; a++) {
					if (a == 0) {
						sepAtCols.push(sep[a]);
					}
					else {
						sepAtCols.push(sep[a]+sepAtCols[a-1])
					}
				}
				var sepSize = $scope.sepSize;
				var d = $scope.d; // stranica kvadrata
				var dx = $scope.dx; // razmak po x
				var dy = $scope.dy;// razmak po y
				var s;
				canvas.width = (d+dx)*rows+dx;
				canvas.height = (d+dy)*cols+dy + sepAtCols.length*sepSize;
				ctx.fillStyle = "#FFFFFF";
				ctx.strokeStyle = "#000000";
				var seats = [];
				for (i=0; i<rows; i++) {
					s = 0;
					for (j=0; j<cols; j++) {
						if(sepAtCols.includes(j)){
							s += sepSize;
						}
						//parametri x,y,duzina po x, duzina po y
						ctx.strokeRect(i*d+(i+1)*dx, j*d+(j+1)*dy+s, d, d);
						ctx.fillRect(i*d+(i+1)*dx,j*d+(j+1)*dy+s,d,d);
						seats.push( {
								x: i*d+(i+1)*dx,
								y: j*d+(j+1)*dy+s,
								row: i+1,
								col: j+1
						});
					}
				}
				cabins[cabinClass] = {
					flightClass: cabinClass,
					row: rows,
					col: cols,
					dx: dx,
					dy: dy,
					d: d,
					separations: $scope.sep,
					separationSize: $scope.sepSize,
					seats: seats,
					aircraftId: $scope.aircraft.id
				}
				console.log(cabins);
				var saveBtn = $('<button/>')
			    .text('Save')
			    .click(function () {
			    	$(this).next().remove();
			    	$(this).remove();
			    	var fClass = cabinClass.charAt(0).toUpperCase() + cabinClass.slice(1);
			    	cabins[cabinClass].flightClass = fClass;
			    	console.log(cabins["first"]);
			    	cabinService.addCabin(cabins[cabinClass]).then(function(data){
			    		//$("#" + cabinClass).prop('disabled', true);
			    		//$("#create-seats").prop('disabled', true);
			    	});
			    })
			    var txt = $('<span/>').text(' Once you save you will no longer be able to make changes to this cabin');
				$('#'+canvas.id).parent().append(saveBtn).append(txt).prepend('<br>');
			}
			
			$(".cabin-cbox").on("change", function(e){
				var tabId = $(this).attr('id') + '-cabin';
				if(this.checked){
					createTab(tabId);
				}
				else{
					$("#"+tabId).remove();
					$("#"+tabId + "-tab").remove();
				}
			});
			
			/*function createTab(tabId) {
				var id = $(".n av-tabs").children().length;
				var name = tabId.split('-')[0];
				name = name.replace(name[0], name[0].toUpperCase());
				$(".anchor").closest('li').before('<li><a href="#' + tabId + '" id="' + tabId + '-tab">'+ name + '</a></li>');
				$('.tab-content').append('<div class="tab-pane" id="' + tabId + '"><h3>'+name+' cabin</h3></div>');
				$('.nav-tabs li:nth-child(' + id + ') a').click();
				
				var canvas = createCanvas(tabId);
			}*/
			
			$(".nav-tabs").on("click", "a", function (e) {
				console.log($(this).attr('href'));
				$scope.selectedTab = $(this).attr('href');
				cabinClass = $scope.selectedTab.split('-')[1];
				if(cabins[cabinClass].configured) {
					$("#create-seats").prop("disabled", true);
				}
			});
		});
		
		function drawSeats2(seats, d) {
			var canvas = document.getElementById(selectedCanvasId);
			var ctx = canvas.getContext('2d');
			ctx.fillStyle = "#FFFFFF";
			ctx.strokeStyle = "#000000";
			seats.forEach(function(seat){
				ctx.strokeRect(seat.x, seat.y, d, d);
				ctx.fillRect(seat.x, seat.y, d, d);
			});
			
		}
		

});
