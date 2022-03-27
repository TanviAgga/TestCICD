package com.filmindustry.candidatescreening.bean;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorPortalBean {

	private long formId;
	
	private long userRegisteredId;
	@NotEmpty
	private String movieName;
	@NotEmpty
	private String movieDesc;
	
	private String movieStatus;
	@NotEmpty
	private String movieGenre;
	@NotEmpty
	private String role;
	@NotEmpty
	private String roleDescription;
	@NotEmpty
	private String characteristics1;
	@NotEmpty
	private String characteristics2;
	@NotEmpty
	private String characteristics3;
	@NotEmpty
	private String characteristics4;
	@NotEmpty
	private String characteristics5;
	
	private String roleStatus;
	
	private String roleGivenTo;
	
	private long roleGivenToID;
	
	private UserDetailsBean userDetailsBean;
	
	private ApplicantPortalBean applicantPortalBean;
	
	String message;
	String error;
	
	public DirectorPortalBean() {
		// TODO Auto-generated constructor stub
	}
	public DirectorPortalBean(String error) {
		this.error=error;
	}
	public DirectorPortalBean(String error,String message) {
		this.error=error;
		this.message=message;
	}
	public DirectorPortalBean(long userRegisteredId, String movieName, String movieDesc, String movieStatus,
			String movieGenre, String role, String roleDescription, String characteristics1, String characteristics2,
			String characteristics3, String characteristics4, String characteristics5, String roleStatus,
			String roleGivenTo, long roleGivenToID) {
		super();
		this.userRegisteredId = userRegisteredId;
		this.movieName = movieName;
		this.movieDesc = movieDesc;
		this.movieStatus = movieStatus;
		this.movieGenre = movieGenre;
		this.role = role;
		this.roleDescription = roleDescription;
		this.characteristics1 = characteristics1;
		this.characteristics2 = characteristics2;
		this.characteristics3 = characteristics3;
		this.characteristics4 = characteristics4;
		this.characteristics5 = characteristics5;
		this.roleStatus = roleStatus;
		this.roleGivenTo = roleGivenTo;
		this.roleGivenToID = roleGivenToID;
	}

	public long getFormId() {
		return formId;
	}

	public void setFormId(long formId) {
		this.formId = formId;
	}

	public long getUserRegisteredId() {
		return userRegisteredId;
	}

	public void setUserRegisteredId(long userRegisteredId) {
		this.userRegisteredId = userRegisteredId;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public String getMovieDesc() {
		return movieDesc;
	}

	public void setMovieDesc(String movieDesc) {
		this.movieDesc = movieDesc;
	}

	public String getMovieStatus() {
		return movieStatus;
	}

	public void setMovieStatus(String movieStatus) {
		this.movieStatus = movieStatus;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public String getCharacteristics1() {
		return characteristics1;
	}

	public void setCharacteristics1(String characteristics1) {
		this.characteristics1 = characteristics1;
	}

	public String getCharacteristics2() {
		return characteristics2;
	}

	public void setCharacteristics2(String characteristics2) {
		this.characteristics2 = characteristics2;
	}

	public String getCharacteristics3() {
		return characteristics3;
	}

	public void setCharacteristics3(String characteristics3) {
		this.characteristics3 = characteristics3;
	}

	public String getCharacteristics4() {
		return characteristics4;
	}

	public void setCharacteristics4(String characteristics4) {
		this.characteristics4 = characteristics4;
	}

	public String getCharacteristics5() {
		return characteristics5;
	}

	public void setCharacteristics5(String characteristics5) {
		this.characteristics5 = characteristics5;
	}

	public String getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleGivenTo() {
		return roleGivenTo;
	}

	public void setRoleGivenTo(String roleGivenTo) {
		this.roleGivenTo = roleGivenTo;
	}

	public long getRoleGivenToID() {
		return roleGivenToID;
	}

	public void setRoleGivenToID(long roleGivenToID) {
		this.roleGivenToID = roleGivenToID;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public UserDetailsBean getUserDetailsBean() {
		return userDetailsBean;
	}
	public void setUserDetailsBean(UserDetailsBean userDetailsBean) {
		this.userDetailsBean = userDetailsBean;
	}
	public ApplicantPortalBean getApplicantPortalBean() {
		return applicantPortalBean;
	}
	public void setApplicantPortalBean(ApplicantPortalBean applicantPortalBean) {
		this.applicantPortalBean = applicantPortalBean;
	}

	

}
