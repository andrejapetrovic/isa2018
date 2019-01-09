package isa.project.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import isa.project.verification.TokenVerificationService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	private HttpSession httpSession;
	
	@Autowired
	private TokenVerificationService tokenVerificationService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(
			value = "details/{id}",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
		User user = userService.findById(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "update",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) throws Exception {
		Long userId = user.getId();
		User updatedUser = userService.updateUser(userId, user);
		return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get/friends/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getFriends(@PathVariable("userId") Long id) {
		List<User> friends = userService.findFriends(id);
		return friends;
	}
	
	@GetMapping(value = "/get/non/friends/{userId}")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getNonFriends(@PathVariable("userId") Long id) {
		User u = userService.findById(id);
		List<User> friends = userService.findFriends(id);
		List<User> nonFriends = (List<User>) userService.findAll();
		nonFriends.removeAll(friends);
		nonFriends.remove(u);
		return nonFriends;
	}
	
	@RequestMapping(
			value = "signup",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@RequestBody User user, HttpServletRequest request) throws Exception {
		if(!user.getPassword().equals(user.getPassword2()))
			return new ResponseEntity<Message>(new Message("Please enter matching passwords"), HttpStatus.BAD_REQUEST);
		
		if(userService.findByEmail(user.getEmail()) != null) {
			return new ResponseEntity<Message>(new Message("User with that email already exists"), HttpStatus.BAD_REQUEST);
		}
		
		String verificationUrl =  request.getRequestURL().toString();
		//"http://localhost:8080/#!/activation";
		String split[] = verificationUrl.split("/");
		verificationUrl = split[0] + "//" + split[2] + "/#!/activation";
		logger.info(verificationUrl);
		tokenVerificationService.createAndSendVerification(user, verificationUrl);
		userService.addUser(user);
		return new ResponseEntity<Message>(
				new Message("Your account has been created but not verified yet, check your email for verification"), HttpStatus.OK);
	}
	
	@RequestMapping(
			value = "login",
			method = RequestMethod.POST,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginData) throws Exception {
		
        org.slf4j.Logger loggeri = LoggerFactory.getLogger(UserController.class);
        loggeri.info(loginData.getEmail());
        loggeri.info(loginData.getPassword());
 
        
		
		User user = userService.findByEmailAndPassword(loginData.getEmail(), loginData.getPassword());
		if(user == null)// iz nekog razloga frontend servis ne prima plain text pa kreiram objekat
			return new ResponseEntity<Message>(new Message("User with that email or password doesn't exist"), HttpStatus.UNAUTHORIZED);
		if(!user.isActivated())
			return new ResponseEntity<Message>(new Message("Your Account hasn't been activated, check your email for account activation"), HttpStatus.UNAUTHORIZED);

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginData.getEmail());
		for (GrantedAuthority r : userDetails.getAuthorities()) {
			logger.info(r.getAuthority().toString());
		}
		Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getUsername (),userDetails.getPassword (),userDetails.getAuthorities ());
		logger.info(auth.getName());
        SecurityContextHolder.getContext().setAuthentication(auth);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	
	@RequestMapping(
			value = "activation/{id}",
			method = RequestMethod.POST,
			produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> verifyUser(@PathVariable("id") String id) {
		return tokenVerificationService.verifyEmail(id);
	}
	
}
