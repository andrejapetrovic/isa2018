var app = angular.module('app');

app.controller('seatConfigCtrl', function($scope, $http, $window, $routeParams, segmentService, airplaneService) {

	var segmentId = "#economy-class-segments";
	var segments = [];
	var deletedSegments = [];
	var addedSegments = [];
	var updatedSegments = [];
	var segCount = 0;
	var planeId;
	
	airplaneService.getAirplane($routeParams.modelNumber, $routeParams.modelName).then(function(data){
		console.log(data);
		planeId = data.id;
		
		segmentService.getSegments(planeId).then(function(data) {
			data.forEach(function(segment) {
				if(segment.travelClass == 'Economy') segmentId = "#economy-class-segments";
				if(segment.travelClass == 'Business') segmentId = "#business-class-segments";
				  generateToolbar(++segCount);
				  var segView = new SegmentView(segment, segCount, segmentId);
				  segView.draw();
				  segments.push(segView);
				});
			console.log(segments);
			segmentId = "#economy-class-segments";
		});
		
		$scope.addSeg = function() {
			var rows = $scope.newSeg.rows, cols = $scope.newSeg.cols;
			if(rows <= 0 || cols <= 0) alert('Input cannot be 0 or a negative number'); 
			else if(isNaN(rows) || isNaN(cols)) alert('Input must be a number');
			else {
				generateToolbar(++segCount);
				if(segmentId == '#economy-class-segments') var travelClass = 'Economy';
				else if(segmentId == '#business-class-segments') var travelClass = 'Business';
				var segModel = {rows: rows, cols: cols, travelClass: travelClass};
				console.log(segModel);
				var s = new SegmentView(segModel, segCount, segmentId);
				s.draw();
				segments.push(s);
				addedSegments.push(s);
			}
		}
		
		$('#economy-tab').click(function() {
			segmentId = "#economy-class-segments";
		});
		$('#business-tab').click(function() {
			segmentId = "#business-class-segments";
		});
		
		
		function generateToolbar(n) {
			  var div = $('<div></div>').attr({ id: 'toolbar'+n});
			  $(segmentId).append(div);
			  var updateBtn =  $('<input/>').attr({ type: 'button', value:'update', id:'updateBtn'+n});
			  div.append(updateBtn);
			  var delSegBtn =  $('<input/>').attr({ type: 'button', value:'Delete Whole Segment', id:'delSegBtn'+n});
			  div.append(delSegBtn);
			  $('#delSegBtn'+n).on('click', generateDelBtnHandler(n));
			  $('#updateBtn'+n).on('click', generateUpdBtnHandler(n));
			  div.append('<br>');
		}
		
		function generateDelBtnHandler(i) {
		    return function(event) { 
				console.log(i);
				var seg = segments[i-1];
				if(!addedSegments.includes(seg)){
					deletedSegments.push(seg);
				}
				console.log('deleted segs'); 
				console.log(deletedSegments);
				$('#canvas'+i).remove();
				$('#toolbar'+i).remove();
		    };
		}
		
		function generateUpdBtnHandler(i) {
		    return function(event) { 
		    	$('#updSegModal').modal('show');
		    	$scope.selectedSeg = segments[i-1];
		    };
		}
		
		$('#submitUpdSeg').on('click', function(){
			var rows = $scope.updSeg.rows, cols = $scope.updSeg.cols;
			if(rows <= 0 || cols <= 0) alert('Input cannot be 0 or a negative number'); 
			else if(isNaN(rows) || isNaN(cols)) alert('Input must be a number');
			else {
				var seg = $scope.selectedSeg;
				console.log(seg);
				seg.update($scope.updSeg.rows, $scope.updSeg.cols);
				seg.draw();
				if(!addedSegments.includes(seg) && !updatedSegments.includes(seg)){
					updatedSegments.push(seg)
				}
				console.log(updatedSegments);
			}
		});
		
		$scope.saveChanges = function() {
			var data = {updated:updatedSegments, deleted:deletedSegments, added:addedSegments };
			console.log('saljem');
			console.log(data);
			segmentService.save(planeId, data).then(function(data){
				console.log("Your changes has been saved successfully");
				$window.location.reload();
			})
		}
		
	})
	
	
});
