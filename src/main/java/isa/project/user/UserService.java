package isa.project.user;

import java.util.Collection;


public interface UserService {

	User findById(Long id);
	
	User findByEmail(String email);
	
	User findByEmailAndPassword(String email, String password);
	
	User addUser(User user);
	
	User updateUser(Long id, User updating);

	Collection<User> findAll();	
	
}
