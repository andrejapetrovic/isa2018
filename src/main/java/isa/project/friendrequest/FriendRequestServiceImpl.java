package isa.project.friendrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.project.friendrelation.FriendRelationService;
import isa.project.user.UserService;

@Service
public class FriendRequestServiceImpl implements FriendRequestService{

	@Autowired
	FriendRequestRepository frRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	FriendRelationService friendRelSerivce;
	
	@Override
	public void sendRequest(Long senderId, Long reciverId) {
		FriendRequest req = new FriendRequest();
		req.setSender(userService.findById(senderId));
		req.setReciver(userService.findById(reciverId));
		req.setReqStatus(FriendRequestStatus.PENDING);
		frRepo.save(req);
	}

	@Override
	public FriendRequest acceptRequest(Long reqId) {
		FriendRequest request = frRepo.getOne(reqId);
		request.setReqStatus(FriendRequestStatus.ACCEPTED);
		friendRelSerivce.addFriend(request.getSender(), request.getReciver());
		return request;
	}

	@Override
	public FriendRequest denyRequest(Long reqId) {
		FriendRequest request = frRepo.getOne(reqId);
		request.setReqStatus(FriendRequestStatus.DENIED);
		return request;
	}

}
