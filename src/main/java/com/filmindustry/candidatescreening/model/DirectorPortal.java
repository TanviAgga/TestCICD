package com.filmindustry.candidatescreening.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.OneToOne;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name= "CASTIT_DIRECTOR_ROLE_FORM")
//@NamedStoredProcedureQueries({
//	  @NamedStoredProcedureQuery(name = "DirectorPortal.getNonAppliedPostings", 
//      procedureName = "CASTIT_PROC_FIND_NONAPPLIED_POSTINGS",resultClasses = DirectorPortal.class,
//      parameters = {
//         @StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class, name="USER_REGISTRATION_ID"),
//         @StoredProcedureParameter(mode = ParameterMode.OUT, type = DirectorPortal.class, name="OUTPUT_DATA")
//      })})
public class DirectorPortal {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "FORM_ID")
	private long formId;
	
	@Column(name= "REGISTRATION_ID")
	private long userRegisteredId;
	
	@Column(name= "MOVIE_NAME")
	private String movieName;
	
	@Column(name= "MOVIE_DESC")
	private String movieDesc;
	
	@Column(name= "MOVIE_STATUS")
	private String movieStatus;
	
	@Column(name= "MOVIE_GENRE")
	private String movieGenre;
	
	@Column(name= "ROLE")
	private String role;
	
	@Column(name= "ROLE_DESCRIPTION")
	private String roleDescription;
	
	@Column(name= "CHARACTERISTICS1")
	private String characteristics1;
	
	@Column(name= "CHARACTERISTICS2")
	private String characteristics2;
	
	@Column(name= "CHARACTERISTICS3")
	private String characteristics3;
	
	@Column(name= "CHARACTERISTICS4")
	private String characteristics4;
	
	@Column(name= "CHARACTERISTICS5")
	private String characteristics5;
	
	@Column(name= "ROLE_STATUS")
	private String roleStatus;
	
	@Column(name= "ROLE_GIVEN_TO")
	private String roleGivenTo;
	
	@Column(name= "ROLE_GIVEN_TO_ID")
	private long roleGivenToID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FORM_ID", referencedColumnName = "FORM_ID")
	private ApplicantPortal applicantPortal;
	
	public DirectorPortal() {
		// TODO Auto-generated constructor stub
	}

	public DirectorPortal(long userRegisteredId, String movieName, String movieDesc, String movieStatus,
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

	public ApplicantPortal getApplicantPortal() {
		return applicantPortal;
	}

	public void setApplicantPortal(ApplicantPortal applicantPortal) {
		this.applicantPortal = applicantPortal;
	}

	

	
	

}
