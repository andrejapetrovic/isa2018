package isa.project.friendrelation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import isa.project.friendrequest.FriendRequest;
import isa.project.friendrequest.FriendRequestRepository;
import isa.project.friendrequest.FriendRequestStatus;
import isa.project.user.User;
import isa.project.user.UserRepository;
import isa.project.user.UserService;

@RestController
@RequestMapping(value = "/friend")
public class FriendRelationController {

	@Autowired
	UserService userService;	
	
	@Autowired
	FriendRequestRepository friendReqService;
	
	@Autowired
	FriendRelationService friendRelSerivce;
	
	@Autowired
	UserRepository userRepo;
	
	@GetMapping(value = "/get/friends/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getFriends(@PathVariable("userId") Long id) {
		List<User> friends = userRepo.findFriends(id);
		return friends;
	}
	
	@GetMapping(value = "/get/non/friends/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getNonFriends(@PathVariable("userId") Long id) {
		User u = userService.findById(id);
		List<User> friends = userRepo.findFriends(id);
		List<User> nonFriends = (List<User>) userService.findAll();
		nonFriends.removeAll(friends);
		nonFriends.remove(u);
		return nonFriends;
	}
	
	@GetMapping(value = "/get/pending/requests/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<FriendRequest> getPendingReq(@PathVariable("userId") Long id) {
		List<FriendRequest> pendingReq = friendReqService.findByReqStatusAndReciverId(FriendRequestStatus.PENDING, id);
		return pendingReq;
	}
	
}
