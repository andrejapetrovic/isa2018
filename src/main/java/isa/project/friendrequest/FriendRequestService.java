package isa.project.friendrequest;

public interface FriendRequestService {

	void sendRequest(Long senderId, Long reciverId);
	
	FriendRequest acceptRequest(Long reqId);
	
	FriendRequest denyRequest(Long reqId);
	
}
