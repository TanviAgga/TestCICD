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

import com.filmindustry.candidatescreening.bean.DirectorPortalBean;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.model.DirectorPortal;
import com.filmindustry.candidatescreening.model.UserDetails;
import com.filmindustry.candidatescreening.repository.DirectorPortalRepositoryInterface;

@TestMethodOrder(OrderAnnotation.class)
public class DirectorPortalTest extends CandidateScreeningApplicationTests{
	@Autowired
	private DirectorPortalRepositoryInterface DPRInterface;
	DirectorPortalBean DPB;
	static long formId;
	static String role;
	DirectorPortalTest()
	{
		DPB= new DirectorPortalBean(1, "ABC", "ABC Desc", "Active",
				"Comedy", "Actor", "Actor Desc", "Height in feet 5", "Weight in kg 50",
				"Gender male", "Nationality Indian", "Color dark", "Active",
				"", 0);
	}
	
	@Test
	@Order(1)
	public void Director_FormPosting_Test() {
		DirectorPortal DirectorPortalEntityTest= new DirectorPortal();
		BeanUtils.copyProperties(DPB, DirectorPortalEntityTest);
		DirectorPortal savedDataEntity=DPRInterface.save(DirectorPortalEntityTest);
		formId=(long) savedDataEntity.getFormId();
		role=savedDataEntity.getRole();
		DPB.setFormId(savedDataEntity.getFormId());
		assertNotNull(DPRInterface.findById(DPB.getFormId()));
	}
	
	@Test
	@Order(2)
	public void Director_GettingAllForms_Test() {
		List<DirectorPortal> list=DPRInterface.findAll();
		assertThat(list).size().isGreaterThan(0);
	}
	
	@Test
	@Order(3)
	public void Director_GettingOneForms_Test() {
		DirectorPortal checkOne=DPRInterface.findByFormId(formId);
		DirectorPortalBean UD=new DirectorPortalBean();
		UD.setRole(checkOne.getRole());
		assertEquals(role, UD.getRole());
	}
	
	@Test
	@Order(4)
	public void Director_UpdateForms_Test() {
		DirectorPortal checkOne=DPRInterface.findByFormId(formId);
		DirectorPortalBean UD=new DirectorPortalBean();
		BeanUtils.copyProperties(checkOne, UD);
		UD.setRole("abcabcabca");
		checkOne.setRole(UD.getRole());
		DPRInterface.save(checkOne);
		assertNotEquals(role, DPRInterface.findByFormId(formId).getRole());
	}
	
	@Test
	@Order(5)
	public void Director_DeleteForms_Test() {
		DPRInterface.deleteById(formId);
		assertThat(DPRInterface.existsById(formId)).isFalse();
	}
}
