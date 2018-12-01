var app = angular.module('app');

app.controller('seatConfigCtrl', function($scope, $http, $window, $routeParams, segmentService) {
	
	segmentService.getSegments($routeParams.planeId).then(function(data) {
		data.forEach(function(segment) {
			  if(segment.travelClass == 'Economy') segmentId = "#economy-class-segments";
			  if(segment.travelClass == 'Business') segmentId = "#business-class-segments";
			  var svg = createSegment(segment.rowCount, segment.colCount);
			  segmentId = "#economy-class-segments";
			});
	});
	
	var segmentId = "#economy-class-segments";
	var segments = [];
	
	/*$scope.addSegment = function() {
		var segmentGrid = createSegment();
	}*/
	
	var createSegment = function(rows, cols) {
	
	var squaresRow = rows;
	var squaresColumn = cols;
		
	var square = 30,
	  w = 1200,
	  h = cols*35;

	var addRowBtn =  $('<input/>').attr({ type: 'button', value:'Add Row' });
	$(segmentId).append(addRowBtn);
	var delRowBtn =  $('<input/>').attr({ type: 'button', value:'Delete Row' });
	$(segmentId).append(delRowBtn);
	var addColBtn =  $('<input/>').attr({ type: 'button', value:'Add Column' });
	$(segmentId).append(addColBtn);
	var delColBtn =  $('<input/>').attr({ type: 'button', value:'Delete Column' });
	$(segmentId).append(delColBtn);
	var delBtn =  $('<input/>').attr({ type: 'button', value:'Delete Selected' });
	$(segmentId).append(delBtn);
	var delSegBtn =  $('<input/>').attr({ type: 'button', value:'Delete Whole Segment' });
	$(segmentId).append(delSegBtn);
	$(segmentId).append('<br></br>');
	
	// create the svg
	var svg = d3.select(segmentId).append('svg')
	  .attr({
	    width: w,
	    height: h
	  });

	// loop over number of columns
	_.times(squaresColumn, function(n) {

	  // create each set of rows
	  var rows = svg.selectAll('rect' + ' .row-' + (n + 1))
	    .data(d3.range(squaresRow))
	    .enter().append('rect')
	    .attr({
	      class: function(d, i) {
	        return 'square row-' + (n + 1) + ' ' + 'col-' + (i + 1);
	      },
	      id: function(d, i) {
	        return 's-' + (n + 1) + (i + 1);
	      },
	      width: square,
	      height: square,
	      x: function(d, i) {
	        return i * square;
	      },
	      y: n * square,
	      fill: '#DDE0E5',
	      stroke: 'grey'
	    });
	    
	    rows.on('click', function(d) {
	    	var color = d3.select(this).style('fill');
	    	
	    	if(color == 'rgb(221, 224, 229)') {
	    		d3.select(this).attr('fill', '#75A3E7');
	    		}
	    	else if(color == 'rgb(255, 255, 255)') {
	    		d3.select(this).attr('fill', '#DDE0E5');
	    	}
	    	else
	    		d3.select(this).attr('fill', '#DDE0E5');
	    });
	    
	});
	
	addRowBtn.click(function() {
		
	});
	return svg;
	}
	
	$('#economy-tab').click(function() {
		segmentId = "#economy-class-segments";
	});
	$('#business-tab').click(function() {
		segmentId = "#business-class-segments";
	});
});