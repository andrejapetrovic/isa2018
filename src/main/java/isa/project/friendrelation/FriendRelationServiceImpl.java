package isa.project.friendrelation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isa.project.user.User;

@Service
public class FriendRelationServiceImpl implements FriendRelationService {

	@Autowired
	FriendRelationRepository frRepo;
	@Override
	public List<FriendRelation> findFriends(Long id) {
		return frRepo.findByUser1IdOrUser2Id(id, id);
	}
	
	@Override
	public void addFriend(User user1, User user2) {
		FriendRelation friendRel = new FriendRelation();
		friendRel.setUser1(user1);
		friendRel.setUser2(user2);
		frRepo.save(friendRel);
	}

	@Override
	public User removeFriend(Long usersId, Long friendsId) {
		// TODO Auto-generated method stub
		return null;
	}


}
