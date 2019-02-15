package isa.project.flight.reservation;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import isa.project.destination.Destination;
import isa.project.flight.Flight;
import isa.project.flight.seat.FlightSeat;


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
		
		Iterator<FlightSeat> fsIterator = res.getFlightSeat().iterator();
		int i = 1;
		while(fsIterator.hasNext()) {
		    FlightSeat fs = fsIterator.next();
		    Flight f = fs.getFlight();
		    sb.append("From: ").append(parseDestination(f.getFrom(), f.getTerminal1())).append("<br>")
		    .append("To:").append(parseDestination(f.getTo(), f.getTerminal2())).append("<br>")
		    .append("Departure date and time: ").append(f.getDepartureDate().toString()).append("<br>")
		    .append("Landing date and time: ").append(f.getLandingDate().toString()).append("<br>")
		    .append("Seat row and column: ").append(fs.getSeat().getRow()).append(" ").append(fs.getSeat().getCol())
		    .append(" Cabin: ").append(fs.getFlightClass()).append("<br>");
		    if(i==1) {
		    	sb.append("Price: ").append(res.getOneWayPrice())
		    	.append("<p>");
		    } else if (i == 2) {
		    	sb.append("Price: ").append(res.getReturnPrice())
		    	.append("<p>");
		    }
		    i++;
		}
		
		return sb.toString();
	}
	
	private String parseDestination(Destination dest, String terminal) {
		return dest.getCity() + " " + dest.getAirportCode() + ", airport " + dest.getAirport() + " Terminal: " + terminal + ", " + dest.getCountry();
	}
	
}
