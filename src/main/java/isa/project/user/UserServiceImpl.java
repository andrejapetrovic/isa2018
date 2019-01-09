package isa.project.user;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jersey.repackaged.com.google.common.collect.Sets;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}
	
	@Override
	public User findByEmail(String email) {
		//Assert.assertNotNull("Kriterijum ne sme biti null", email);
		User user = userRepository.findByEmail(email);
		return user;
	}

	@Override
	public User addUser(User user) {
		user = userRepository.save(user);
		return user;
	}

	@Override
	public Collection<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findByEmailAndPassword(String email, String password) {
		User user = userRepository.findByEmailAndPassword(email, password);
		return user;
	}

	@Override
	public User updateUser(Long id, User updated) {
		User user = userRepository.findById(id);
		user.setName(updated.getName());
		user.setLastName(updated.getLastName());
		user.setEmail(updated.getEmail());
		user.setCity(updated.getCity());
		user.setPhone(updated.getPhone());
		userRepository.save(user);
		return user;
	}

	@Override
	public List<User> findFriends(Long id) {
		return userRepository.findFriends(id);
	}

	@Override
	public void addFriend(User user1, User user2) {
		user1.getFriends().add(user2);
		user2.getFriends().add(user1);
		userRepository.save(user1);
	}

}
