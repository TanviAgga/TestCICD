package com.filmindustry.candidatescreening.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.functionclasses.PasswordEncDec;
import com.filmindustry.candidatescreening.model.UserDetails;
import com.filmindustry.candidatescreening.repository.UserDetailsRepositoryInterface;
import com.filmindustry.candidatescreening.service.UserDetailsServiceInterface;
import com.filmindustry.candidatescreening.serviceimpl.UserDetailsService;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api/v1/userdetails/")
public class UserController {
	
	
	@Autowired
	UserDetailsRepositoryInterface UserDetailsRepositoryIntrOBJ;
	//TA

	@Autowired
	private UserDetailsServiceInterface UDServiceInter;
	
	@PostMapping("adduser")
	public ResponseEntity<UserDetailsBean> saveUsers(@Valid @RequestBody UserDetailsBean user) {
		return new ResponseEntity<UserDetailsBean>(UDServiceInter.saveUsers(user),HttpStatus.CREATED);
	}
	
//	@GetMapping("checkuser")
//	public ResponseEntity<UserDetailsBean> loginUsers(HttpServletRequest request,HttpServletResponse response) {
//		return new ResponseEntity<UserDetailsBean>(UDServiceInter.loginUsers(request.getParameter("userEmail"),request.getParameter("userPassword")),HttpStatus.OK);
	//}
	@PostMapping("checkuser")
	public ResponseEntity<UserDetailsBean> loginUsers(@RequestBody UserDetailsBean user) {
		return new ResponseEntity<UserDetailsBean>(UDServiceInter.loginUsers(user.getUserEmail(),user.getUserPassword()),HttpStatus.OK);
	}
	
	@DeleteMapping("deleteuser")
	public ResponseEntity<UserDetailsBean> deleteUser(@RequestBody UserDetailsBean user) {
		return new ResponseEntity<UserDetailsBean>(UDServiceInter.deleteUser(user.getUserRegisterationId()),HttpStatus.OK);
	}
	
	@PutMapping("updateuser")
	public ResponseEntity<UserDetailsBean> updateUser(@Valid @RequestBody UserDetailsBean user) {
		return new ResponseEntity<UserDetailsBean>(UDServiceInter.updateUser(user.getUserEmail(),user),HttpStatus.OK);
	}
	
	@PostMapping("forgot")
	public UserDetailsBean processForgotPasswordForm(@RequestBody UserDetailsBean user, HttpServletRequest request) {
		UserDetailsBean bean=null;
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
		entity=new UserDetails();
	    bean=new UserDetailsBean();
	    entity.setUserEmail(user.getUserEmail());
		// Lookup user in database by e-mail
		UserDetails selectemail  = UserDetailsRepositoryIntrOBJ.findByUserEmail(entity.getUserEmail());
		if (selectemail == null) {
			//modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
			//return UserDetailsBean ud=new UserDetails("");
			System.out.println("Invalid email");
			return new UserDetailsBean("We didn't find an account for that e-mail address.");
			
		}
		else 
		{
			UserDetails previousData=UserDetailsRepositoryIntrOBJ.findByUserEmail(user.getUserEmail());
			//previousData.setResetToken(UUID.randomUUID().toString());
			//UserDetails savedData=UserDetailsRepositoryIntrOBJ.save(previousData);
			BeanUtils.copyProperties(previousData, bean);
			PasswordEncDec ped=new PasswordEncDec();
	        String decryptedPassword=ped.decrypt(bean.getUserPassword());
			//String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort();
			Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(bean.getUserEmail())
            );
            message.setSubject("Recovered login credentials of the user");
		message.setText("Hi "+bean.getUserFirstName()+",\nPlease find below your credentials:\n"+"User Email: "+bean.getUserEmail()+"\n Password:  "+decryptedPassword);
            Transport transport = session.getTransport("smtp");
    		transport.connect(host, username, password);
    		
    		transport.sendMessage(message, message.getAllRecipients());
    		
            //Transport.send(message);
            //modelAndView.addObject("successMessage", "A password reset link has been sent to " + user.getUserEmail());
            System.out.println("Done");
             beanreturn=new UserDetailsBean(null, "Success:A password reset link has been sent to " + user.getUserEmail());

	        
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return beanreturn;
	}
	
}
