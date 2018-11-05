package isa.project.friendrelation;

import java.util.List;

import isa.project.user.User;

public interface FriendRelationService {

	List<FriendRelation> findFriends(Long id);
	
	void addFriend(User user1, User user2);
	
	User removeFriend(Long usersId, Long friendsId);
	
}
