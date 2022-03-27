package com.filmindustry.candidatescreening.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDetailsBean {
	private long userRegisterationId;
	
	@NotEmpty
	@Email(message="Please enter a valid Email")
	private String userEmail;
	
	@NotEmpty
	@JsonProperty(access = Access.WRITE_ONLY)
	@Size(min=8,max=15,message="Password should be minimum 8 and maximum 15 characters long")
	private String userPassword;
	 
	@NotEmpty
	@Size(min=3,message="First Name should be atleast 3 characters long")
	private	String userFirstName;
	
	@NotEmpty
	private String userLastName;
	
	@NotEmpty
	private String userDOB;
	
	@NotEmpty
	private String userRegistereAs;
	
	private String error;
	
	private String message;
	private ApplicantPortalBean applicantPortalBean;

	public UserDetailsBean()
	{
		
	}
	
	public UserDetailsBean(String errorMessage)
	{
		this.error = errorMessage;
	}
	
	public UserDetailsBean(String errorMessage, String message)
	{
		this.error = errorMessage;
		this.message=message;
	}
	
	public UserDetailsBean(@NotEmpty @Email(message = "Please enter a valid Email") String userEmail,
			@NotEmpty @Size(min = 8, message = "Password should be atleast 8 characters long") String userPassword,
			@NotEmpty @Size(min = 3, message = "First Name should be atleast 3 characters long") String userFirstName,
			@NotEmpty @Size(min = 3, message = "Last Name should be atleast 3 characters long") String userLastName,
			@NotEmpty String userDOB, @NotEmpty String userRegistereAs) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDOB = userDOB;
		this.userRegistereAs = userRegistereAs;
	}

	public long getUserRegisterationId() {
		return userRegisterationId;
	}

	public void setUserRegisterationId(long userRegisterationId) {
		this.userRegisterationId = userRegisterationId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserRegistereAs() {
		return userRegistereAs;
	}

	public void setUserRegistereAs(String userRegistereAs) {
		this.userRegistereAs = userRegistereAs;
	}
	

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApplicantPortalBean getApplicantPortalBean() {
		return applicantPortalBean;
	}

	public void setApplicantPortalBean(ApplicantPortalBean applicantPortalBean) {
		this.applicantPortalBean = applicantPortalBean;
	}
	
	
}
