package ca.sheridancollege.teixerya.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import ca.sheridancollege.teixerya.bean.Drink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;

@Component
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;
	

    


			@Autowired
			private TemplateEngine templateEngine;

			public void sendEmailWithThymeleaf(String to, String subject,
											   String name, ArrayList<Drink> drinks, String footer) throws MessagingException {
    
				//Assign the attributes for the email
				//We treat context like Model and setAttribute
				//import org.thymeleaf.context
				// Prepare the evaluation context
				final Context ctx = new Context();
				ctx.setVariable("name", name);
				ctx.setVariable("drinks", drinks);
				ctx.setVariable("footer", footer);
	
				//Prepare the email message body using the email template
				final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				
				String htmlContent = null;
				try {
					htmlContent = this.templateEngine.process("emailTemplate.html", ctx);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					
				}	
				
				message.setSubject(subject);
				message.setTo(to);				
				message.setText(htmlContent, true);
				
				this.emailSender.send(mimeMessage);
				
			}
			
			
}











