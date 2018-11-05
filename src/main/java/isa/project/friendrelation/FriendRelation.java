package isa.project.friendrelation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import isa.project.user.User;

@Entity	
public class FriendRelation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "friend_rel_id")
	private Long id;
	
	@OneToOne
	private User user1;	
	
	@OneToOne
	private User user2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}
	
	
	
}
