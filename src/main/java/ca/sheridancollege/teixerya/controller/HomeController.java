package ca.sheridancollege.teixerya.controller;

import javax.mail.MessagingException;

import ca.sheridancollege.teixerya.repository.DrinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ca.sheridancollege.teixerya.email.EmailServiceImpl;

import java.sql.SQLOutput;

@Controller
public class HomeController {
	
	@Autowired
	private EmailServiceImpl esi;

	@Autowired
	private DrinkRepository drinkRepo;
	
	@GetMapping("/")
	public String goHome() {
		//line 27 set the destination of my email
		try {
		esi.sendEmailWithThymeleaf("java.sheridan.2021@gmail.com", "Test Email",
				"Jonathan", drinkRepo.getDrinks(), "\nRegards,\n Ryan Teixeira");
		} catch (MessagingException e) {
			System.out.println(e.getMessage()); //what is happening
		}
		
		return "home.html";
	}

}
