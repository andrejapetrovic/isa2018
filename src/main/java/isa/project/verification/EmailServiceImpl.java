package isa.project.verification;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Override
	public boolean sendEmail(String toEmail, String verificationCode, String url) {
        try {
    		Properties properties = System.getProperties();
    		properties.setProperty("mail.smtp.auth", "true");
    		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
    		properties.setProperty("mail.smtp.port", "587");
    		properties.setProperty("mail.smtp.starttls.enable", "true");
    		
    		String host = "smtp.gmail.com";
    		String username = "isaprojekatverifikacija@gmail.com";
    		String password = "randomsifra";

            Session session = Session.getDefaultInstance(properties);
            session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject("Please verify your email");
            msg.setContent(url, "text/html");
            
            Transport transport = session.getTransport();
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
	}

}
