var app = angular.module('app');

app.controller('seatConfigCtrl', function($scope, $http, $window, $stateParams, airplaneService, cabinService) {

		airplaneService.getAirplane($stateParams.id).then(function(data){
			$scope.data = data;
			$scope.aircraft = data.aircraft;
			console.log($scope.data);
			var selectedCanvasId;
			var	cabins = {
						economy: {configured: false},
						business: {configured: false},
						premium: {configured: false},
						first: {configured: false}
				};
				
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
				var canvas = document.getElementById(selectedCanvasId);
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
					console.log(sepAtCols[a]);
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
				className = canvas.id.split('-')[0];
				var seats = [];
				for (i=0; i<rows; i++) {
					s = 0;
					for (j=0; j<cols; j++) {
						if(sepAtCols.includes(j)){
							//console.log(j);
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
				cabins[className] = {
					flightClass: className,
					row: rows,
					col: cols,
					dx: dx,
					dy: dy,
					d: d,
					separations: $scope.sep,
					separationsSize: $scope.sepSize,
					seats: seats,
					aircraftId: $scope.aircraft.id
				}
				
				var saveBtn = $('<button/>')
			    .text('Save')
			    .click(function () {
			    	$(this).next().remove();
			    	$(this).remove();
			    	cabins[className].configured = true;
			    	cabinService.addCabin(cabins[className]).then(function(data){
			    		$("#" + className).prop('disabled', true);
			    		$("#create-seats").prop('disabled', true);
			    	});
			    })
			    var txt = $('<span/>').text(' Once you save you will no longer be able to make changes to this cabin');
				$("#"+selectedCanvasId).parent().prepend(txt).prepend(saveBtn).prepend('<br>');
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
			
			function createTab(tabId) {
				var id = $(".n av-tabs").children().length;
				var name = tabId.split('-')[0];
				name = name.replace(name[0], name[0].toUpperCase());
				$(".anchor").closest('li').before('<li><a href="#' + tabId + '" id="' + tabId + '-tab">'+ name + '</a></li>');
				$('.tab-content').append('<div class="tab-pane" id="' + tabId + '"><h3>'+name+' cabin</h3></div>');
				$('.nav-tabs li:nth-child(' + id + ') a').click();
				
				var canvas = createCanvas(tabId);
			}
			
			$(".nav-tabs").on("click", "a", function (e) {
				var cabinClass = $(this).attr('id').split('-')[0];
				$("#create-seats").prop("disabled", cabins[cabinClass].configured);
				e.preventDefault();
				$(this).tab('show');
				selectedCanvasId = $(this).attr('id').replace("-tab", "-canvas");
				console.log(selectedCanvasId);
			});
		});
		
		function drawSeats(seats, d) {
			var canvas = document.getElementById(selectedCanvasId);
			var ctx = canvas.getContext('2d');
			ctx.fillStyle = "#FFFFFF";
			ctx.strokeStyle = "#000000";
			seats.forEach(function(seat){
				ctx.strokeRect(seat.x, seat.y, d, d);
				ctx.fillRect(seat.x, seat.y, d, d);
			});
			
		}
		
		//nakon sto se stranica loaduje
		$scope.$on('$stateChangeSuccess', function () {
			$("#economy").click().trigger();
			console.log($scope.data);
			if($scope.data.cabins.length > 0) {
			$scope.data.cabins.forEach(function(cabin){
				var fclass = $scope.data.cabin.flightClass;
				var id = fclass.replace(fclass[0], fclass[0].toLowerCase()) + "-cabin";
				console.log(id);
				//createTab(id);
				//drawSeats((seats.filter(seat => seat.cabin.id != cabin.id)), cabin.d);
			});
		} 
		});
});
