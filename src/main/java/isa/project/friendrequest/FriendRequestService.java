package isa.project.friendrequest;

import java.util.List;

public interface FriendRequestService {

	void sendRequest(Long senderId, Long reciverId);
	
	FriendRequest acceptRequest(Long reqId);
	
	FriendRequest denyRequest(Long reqId);

	List<FriendRequest> findByReqStatusAndReciverId(FriendRequestStatus reqStatus, Long reciverId);
	
}
