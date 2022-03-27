package com.filmindustry.candidatescreening.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.filmindustry.candidatescreening.model.ApplicantPortal;
import com.filmindustry.candidatescreening.model.UserDetails;

@Repository
public interface UserDetailsRepositoryInterface extends JpaRepository<UserDetails, Long>{

	UserDetails findByUserEmail(String email);
	UserDetails findByUserRegisterationId(long id);
	
	@Modifying
	@Query(nativeQuery = true,value = "SELECT a.*,b.* "
			+ "FROM castit_user_login_signup a inner join castit_applicant_role_form b "
			+ "on a.registration_id=b.registration_id where b.FORM_ID=:formId")
	List<UserDetails> getFeasableCandidates1(@Param("formId") long formId);
}
