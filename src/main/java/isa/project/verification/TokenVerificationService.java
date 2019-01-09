package isa.project.verification;
import org.springframework.http.ResponseEntity;

import isa.project.user.User;

public interface TokenVerificationService {

	void createAndSendVerification(User user, String url);
	
	ResponseEntity<String> verifyEmail(String token);
	
}
