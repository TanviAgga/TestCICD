package com.filmindustry.candidatescreening.functionclasses;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.filmindustry.candidatescreening.bean.ApplicantPortalBean;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.model.DirectorPortal;
import com.filmindustry.candidatescreening.model.UserDetails;
import com.filmindustry.candidatescreening.repository.DirectorPortalRepositoryInterface;
import com.filmindustry.candidatescreening.repository.UserDetailsRepositoryInterface;

public class Email {
	@Autowired
	DirectorPortalRepositoryInterface RepositoryIntrOBJ;
	public void sendSelectedEmail(DirectorPortal dp, ApplicantPortalBean source, HttpServletRequest request)
	{
			UserDetails entity=null;
			UserDetailsBean beanreturn=null;
			String host = "smtp.mail.yahoo.com";
			Properties prop = new Properties();
			final String username = "castit651project@yahoo.com";
		    final String password = "rnewmrgfwurlxcgv"; //  Group@12345
			prop.put("mail.smtp.host", "smtp.mail.yahoo.com");
		    prop.put("mail.smtp.port", "587");
		    prop.put("mail.smtp.auth", "true");
		    prop.put("mail.smtp.starttls.enable", "true");
			Session session = Session.getInstance(prop,
		            new javax.mail.Authenticator() {
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication(username, password);
		                }
		            });
			try
			{
			
				Message message = new MimeMessage(session);
		        message.setFrom(new InternetAddress(username));
		        message.setRecipients(
		                Message.RecipientType.TO,
		                InternetAddress.parse(source.getUserEmail())
		        );
		        message.setSubject("Selected for Movie "+dp.getMovieName());
			message.setText("Hi "+source.getUserFirstName()+",\nCongratulations!\n\n"+"You are selected "
					+ "for the role: "+dp.getRole()+"\n Movie:  "+dp.getMovieName()+"\n\n Please Revert from your registered Email Id "
							+ "If you are interested.\n\n regards, \nCastit");
		        Transport transport = session.getTransport("smtp");
				transport.connect(host, username, password);
				
				transport.sendMessage(message, message.getAllRecipients());
				
		        //Transport.send(message);
		        //modelAndView.addObject("successMessage", "A password reset link has been sent to " + user.getUserEmail());
		        System.out.println("Done");
		
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
	
}
