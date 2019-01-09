package isa.project.verification;

public interface EmailService {

	boolean sendEmail(String toEmail,  String verificationCode, String url);
	
}
