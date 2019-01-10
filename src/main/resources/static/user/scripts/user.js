var app = angular.module('app');

app.controller('uCtrl', function($scope, $http, $window, friendService, userService) {
  
	var id;
	
	userService.getLogged().then(function(data) {
		console.log(data);
		$scope.user = data;
		id = data.id;
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
      
	/*friendService.getNonFriends(id).then(function(data) { 
		console.log(data);
		$scope.nonFriends = data;
	  });
	
	friendService.getFriends(id).then(function(data) { 
		console.log('friends');
		console.log(data);
		$scope.friends = data;
	  });
	
	friendService.getPendingRequests(id).then(function(data) { 
		console.log(data);
		$scope.pending = data;
	  });
	
	$scope.sendReq = function(friendId) {
		friendService.sendRequest(id, friendId).then(function(data) { 
			console.log(data);
		  });
	}
	
	$scope.acceptFriendReq = function(reqId) {
		friendService.acceptReq(reqId).then(function(data) { 
			console.log(data);
			$scope.friends.push(data);
			$scope.pending = $scope.pending.filter( req => req.sender.id == reqId );
			$scope.friends = $scope.friends.filter(f => $scope.pending.filter( req => req.sender.id == f.id));
		  });
	}

	$scope.declineFriendReq = function(reqId) {
		friendService.declineReq(reqId).then(function(data) { 
			console.log(data);
			//$scope.nonFriends.push(data.sender);
			$scope.pending = $scope.pending.filter( req => req.sender.id === reqId );
		  });
	}*/
      
  });