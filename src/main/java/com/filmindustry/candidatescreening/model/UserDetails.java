package com.filmindustry.candidatescreening.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="CASTIT_USER_LOGIN_SIGNUP")
public class UserDetails {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "REGISTRATION_ID")
	private long userRegisterationId;
	
	@Column(unique=true,name= "USER_EMAIL")
	private String userEmail;
	
	@Column(name= "USER_PASSWORD")
	private String userPassword;
	
	@Column(name= "USER_FIRST_NAME")
	private	String userFirstName;
	
	@Column(name= "USER_LAST_NAME")
	private String userLastName;
	
	@Column(name= "USER_DOB")
	private String userDOB;
	
	@Column(name= "REGISTER_AS")
	private String userRegistereAs;
    
//	@OneToOne(cascade = CascadeType.ALL,fetch=FetchType.LAZY)
//    @JoinColumn(name = "REGISTRATION_ID", referencedColumnName = "REGISTRATION_ID")
//	private ApplicantPortal applicantPortal1;
	
	public UserDetails(String userEmail, String userPassword, String userFirstName, String userLastName, String userDOB,
			String userRegistereAs) {
		super();
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDOB = userDOB;
		this.userRegistereAs = userRegistereAs;
	}
	
	public UserDetails()
	{}

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

//	public ApplicantPortal getApplicantPortal1() {
//		return applicantPortal1;
//	}
//
//	public void setApplicantPortal1(ApplicantPortal applicantPortal1) {
//		this.applicantPortal1 = applicantPortal1;
//	}

	



	
}
