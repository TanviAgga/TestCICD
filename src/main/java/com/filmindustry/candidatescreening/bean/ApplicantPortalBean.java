package com.filmindustry.candidatescreening.bean;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicantPortalBean {
	
		private long formId;
		
		private long applicantFormId;
		
		private long userRegisteredId;
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
		private String userEmail;
		
		private String userPassword;
		 
		private	String userFirstName;
		
		private String userLastName;
		
		private String shortlistingStatus;
		
		private String profilePic;
		
		private boolean checked;
		String message;
		String error;
		private String percentageMatch;
		private UserDetailsBean userDetailsBean;
		private DirectorPortalBean directorPortalBeanList;
		
		public ApplicantPortalBean() {
			// TODO Auto-generated constructor stub
		}
		public ApplicantPortalBean(String error) {
			this.error=error;
		}
		public ApplicantPortalBean(String error,String message) {
			this.error=error;
			this.message=message;
		}
		public ApplicantPortalBean(long userRegisteredId, long formId, String roleDescription, String characteristics1, String characteristics2,
				String characteristics3, String characteristics4, String characteristics5) {
			super();
			this.formId=formId;
			this.userRegisteredId = userRegisteredId;
			this.characteristics1 = characteristics1;
			this.characteristics2 = characteristics2;
			this.characteristics3 = characteristics3;
			this.characteristics4 = characteristics4;
			this.characteristics5 = characteristics5;
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

		public long getApplicantFormId() {
			return applicantFormId;
		}

		public void setApplicantFormId(long applicantFormId) {
			this.applicantFormId = applicantFormId;
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
		
		public String getPercentageMatch() {
			return percentageMatch;
		}
		public void setPercentageMatch(String percentageMatch) {
			this.percentageMatch = percentageMatch;
		}
		public DirectorPortalBean getDirectorPortalBeanList() {
			return directorPortalBeanList;
		}
		public void setDirectorPortalBeanList(DirectorPortalBean directorPortalBeanList) {
			this.directorPortalBeanList = directorPortalBeanList;
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
		public String getShortlistingStatus() {
			return shortlistingStatus;
		}
		public void setShortlistingStatus(String shortlistingStatus) {
			this.shortlistingStatus = shortlistingStatus;
		}
		public String getProfilePic() {
			return profilePic;
		}
		public void setProfilePic(String profilePic) {
			this.profilePic = profilePic;
		}
		public boolean isChecked() {
			return checked;
		}
		public void setChecked(boolean checked) {
			this.checked = checked;
		}
		
		
		
		
	}
