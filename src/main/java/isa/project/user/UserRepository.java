package isa.project.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository  extends JpaRepository<User, Long> {
	
	User findByNameAndLastNameAllIgnoringCase(String name, String lastName);
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	User findById(Long id);
	

	@Query(value = "SELECT * FROM rezervacija.USER WHERE user_id IN" 
	+ "(SELECT friend_id FROM rezervacija.FRIEND_RELATION WHERE user_id = :id)"
	,nativeQuery = true)
	List<User> findFriends(@Param("id") Long id);
}
