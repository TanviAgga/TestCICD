package com.filmindustry.candidatescreening.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.filmindustry.candidatescreening.model.ApplicantPortal;
import com.filmindustry.candidatescreening.model.DirectorPortal;


@Repository
public interface ApplicantPortalRepositoryInterface extends JpaRepository<ApplicantPortal, Long> {
	ApplicantPortal findByApplicantFormId(long appformId);
	
	@Modifying
	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b WHERE b.FORM_ID =:formId")
	List<ApplicantPortal> getApplicantListOnPostings(@Param("formId") long formId);
	
//	@Modifying
//	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b WHERE b.FORM_ID =:formId")
//	List<ApplicantPortal> getFeasableCandidates(@Param("formId") long formId);
	
	@Modifying
	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b WHERE b.FORM_ID =:formId and b.SHORTLISTING_STATUS is null")
	List<ApplicantPortal> getFeasableCandidates(@Param("formId") long formId);
	
//	@Modifying
//	@Query(nativeQuery = true,value = "SELECT\r\n"
//			+ "        a.USER_DOB as userDob,\r\n"
//			+ "        a.USER_EMAIL as userEmail,\r\n"
//			+ "        a.USER_FIRST_NAME as userFirstName,\r\n"
//			+ "        a.USER_LAST_NAME as userLastName, \r\n"
//			+ "        b.CHARACTERISTICS1 as characteristics1,\r\n"
//			+ "        b.CHARACTERISTICS2 as characteristics2,\r\n"
//			+ "        b.CHARACTERISTICS3 as characteristics3,\r\n"
//			+ "        b.CHARACTERISTICS4 as characteristics4,\r\n"
//			+ "        b.CHARACTERISTICS5 as characteristics5,\r\n"
//			+ "        b.PERCENTAGEMATCH as percentageMatch \r\n"
//			+ "    FROM\r\n"
//			+ "        castit_user_login_signup a inner join\r\n"
//			+ "        castit_applicant_role_form b \r\n"
//			+ "    on\r\n"
//			+ "        a.registration_id=b.registration_id \r\n"
//			+ "        where a.registration_id=b.registration_id and b.FORM_ID=:formId")
//	List<UserApplicantJoinInterface> getFeasableCandidates(@Param("formId") long formId);
	
	@Modifying
	@Query(nativeQuery = true,value = "Update castit_applicant_role_form b set b.PERCENTAGEMATCH=:percentageMatch WHERE b.APPLICANT_FORM_ID =:applicantFormId")
	void updatePercentage(@Param("applicantFormId") long applicantFormId,@Param("percentageMatch") String percentageMatch);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "delete from castit_applicant_role_form b WHERE b.APPLICANT_FORM_ID =:applicantFormId")
	void deleteByCustomId(@Param("applicantFormId") long applicantFormId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "Update castit_applicant_role_form b set b.SHORTLISTING_STATUS=:shortlistingStatus WHERE b.APPLICANT_FORM_ID =:applicantFormId")
	void getrightswipe(@Param("applicantFormId") long applicantFormId,@Param("shortlistingStatus") String shortlistingStatus);
	
	@Modifying
	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b WHERE b.SHORTLISTING_STATUS='Y' and b.APPLICANT_FORM_ID =:applicantFormId")
	List<ApplicantPortal> getrightswipedCandidates(@Param("applicantFormId") long applicantFormId);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "Update castit_applicant_role_form b set b.SHORTLISTING_STATUS=:shortlistingStatus WHERE b.APPLICANT_FORM_ID =:applicantFormId")
	void getFinalSelection(@Param("applicantFormId") long applicantFormId,@Param("shortlistingStatus") String shortlistingStatus);

	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "Update castit_director_role_form b set b.ROLE_GIVEN_TO=:rolesGivenTo,ROLE_STATUS='CLOSE' WHERE b.FORM_ID =:formId")
	void closeRole(@Param("rolesGivenTo") String rolesGivenTo, @Param("formId") long formId);

	@Modifying
	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b WHERE b.SHORTLISTING_STATUS='Y' and b.FORM_ID =:formId")
	List<ApplicantPortal> getrightswipedCandidates1(@Param("formId") long formId);

//	@Modifying
//	@Query(nativeQuery = true,value = "SELECT b.* FROM castit_applicant_role_form b.PERCENTAGEMATCH=:percentageMatch")
//	List<ApplicantPortal> getUpdatedListMatchedWithPercentage(@Param("percentageMatch") String percentageMatch);
}
