package isa.project.verification;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import isa.project.user.User;
import isa.project.user.UserRepository;
import isa.project.user.UserService;

@Service
public class TokenVerificationServiceImpl implements TokenVerificationService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	EmailService emailService;

	@Autowired
	TokenVerificationRepository tokenVerificationRepository;
	
	@Autowired
	UserService userService;

	@Override
	public void createAndSendVerification(User user, String url) {
        List<VerificationToken> verificationTokens = tokenVerificationRepository.findByUserEmail(user.getEmail());
        VerificationToken verificationToken;
        if (verificationTokens.isEmpty()) {
            verificationToken = new VerificationToken();
            verificationToken.setUser(user);
            tokenVerificationRepository.save(verificationToken);
        } else {
            verificationToken = verificationTokens.get(0);
        }

        emailService.sendEmail(user.getEmail(), verificationToken.getToken(), url.concat("/" + verificationToken.getToken()));
	}

	@Override
	public ResponseEntity<String> verifyEmail(String token) {
        List<VerificationToken> verificationTokens = tokenVerificationRepository.findByToken(token);
        if (verificationTokens.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }

        VerificationToken verificationToken = verificationTokens.get(0);
        if (verificationToken.getExpiredDateTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        verificationToken.setConfirmedDateTime(LocalDateTime.now());
        verificationToken.setStatus(VerificationToken.STATUS_VERIFIED);
        verificationToken.getUser().setActivated(true);
        tokenVerificationRepository.save(verificationToken);

        return ResponseEntity.ok(verificationToken.getUser().getName() + ", "
        		+ "you have successfully verified your email address.");
    }
}
