package com.filmindustry.candidatescreening.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filmindustry.candidatescreening.bean.ApplicantPortalBean;
import com.filmindustry.candidatescreening.bean.DirectorPortalBean;
import com.filmindustry.candidatescreening.bean.UserDetailsBean;
import com.filmindustry.candidatescreening.model.ApplicantPortal;
import com.filmindustry.candidatescreening.model.DirectorPortal;
import com.filmindustry.candidatescreening.model.UserDetails;
import com.filmindustry.candidatescreening.repository.DirectorPortalRepositoryInterface;
import com.filmindustry.candidatescreening.service.DirectorPortalServiceInterface;

@Service
public class DirectorPortalService implements DirectorPortalServiceInterface{

	@Autowired
	private DirectorPortalRepositoryInterface DPRInterface;

	@Override
	public DirectorPortalBean addPosting(DirectorPortalBean directorForm) {
		DirectorPortalBean bean=null;
		DirectorPortal entity=null; 
		String patternChecker="";
		boolean match;
	    
		try
		{
	        entity=new DirectorPortal();
		    bean=new DirectorPortalBean();
			BeanUtils.copyProperties(directorForm, entity);
			DirectorPortal savedData=DPRInterface.save(entity);
			BeanUtils.copyProperties(savedData, bean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new DirectorPortalBean("Error at the server end");
		}
		return bean;
	}

	@Override
	public DirectorPortalBean checkPosting(long formId) {
		DirectorPortalBean bean=null;
		DirectorPortal entity=null; 	    
		try
		{
	        entity=new DirectorPortal();
		    bean=new DirectorPortalBean();
		    entity.setFormId(formId);
			DirectorPortal savedData=DPRInterface.findByFormId(entity.getFormId());
			BeanUtils.copyProperties(savedData, bean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new DirectorPortalBean("Error at the server end");
		}
		return bean;
	}

	@Override
	public DirectorPortalBean deletePosting(long formId) {
		DirectorPortalBean bean=null;
		DirectorPortal entity=null; 	    
		try
		{
	        entity=new DirectorPortal();
		    entity.setFormId(formId);
			DPRInterface.deleteById(entity.getFormId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new DirectorPortalBean("Error at the server end");
		}
		bean=new DirectorPortalBean(null,"Success");
		bean.setFormId(formId);
		return bean;
	}

	@Override
	public DirectorPortalBean updatePosting(long formId, DirectorPortalBean directorForm) {
		DirectorPortalBean bean=null;
		DirectorPortal entity=null; 
		String patternChecker="";
		boolean match;
	    
		try
		{
	        entity=new DirectorPortal();
		    bean=new DirectorPortalBean();
		    entity.setFormId(formId);
			DirectorPortal previousData=DPRInterface.findByFormId(entity.getFormId());
			previousData.setCharacteristics1(directorForm.getCharacteristics1());
			previousData.setCharacteristics2(directorForm.getCharacteristics2());
			previousData.setCharacteristics3(directorForm.getCharacteristics3());
			previousData.setCharacteristics4(directorForm.getCharacteristics4());
			previousData.setCharacteristics5(directorForm.getCharacteristics5());
			previousData.setMovieDesc(directorForm.getMovieDesc());
			previousData.setMovieGenre(directorForm.getMovieGenre());
			previousData.setMovieName(directorForm.getMovieName());
			previousData.setRole(directorForm.getRole());
			previousData.setRoleDescription(directorForm.getRoleDescription());
			DirectorPortal savedData=DPRInterface.save(previousData);
			BeanUtils.copyProperties(savedData, bean);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			bean=null;
			entity=null;
			return new DirectorPortalBean("Error at the server end");
		}
		return bean;
	}

	@Override
	public List<DirectorPortalBean> selectAllPosting(long userRegisteredId) {
			DirectorPortal entity = new DirectorPortal();
			entity.setUserRegisteredId(userRegisteredId);
			List<DirectorPortal> list=DPRInterface.findAllByUserRegisteredId(entity.getUserRegisteredId());
			List <DirectorPortalBean> finalList=new ArrayList<DirectorPortalBean>(list.size());
			for (DirectorPortal source: list ) {
				DirectorPortalBean target= new DirectorPortalBean();
		        BeanUtils.copyProperties(source , target);
		        finalList.add(target);
		     }
		
		return finalList;
	}

	@Override
	public List<DirectorPortalBean> applicantselectallposting() {
		DirectorPortal entity = new DirectorPortal();
		entity.setRoleStatus("Active");
		List<DirectorPortal> list=DPRInterface.findAllByroleStatus(entity.getRoleStatus());
		List <DirectorPortalBean> finalList=new ArrayList<DirectorPortalBean>(list.size());
		for (DirectorPortal source: list ) {
			DirectorPortalBean target= new DirectorPortalBean();
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		return finalList;
	}

	@Override
	public List<DirectorPortalBean> NonAppliedPostings(long userRegisteredId) {
		DirectorPortal entity = new DirectorPortal();
		entity.setUserRegisteredId(userRegisteredId);
		//List<DirectorPortal> list=DPRInterface.getNonAppliedPostings(entity.getUserRegisteredId());//CASTIT_DIRECTOR_ROLE_FORM
		//List<DirectorPortal> list1=new ArrayList<DirectorPortal>();
		List<DirectorPortal> list=DPRInterface.getNonAppliedPostings(entity.getUserRegisteredId());
		List <DirectorPortalBean> finalList=new ArrayList<DirectorPortalBean>(list.size());
		for (DirectorPortal source: list ) {
			DirectorPortalBean target= new DirectorPortalBean();
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		return finalList;
	}

	@Override
	public List<DirectorPortalBean> AppliedPostings(long userRegisteredId) {
		DirectorPortal entity = new DirectorPortal();
		entity.setUserRegisteredId(userRegisteredId);
		//List<DirectorPortal> list=DPRInterface.getNonAppliedPostings(entity.getUserRegisteredId());//CASTIT_DIRECTOR_ROLE_FORM
		//List<DirectorPortal> list1=new ArrayList<DirectorPortal>();
		List<DirectorPortal> list=DPRInterface.getAppliedPostings(entity.getUserRegisteredId());
		List <DirectorPortalBean> finalList=new ArrayList<DirectorPortalBean>(list.size());
		for (DirectorPortal source: list ) {
			DirectorPortalBean target= new DirectorPortalBean();
			ApplicantPortalBean b=new ApplicantPortalBean();
			List<DirectorPortal> apList=DPRInterface.getAppliAppliedPostings(userRegisteredId,source.getFormId());
			//int ap=DPRInterface.getAppliidAppliedPostings(userRegisteredId,source.getFormId());
			for (DirectorPortal source1: apList ) {
				b.setApplicantFormId(source1.getApplicantPortal().getApplicantFormId());
				source.setCharacteristics1(source1.getApplicantPortal().getCharacteristics1());
				source.setCharacteristics2(source1.getApplicantPortal().getCharacteristics2());
				source.setCharacteristics3(source1.getApplicantPortal().getCharacteristics3());
				source.setCharacteristics4(source1.getApplicantPortal().getCharacteristics4());
				source.setCharacteristics5(source1.getApplicantPortal().getCharacteristics5());
			}
			target.setApplicantPortalBean(b);
	        BeanUtils.copyProperties(source , target);
	        finalList.add(target);
	     }
		return finalList;
	}
}
