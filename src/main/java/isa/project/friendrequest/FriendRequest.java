package isa.project.friendrequest;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import isa.project.user.User;

@Entity
public class FriendRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -82437562659938725L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="friend_req_id")
	private Long id;
	
	@OneToOne
	private User sender;
	
	@OneToOne
	private User reciver;
	
	private FriendRequestStatus reqStatus;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReciver() {
		return reciver;
	}

	public void setReciver(User reciver) {
		this.reciver = reciver;
	}

	public FriendRequestStatus getReqStatus() {
		return reqStatus;
	}

	public void setReqStatus(FriendRequestStatus reqStatus) {
		this.reqStatus = reqStatus;
	}

	
	
}
