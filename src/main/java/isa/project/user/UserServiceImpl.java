package isa.project.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		user.setRole(Role.RegisteredUser);
		userRepository.save(user);
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

}
