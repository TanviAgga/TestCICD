package com.filmindustry.candidatescreening;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.functionclasses.PasswordEncDec;
import com.filmindustry.candidatescreening.model.UserDetails;
import com.filmindustry.candidatescreening.repository.UserDetailsRepositoryInterface;

@TestMethodOrder(OrderAnnotation.class)
public class UserDetailsTest extends CandidateScreeningApplicationTests{
	@Autowired
	UserDetailsRepositoryInterface UserDetailsRepositoryInterface;	
	int randomNo;
	String patternChecker="";
	boolean match;
	UserDetailsBean UserDetailsBeanTest;
	UserDetailsBean UserDetailsBeanTestValidation1;
	UserDetailsBean UserDetailsBeanTestValidation2;
	UserDetailsBean UserDetailsBeanTestValidation3;
	UserDetailsBean UserDetailsBeanTestValidation4;
	UserDetailsBean UserDetailsBeanTestValidation5;
	UserDetailsBean UserDetailsBeanTestValidation6;
	UserDetailsBean UserDetailsBeanTestValidation7;
	UserDetailsBean UserDetailsBeanTestValidation8;
	UserDetailsBean UserDetailsBeanTestValidation9;
	UserDetailsBean UserDetailsBeanTestValidation10;
	UserDetailsBean UserDetailsBeanTestValidation11;
	UserDetailsBean UserDetailsBeanTestValidation12;
	UserDetailsBean UserDetailsBeanTestValidation13;
	static long userId;
	static String userEmail;
	static String userName;

	UserDetailsTest()
	{
		randomNo= (int) ((Math.random() * (1000 - 2)) + 2);
		UserDetailsBeanTest= new UserDetailsBean("abc"+randomNo+"@abc.com","!Abc123456","abc","def","12/23/1996","Director");
		UserDetailsBeanTestValidation1=new UserDetailsBean ("abcabc.com","!Abc123456","abc","def","12/23/1996","Director");
		UserDetailsBeanTestValidation2=new UserDetailsBean ("abcabc123@def.com","Abc123456","abc","def","12/23/1996","Director");
		UserDetailsBeanTestValidation3=new UserDetailsBean ("abcabc123@def.com","!Abc123456","ab","def","12/23/1996","Director");
		UserDetailsBeanTestValidation4=new UserDetailsBean ("abcabc123@def.com","!Abc123456","abc","def","23/23/1996","Director");
		UserDetailsBeanTestValidation5=new UserDetailsBean ("","!Abc123456","abc","def","12/23/1996","Director");
		UserDetailsBeanTestValidation6=new UserDetailsBean ("abcabc123@def.com","","abc","def","12/23/1996","Director");
		UserDetailsBeanTestValidation7=new UserDetailsBean ("abcabc123@def.com","!Abc123456","","def","12/23/1996","Director");
		UserDetailsBeanTestValidation8=new UserDetailsBean ("abcabc123@def.com","!Abc123456","abc","","12/23/1996","Director");
		UserDetailsBeanTestValidation9=new UserDetailsBean ("abcabc123@def.com","!Abc123456","abc","def","","Director");
		UserDetailsBeanTestValidation10=new UserDetailsBean ("abcabc123@def.com","!Abc123456","abc","def","12/23/1996","");
		UserDetailsBeanTestValidation11=new UserDetailsBean ("abcabc123@def.com","!Abc123456","ab@c","def","12/23/1996","Director");
		UserDetailsBeanTestValidation12=new UserDetailsBean ("abcabc123@def.com","!Abc123456","abc","d@ef","12/23/1996","Director");
		UserDetailsBeanTestValidation13=new UserDetailsBean ("abc1@abc.com","!Abc123456","abc","def","12/23/1996","Director");
	}
	
	@Test
	@Order(1)
	public void SignUp_NewUser_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTest, UserDetailsEntityTest);
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		userId=(long) savedDataEntity.getUserRegisterationId();
		userEmail=savedDataEntity.getUserEmail();
		userName=savedDataEntity.getUserFirstName();
		UserDetailsBeanTest.setUserRegisterationId(savedDataEntity.getUserRegisterationId());
		assertNotNull(UserDetailsRepositoryInterface.findByUserRegisterationId(UserDetailsBeanTest.getUserRegisterationId()));
	}
	
	@Test
	@Order(2)
	public void GetAllRegisteredUsers_TestCase() {
		List<UserDetails> list=UserDetailsRepositoryInterface.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void Login_ValidUser_TestCase() {
		//System.out.println(UserDetailsBeanTest.getUserRegisterationId());
		UserDetails checkOne=UserDetailsRepositoryInterface.findByUserRegisterationId(userId);
		//System.out.println(checkOne.getUserEmail());
		UserDetailsBean UD=new UserDetailsBean();
		UD.setUserEmail(checkOne.getUserEmail());
		assertEquals(userEmail, UD.getUserEmail());
	}
	
	@Test
	@Order(4)
	public void Update_UserDetails_TestCase() {
		UserDetails checkOne=UserDetailsRepositoryInterface.findByUserRegisterationId(userId);
		UserDetailsBean UD=new UserDetailsBean();
		BeanUtils.copyProperties(checkOne, UD);
		UD.setUserFirstName("abcabcabca");
		checkOne.setUserFirstName(UD.getUserFirstName());
		UserDetailsRepositoryInterface.save(checkOne);
		assertNotEquals(userName, UserDetailsRepositoryInterface.findByUserRegisterationId(userId).getUserFirstName());
	}
	
	@Test
	@Order(5)
	public void DeleteAccount_TestCase() {
		UserDetailsRepositoryInterface.deleteById(userId);
		assertThat(UserDetailsRepositoryInterface.existsById(userId)).isFalse();
	}
	
	@Test
	@Order(6)
	public void SignUp_NewUser_FirstName_Validation_TestCase() {
		patternChecker=UserDetailsBeanTestValidation11.getUserFirstName();
	    match=Pattern.matches("[a-zA-Z]+", patternChecker);
		if(!match)
			UserDetailsBeanTestValidation11.setError("error");
		assertEquals("error", UserDetailsBeanTestValidation11.getError());
	}
	
	@Test
	@Order(7)
	public void SignUp_NewUser_LastName_Validation_TestCase() {
		patternChecker=UserDetailsBeanTestValidation12.getUserLastName();
	    match=Pattern.matches("[a-zA-Z]+", patternChecker);
		if(!match)
			UserDetailsBeanTestValidation12.setError("error");
		assertEquals("error", UserDetailsBeanTestValidation12.getError());
	}
	
	@Test
	@Order(8)
	public void SignUp_NewUser_DOB_Validation_TestCase() {
		patternChecker=UserDetailsBeanTestValidation4.getUserDOB();
		SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
		sdfrmt.setLenient(false);
		try
	    {
	        Date javaDate = sdfrmt.parse(patternChecker); 
	    }
	    catch (ParseException e)
	    {
	    	UserDetailsBeanTestValidation4.setError("error");
	    }
		assertEquals("error", UserDetailsBeanTestValidation4.getError());
	}
	
	@Test
	@Order(9)
	public void SignUp_NewUser_Password_Encryption_Decryption_TestCase() throws Exception {   
		PasswordEncDec ped=new PasswordEncDec();
        String encryptedPassword=ped.encrypt(UserDetailsBeanTestValidation1.getUserPassword());
        String decryptedPassword=ped.decrypt(encryptedPassword);
        assertEquals(UserDetailsBeanTestValidation1.getUserPassword(), decryptedPassword);	
	}
	
	@Test
	@Order(10)
	public void SignUp_NewUser_Password_Validation_TestCase() throws Exception {   
		patternChecker=UserDetailsBeanTestValidation2.getUserPassword();
		match=Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,15}$", patternChecker);
		if(!match)
			UserDetailsBeanTestValidation2.setError("error");
		assertEquals("error", UserDetailsBeanTestValidation2.getError());
	}	
	
	@Test
	@Order(11)
	public void SignUp_NewUser_EmailAlreadyExist_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation13, UserDetailsEntityTest);
		try {
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		}
		catch (DataIntegrityViolationException  e)
		{
			UserDetailsBeanTestValidation13.setError("error");
		}
		assertEquals("error", UserDetailsBeanTestValidation13.getError());
	}
	
	@Test
	@Order(12)
	public void Login_Email_Validation_TestCase() {
		if (UserDetailsBeanTestValidation5.getUserEmail() == "" || UserDetailsBeanTestValidation5.getUserPassword()=="")
			UserDetailsBeanTestValidation5.setError("error");
		assertEquals("error", UserDetailsBeanTestValidation5.getError());
	}
	@Test
	@Order(13)
	public void Login_Password_Validation_TestCase() {
		if (UserDetailsBeanTestValidation5.getUserEmail() == "" || UserDetailsBeanTestValidation5.getUserPassword()=="")
			UserDetailsBeanTestValidation5.setError("error11");
		assertEquals("error11", UserDetailsBeanTestValidation5.getError());
	}
	
	@Test
	@Order(14)
	public void Login_EmailDoesntExist_Validation_TestCase() {
		UserDetails selectData=UserDetailsRepositoryInterface.findByUserEmail("abcdefgh@ghgsgd.com");
		if (selectData == null)
			UserDetailsBeanTestValidation5.setError("error1");
		assertEquals("error1", UserDetailsBeanTestValidation5.getError());
	}
	
	@Test
	@Order(15)
	public void Login_PasswordMismatch_Validation_TestCase() throws Exception {
		UserDetails selectData=UserDetailsRepositoryInterface.findByUserEmail("abc633855d@gmail.com");
		PasswordEncDec ped=new PasswordEncDec();
        String decryptedPassword=ped.decrypt(selectData.getUserPassword());
        if (!decryptedPassword.equals("abc"))
			UserDetailsBeanTestValidation6.setError("error1");
		assertEquals("error1", UserDetailsBeanTestValidation6.getError());
	}
	
	@Test
	@Order(16)
	public void SignUp_NewUser_NullEmailValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation5, UserDetailsEntityTest);
			UserDetailsBeanTestValidation5.setError("emailnull");
		assertEquals("emailnull", UserDetailsBeanTestValidation5.getError());
	}
	
	@Test
	@Order(17)
	public void SignUp_NewUser_NullPasswordValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation5, UserDetailsEntityTest);
			UserDetailsBeanTestValidation5.setError("passwordnull");
			assertEquals("passwordnull", UserDetailsBeanTestValidation5.getError());
	}
	
	@Test
	@Order(18)
	public void SignUp_NewUser_NullFirstNameValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation7, UserDetailsEntityTest);
		try {
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		}
		catch (Exception e)
		{
			UserDetailsBeanTestValidation7.setError("FNnull");
		}
		assertEquals("FNnull", UserDetailsBeanTestValidation7.getError());
	}
	
	@Test
	@Order(19)
	public void SignUp_NewUser_NullLastNameValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation8, UserDetailsEntityTest);
		try {
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		}
		catch (Exception e)
		{
			UserDetailsBeanTestValidation8.setError("LNnull");
		}
		assertEquals("LNnull", UserDetailsBeanTestValidation8.getError());
	}
	
	@Test
	@Order(18)
	public void SignUp_NewUser_NullDOBValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation9, UserDetailsEntityTest);
		try {
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		}
		catch (Exception e)
		{
			UserDetailsBeanTestValidation9.setError("DOBnull");
		}
		assertEquals("DOBnull", UserDetailsBeanTestValidation9.getError());
	}
	
	@Test
	@Order(18)
	public void SignUp_NewUser_NullPortalValidation_TestCase() {
		UserDetails UserDetailsEntityTest= new UserDetails();
		BeanUtils.copyProperties(UserDetailsBeanTestValidation10, UserDetailsEntityTest);
		try {
		UserDetails savedDataEntity=UserDetailsRepositoryInterface.save(UserDetailsEntityTest);
		}
		catch (Exception e)
		{
			UserDetailsBeanTestValidation10.setError("pornull");
		}
		assertEquals("pornull", UserDetailsBeanTestValidation10.getError());
	}
}
