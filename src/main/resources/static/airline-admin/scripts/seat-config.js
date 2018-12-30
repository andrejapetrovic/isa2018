var app = angular.module('app');

app.controller('seatConfigCtrl', function($scope, $http, $window, $stateParams, airplaneService) {

		airplaneService.getAirplane($stateParams.id).then(function(data){
			$scope.aircraft = data;
			console.log(data);
			
			jQuery('<div/>', {
			    id: 'economy'
			}).appendTo('body');
			
			var canvas = document.createElement('canvas');
			var ctx = canvas.getContext('2d');
			$("#economy").append(canvas);
			$scope.rows = 40;
			$scope.cols = 10;
			$scope.sep = "3-4-3";
			$scope.sepSize = 40;
			$scope.addSeats = function() {
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
				var d = 20; // stranica kvadrata
				var rx = 10; // razmak po x
				var ry = 3;// razmak po y
				var s;
				canvas.width = (d+rx)*rows+rx;
				canvas.height = (d+ry)*cols+ry + sepAtCols.length*sepSize;
				ctx.fillStyle = "#FFFFFF";
				ctx.strokeStyle = "#000000";
				for (i=0; i<rows; i++) {
					s = 0;
					for (j=0; j<cols; j++) {
						if(sepAtCols.includes(j)){
							//console.log(j);
							s += sepSize;
						}
						//parametri x,y,duzina po x, duzina po y
						ctx.strokeRect(i*d+(i+1)*rx, j*d+(j+1)*ry+s, d, d);
						ctx.fillRect(i*d+(i+1)*rx,j*d+(j+1)*ry+s,d,d);
					}
				}
			}
			
		});
		
		
		$(".nav-tabs").on("click", "a", function (e) {
	        e.preventDefault();
	        if (!$(this).hasClass('add-contact')) {
	            $(this).tab('show');
	        }
	    })
	    .on("click", "span", function () {
	        var anchor = $(this).siblings('a');
	        $(anchor.attr('href')).remove();
	        $(this).parent().remove();
	        $(".nav-tabs li").children('a').first().click();
	    });

	$('.add-contact').click(function (e) {
	    e.preventDefault();
	    var id = $(".nav-tabs").children().length; //think about it ;)
	    var tabId = 'contact_' + id;
	    $(this).closest('li').before('<li><a href="#contact_' + id + '">New Tab</a> <span> x </span></li>');
	    $('.tab-content').append('<div class="tab-pane" id="' + tabId + '">Contact Form: New Contact ' + id + '</div>');
	   $('.nav-tabs li:nth-child(' + id + ') a').click();
	});
});
