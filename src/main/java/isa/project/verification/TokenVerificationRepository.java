package isa.project.verification;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenVerificationRepository extends JpaRepository<VerificationToken, String> {
    List<VerificationToken> findByUserEmail(String email);
    List<VerificationToken> findByToken(String token);
}
