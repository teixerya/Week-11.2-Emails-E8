package ca.sheridancollege.teixerya.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class EmailServiceImpl {

    @Autowired
    public JavaMailSender emailSender;
	
	//Destination (To)
    //Message Body
    //Subject
    
    public void sendSimpleMessage(String to, String subject, String message) {
    	        SimpleMailMessage simpleMessage = new SimpleMailMessage(); 
    	        simpleMessage.setTo(to); 
    	        simpleMessage.setSubject(subject); 
    	        simpleMessage.setText(message);
    	        emailSender.send(simpleMessage);
			}

			@Autowired
	private TemplateEngine templateEngine;

			public void sendEmailWithThymeleaf(String to, String subject, 
    		String name, String messageBody, String footer) throws MessagingException {
    
				//Assign the attributes for the email
				//We treat context like Model and setAttribute
				//import org.thymeleaf.context
				// Prepare the evaluation context
				final Context ctx = new Context();
				ctx.setVariable("name", name);
				ctx.setVariable("message", messageBody);
				ctx.setVariable("footer", footer);
	
				//Prepare the email message body using the email template
				final MimeMessage mimeMessage = this.emailSender.createMimeMessage();
				final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8"); 
				final String htmlContent = this.templateEngine.process("emailTemplate.html", ctx);	
				
				message.setSubject(subject);
				message.setTo(to);				
				message.setText(htmlContent, true);
				
				this.emailSender.send(mimeMessage);
				
			}
			
			
}











