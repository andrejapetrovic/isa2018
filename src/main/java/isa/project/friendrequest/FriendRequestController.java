package isa.project.friendrequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import isa.project.user.User;
import isa.project.user.UserService;

@RestController
@RequestMapping(value = "/friendReq")
public class FriendRequestController {

	@Autowired
	FriendRequestService frService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(
			value = "send",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String send(@RequestBody FriendRequestDto friendReq) {
		frService.sendRequest(friendReq.getSenderId(), friendReq.getReciverId());
		return "Your friend request has been sent to " + userService.findById(friendReq.getReciverId()).getName();
	}
	
	@RequestMapping(
			value = "/accept/{reqId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> accept(@PathVariable("reqId") Long id) {
		FriendRequest req = frService.acceptRequest(id);
		return new ResponseEntity<User>(req.getSender(), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "/deny/{reqId}",
			method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deny(@PathVariable("reqId") Long id) {
		FriendRequest req = frService.denyRequest(id);
		return new ResponseEntity<User>(req.getSender(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/pending/requests/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<FriendRequest> getPendingReq(@PathVariable("userId") Long id) {
		List<FriendRequest> pendingReq = frService.findByReqStatusAndReciverId(FriendRequestStatus.PENDING, id);
		return pendingReq;
	}
}
