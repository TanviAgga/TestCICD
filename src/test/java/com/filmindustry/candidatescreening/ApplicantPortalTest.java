package com.filmindustry.candidatescreening;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.filmindustry.candidatescreening.bean.ApplicantPortalBean;
import com.filmindustry.candidatescreening.bean.DirectorPortalBean;
import com.filmindustry.candidatescreening.model.ApplicantPortal;
import com.filmindustry.candidatescreening.model.DirectorPortal;
import com.filmindustry.candidatescreening.repository.ApplicantPortalRepositoryInterface;
import com.filmindustry.candidatescreening.repository.DirectorPortalRepositoryInterface;

@TestMethodOrder(OrderAnnotation.class)
public class ApplicantPortalTest extends CandidateScreeningApplicationTests{

	@Autowired
	private ApplicantPortalRepositoryInterface DPRInterface;
	ApplicantPortalBean DPB;
	static long applicantFormId;
	static String characteristic1;
	ApplicantPortalTest()
	{
		DPB= new ApplicantPortalBean(2, 1, "abc", "height 58", "weight 50",
				"color black", "asian", "abcdef");
	}
	
	@Test
	@Order(1)
	public void Applicant_ApplyingListing_Test() {
		ApplicantPortal ApplicantPortalEntityTest= new ApplicantPortal();
		BeanUtils.copyProperties(DPB, ApplicantPortalEntityTest);
		ApplicantPortal savedDataEntity=DPRInterface.save(ApplicantPortalEntityTest);
		applicantFormId=(long) savedDataEntity.getApplicantFormId();
		characteristic1=savedDataEntity.getCharacteristics1();
		DPB.setApplicantFormId(savedDataEntity.getApplicantFormId());
		assertNotNull(DPRInterface.findById(DPB.getApplicantFormId()));
	}
	
	@Test
	@Order(2)
	public void Applicant_GettingAllListings_Test() {
		List<ApplicantPortal> list=DPRInterface.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void Applicant_GettingOneListing_Test() {
		ApplicantPortal checkOne=DPRInterface.findByApplicantFormId(applicantFormId);
		ApplicantPortalBean UD=new ApplicantPortalBean();
		UD.setCharacteristics1(checkOne.getCharacteristics1());
		assertEquals(characteristic1, UD.getCharacteristics1());
	}
	
	@Test
	@Order(4)
	public void Applicant_UpdateApplication_Test() {
		ApplicantPortal checkOne=DPRInterface.findByApplicantFormId(applicantFormId);
		ApplicantPortalBean UD=new ApplicantPortalBean();
		BeanUtils.copyProperties(checkOne, UD);
		UD.setCharacteristics1("abcabcabca");
		checkOne.setCharacteristics1(UD.getCharacteristics1());
		DPRInterface.save(checkOne);
		assertNotEquals(characteristic1, DPRInterface.findByApplicantFormId(applicantFormId).getCharacteristics1());
	}
	
	@Test
	@Order(5)
	public void Applicant_DeleteApplication_Test() {
		DPRInterface.deleteById(applicantFormId);
		assertThat(DPRInterface.existsById(applicantFormId)).isFalse();
	}
}
