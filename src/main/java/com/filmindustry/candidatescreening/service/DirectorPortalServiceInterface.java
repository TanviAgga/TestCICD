package com.filmindustry.candidatescreening.service;

import java.util.List;

import com.filmindustry.candidatescreening.bean.DirectorPortalBean;

public interface DirectorPortalServiceInterface {

	DirectorPortalBean addPosting(DirectorPortalBean directorForm);

	DirectorPortalBean checkPosting(long formId);

	DirectorPortalBean deletePosting(long formId);

	DirectorPortalBean updatePosting(long formId,DirectorPortalBean directorForm);

	List<DirectorPortalBean> selectAllPosting(long userRegisteredId);

	List<DirectorPortalBean> applicantselectallposting();

	List<DirectorPortalBean> NonAppliedPostings(long userRegisteredId);

	List<DirectorPortalBean> AppliedPostings(long userRegisteredId);

}
