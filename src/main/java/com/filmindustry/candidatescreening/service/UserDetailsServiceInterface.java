package com.filmindustry.candidatescreening.service;

import java.util.List;

import com.filmindustry.candidatescreening.bean.ApplicantPortalBean;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.model.UserDetails;

public interface UserDetailsServiceInterface {

	UserDetailsBean saveUsers( UserDetailsBean user);
	UserDetailsBean loginUsers(String email,String password);
	UserDetailsBean deleteUser(long id);
	UserDetailsBean updateUser(String email,UserDetailsBean user);
	List<UserDetailsBean> getFeasableCandidates1(long formId, String percentage);
}
