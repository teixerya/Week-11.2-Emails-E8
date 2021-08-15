package ca.sheridancollege.teixerya.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.teixerya.email.EmailServiceImpl;

@Controller
public class HomeController {
	
	@Autowired
	private EmailServiceImpl esi;
	
	@GetMapping("/")
	public String goHome() {
		
		try {
		esi.sendEmailWithThymeleaf("ryantexappbackup@gmail.com", "Test Email", 
				"Ryan", "Meow meow", "Woof Woof");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return "home.html";
	}

}
