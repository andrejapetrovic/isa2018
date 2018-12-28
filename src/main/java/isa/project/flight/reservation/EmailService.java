package isa.project.flight.reservation;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;


@Service
public class EmailService {

	public boolean sendEmail(Reservation res) {
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
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(res.getEmail()));
            msg.setSubject("Flight ticket reservation");
            msg.setContent(createMsg(res), "text/html");
            
            Transport transport = session.getTransport();
            transport.connect(host, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            return true;
        } catch (Exception ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        return false;
	}
	
	private String createMsg(Reservation res) {
		StringBuilder sb = new StringBuilder("<p>You have succesfully made a flight reservation.<br>");
		/*sb.append("From: ").append(res.getFlight().getFrom()).append("<br>")
		.append("To:").append(res.getFlight().getTo()).append("<br>")
		.append("Departure date and time: ").append(res.getFlight().getDepartureDate()).append("<br>")
		.append("<p>");
		//dodati jos informacija na kraju*/
		return sb.toString();
	}
	
}
