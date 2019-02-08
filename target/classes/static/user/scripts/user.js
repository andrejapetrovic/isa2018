var app = angular.module('app');

app.controller('uCtrl', function($scope, $http, $window, friendService, userService, reservationService) {
  
	var id;
	
	userService.getLogged().then(function(data) {
		console.log(data);
		$scope.user = data;
		id = data.id;
		console.log(data.email);
		reservationService.getReservations(data.email).then(function(ress){
			$scope.reservations = ress;
			console.log($scope.reservations);
		});
		
	});
	
      $scope.edit = function (attr, $event) {
    	  var field = $('.' + attr);
    	  console.log($event);
    	  angular.element($event.currentTarget).hide();
    	  var updateDiv = jQuery('<div/>', {
    		    class: 'col-sm-5',
    		})
    	  var updateForm = $("<form></form>");
    	  var updateInput =$('<input>').attr({
    		    type: 'text',
    		    value: field.text()
    		})
    	  var updateBtn = $("<button type='submit'/>")
		    .text('Save')
		    .click(function () {
		    	field.text(updateInput.val());
		    	$scope.user[attr] = field.text();
		    	userService.updateUser($scope.user).then(function(data) {
		    		console.log(data);
		    		updateDiv.replaceWith(field);
		    		updateDiv.remove();
		    		angular.element($event.currentTarget).show();
		    	});
		    });
    	  updateForm.append(updateInput).append(updateBtn);
    	  updateDiv.append(updateForm);
    	  field.replaceWith(updateDiv);

      }
      
      $('#modal-btn').click( function(e){
    	  console.log('click');
    	  $('#exampleModal').modal('show');
      });
      
		$scope.formatDate = function(date) {
			var d = new Date(date);
			var retVal = d.toString();
			retVal = retVal.substring(0, retVal.indexOf(':')+3);
			return retVal;
		}
		$scope.parseType = function(aircraftType) {
			return aircraftType.split('_').join(' ');
		}
  });