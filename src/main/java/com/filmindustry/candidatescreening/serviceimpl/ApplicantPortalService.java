package com.filmindustry.candidatescreening.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.filmindustry.candidatescreening.functionclasses.Email;
import com.filmindustry.candidatescreening.functionclasses.FuzzyLogic;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filmindustry.candidatescreening.bean.ApplicantPortalBean;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.model.ApplicantPortal;
import com.filmindustry.candidatescreening.model.DirectorPortal;
import com.filmindustry.candidatescreening.repository.ApplicantPortalRepositoryInterface;
import com.filmindustry.candidatescreening.repository.DirectorPortalRepositoryInterface;
import com.filmindustry.candidatescreening.service.ApplicantPortalServiceInterface;

@Service
public class ApplicantPortalService implements ApplicantPortalServiceInterface{
	
	@Autowired
	private ApplicantPortalRepositoryInterface APRInterface;
	@Autowired
	private DirectorPortalRepositoryInterface DPRInterface;
	
	public ApplicantPortalBean applyPosting(ApplicantPortalBean applicantForm) {
		ApplicantPortalBean bean=null;
		ApplicantPortal entity=null;
		try
		{
	        entity=new ApplicantPortal();
		    bean=new ApplicantPortalBean();
			BeanUtils.copyProperties(applicantForm, entity);
			ApplicantPortal savedData=APRInterface.save(entity);
			BeanUtils.copyProperties(savedData, bean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
		}
		return bean;
	}
	@Override
	public ApplicantPortalBean deletePosting(long appformId) {
		// TODO Auto-generated method stub
		ApplicantPortalBean bean=null;
		ApplicantPortal entity=null; 	    
		try
		{
	        entity=new ApplicantPortal();
		    entity.setApplicantFormId(appformId);
		    APRInterface.deleteByCustomId(entity.getApplicantFormId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new ApplicantPortalBean("Error at the server end");
		}
		bean=new ApplicantPortalBean(null,"Success");
		bean.setApplicantFormId(appformId);
		return bean;
	}

	@Override
	public ApplicantPortalBean updatePosting(long applicantFormId, @Valid ApplicantPortalBean applicantForm) {
		// TODO Auto-generated method stub
		ApplicantPortalBean bean=null;
		ApplicantPortal entity=null;
	    
		try
		{
	        entity=new ApplicantPortal();
		    bean=new ApplicantPortalBean();
		    entity.setApplicantFormId(applicantFormId);
		    ApplicantPortal previousData=APRInterface.findByApplicantFormId(entity.getApplicantFormId());
			previousData.setCharacteristics1(applicantForm.getCharacteristics1());
			previousData.setCharacteristics2(applicantForm.getCharacteristics2());
			previousData.setCharacteristics3(applicantForm.getCharacteristics3());
			previousData.setCharacteristics4(applicantForm.getCharacteristics4());
			previousData.setCharacteristics5(applicantForm.getCharacteristics5());
			previousData.setFormId(applicantForm.getFormId());
			previousData.setUserRegisteredId(applicantForm.getUserRegisteredId());
			ApplicantPortal savedData=APRInterface.save(previousData);
			BeanUtils.copyProperties(savedData, bean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new ApplicantPortalBean("Error at the server end");
		}
		return bean;
	}
	@Override
	public List<ApplicantPortalBean> getApplicantListOnPostings(long formId) {
		ApplicantPortal entity = new ApplicantPortal();
		entity.setFormId(formId);
		List<ApplicantPortal> list=APRInterface.getApplicantListOnPostings(entity.getFormId());
		List <ApplicantPortalBean> finalList=new ArrayList<ApplicantPortalBean>(list.size());
		for (ApplicantPortal source: list ) {
			ApplicantPortalBean target= new ApplicantPortalBean();
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		return finalList;
	}
	
	@Override
	public List<ApplicantPortalBean> getFeasableCandidates(long formId,String percentage) {
		ApplicantPortal entity = new ApplicantPortal();
		entity.setFormId(formId);
		List<ApplicantPortal> list=APRInterface.getFeasableCandidates(entity.getFormId());

		List <ApplicantPortalBean> finalList=new ArrayList<ApplicantPortalBean>(list.size());
		
		DirectorPortal entityDirector = new DirectorPortal();
		FuzzyLogic fuzzy=new FuzzyLogic();
		entityDirector.setFormId(formId);
		DirectorPortal dpGet=DPRInterface.findByFormId(entityDirector.getFormId());
		String fixedFormCharac1=dpGet.getCharacteristics1().split(",")[1];
		String fixedFormCharac2=dpGet.getCharacteristics2().split(",")[1];
		String fixedFormCharac3=dpGet.getCharacteristics3().split(",")[1];
		String fixedFormCharac4=dpGet.getCharacteristics4().split(",")[1];
		String fixedFormCharac5=dpGet.getCharacteristics5().split(",")[1];
		
		for (ApplicantPortal source: list ) {
			ApplicantPortalBean target= new ApplicantPortalBean();
			String matchFormCharac1=source.getCharacteristics1().split(",")[1];
			String matchFormCharac2=source.getCharacteristics2().split(",")[1];
			String matchFormCharac3=source.getCharacteristics3().split(",")[1];
			String matchFormCharac4=source.getCharacteristics4().split(",")[1];
			String matchFormCharac5=source.getCharacteristics5().split(",")[1];
			double perValueMatchCharac1=fuzzy.findSimilarity(fixedFormCharac1, matchFormCharac1);
			double perValueMatchCharac2=fuzzy.findSimilarity(fixedFormCharac2, matchFormCharac2);
			double perValueMatchCharac3=fuzzy.findSimilarity(fixedFormCharac3, matchFormCharac3);
			double perValueMatchCharac4=fuzzy.findSimilarity(fixedFormCharac4, matchFormCharac4);
			double perValueMatchCharac5=fuzzy.findSimilarity(fixedFormCharac5, matchFormCharac5);
			double avgTotalPer=((perValueMatchCharac1+perValueMatchCharac2+perValueMatchCharac3+perValueMatchCharac4+perValueMatchCharac5)/5)*100;
			source.setPercentageMatch(Double.toString(avgTotalPer));
			APRInterface.updatePercentage(source.getApplicantFormId(), Double.toString(avgTotalPer));
			if (avgTotalPer<Double.parseDouble(percentage))
				continue;
			source.setShortlistingStatus("N");
	        BeanUtils.copyProperties(source , target);
	        target.setChecked(false);
	        finalList.add(target);
	     }
		
		return finalList;
	}
	@Override
	public List<ApplicantPortalBean> getrightswipe(long applicantFormId) {
		ApplicantPortal entity = new ApplicantPortal();
		List <ApplicantPortalBean> finalList = null;		
		try {
		entity.setApplicantFormId(applicantFormId);
		entity.setShortlistingStatus("Y");
		APRInterface.getrightswipe(entity.getApplicantFormId(),entity.getShortlistingStatus());
		List<ApplicantPortal> getData=APRInterface.getrightswipedCandidates(entity.getApplicantFormId());
		finalList=new ArrayList<ApplicantPortalBean>(getData.size());
		for (ApplicantPortal source: getData ) {
			ApplicantPortalBean target= new ApplicantPortalBean();
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return finalList;
	}
	@Override
	public ApplicantPortalBean getfinalSelection(List<ApplicantPortalBean> applicantForm,HttpServletRequest request) {
		List <ApplicantPortal> applicantPortalList=new ArrayList<ApplicantPortal>(applicantForm.size());
		List <ApplicantPortalBean> applicantPortalBeanList=new ArrayList<ApplicantPortalBean>(applicantForm.size());
		String rolesGivenTo="";
		long formId=0;
		try 
		{
			for (ApplicantPortalBean source: applicantForm ) {
				ApplicantPortal target= new ApplicantPortal();
				DirectorPortal dp=DPRInterface.findByFormId(source.getFormId());
				Email e=new Email();
				e.sendSelectedEmail(dp,source, request);
		        BeanUtils.copyProperties(source , target);
		        applicantPortalList.add(target);
		     }	
			for (ApplicantPortal source: applicantPortalList ) {
				ApplicantPortalBean target= new ApplicantPortalBean();
				APRInterface.getFinalSelection(source.getApplicantFormId(),source.getShortlistingStatus());
				rolesGivenTo=rolesGivenTo+","+source.getApplicantFormId();
				if (formId==0)
					formId=source.getFormId();
		        BeanUtils.copyProperties(source , target);
		        applicantPortalBeanList.add(target);
		     }
			
			APRInterface.closeRole(rolesGivenTo,formId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return new ApplicantPortalBean("Error at the server end");
		}
		
		return new ApplicantPortalBean(null,"Candidates Selected and Role Closed");
		
	}
	@Override
	public List<ApplicantPortalBean> getRightSwipedCandidates1(long FormId) {
		ApplicantPortal entity = new ApplicantPortal();
		List<ApplicantPortalBean> finalList = null;		
		try {
		List<ApplicantPortal> getData=APRInterface.getrightswipedCandidates1(FormId);
		finalList=new ArrayList<ApplicantPortalBean>(getData.size());
		for (ApplicantPortal source: getData ) {
			ApplicantPortalBean target= new ApplicantPortalBean();
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return finalList;
	}
}
