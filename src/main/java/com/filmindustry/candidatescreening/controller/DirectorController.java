package com.filmindustry.candidatescreening.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filmindustry.candidatescreening.bean.DirectorPortalBean;
import com.filmindustry.candidatescreening.service.DirectorPortalServiceInterface;
@CrossOrigin("*")
@Validated
@RestController
@RequestMapping("/api/v1/directorportal/")
public class DirectorController {
	
	@Autowired
	private DirectorPortalServiceInterface DPSInterface;
	
	@PostMapping("addposting")
	public ResponseEntity<DirectorPortalBean> addPosting(@Valid @RequestBody DirectorPortalBean directorForm) {
		return new ResponseEntity<DirectorPortalBean>(DPSInterface.addPosting(directorForm),HttpStatus.CREATED);
	}
	
	@PostMapping("checkposting")
	public ResponseEntity<DirectorPortalBean> checkPosting(@RequestBody DirectorPortalBean directorForm) {
		return new ResponseEntity<DirectorPortalBean>(DPSInterface.checkPosting(directorForm.getFormId()),HttpStatus.OK);
	}
	
	@PostMapping("selectallposting")
	public ResponseEntity<List<DirectorPortalBean>> selectAllPosting(@RequestBody DirectorPortalBean directorForm) {
		return  new ResponseEntity<List<DirectorPortalBean>>(DPSInterface.selectAllPosting(directorForm.getUserRegisteredId()),HttpStatus.OK);
	}
	
	@PostMapping("applicantselectallposting")
	public ResponseEntity<List<DirectorPortalBean>> applicantselectallposting(@RequestBody DirectorPortalBean directorForm) {
		return  new ResponseEntity<List<DirectorPortalBean>>(DPSInterface.applicantselectallposting(),HttpStatus.OK);
	}
	
	@DeleteMapping("deleteposting")
	public ResponseEntity<DirectorPortalBean> deletePosting(@RequestBody DirectorPortalBean directorForm) {
		return new ResponseEntity<DirectorPortalBean>(DPSInterface.deletePosting(directorForm.getFormId()),HttpStatus.OK);
	}
	
	@PostMapping("updateposting")
	public ResponseEntity<DirectorPortalBean> updatePosting(@Valid @RequestBody DirectorPortalBean directorForm) {
		return new ResponseEntity<DirectorPortalBean>(DPSInterface.updatePosting(directorForm.getFormId(),directorForm),HttpStatus.OK);
	}
	
	@PostMapping("getnonappliedpostings")
	@Transactional(readOnly = true)
	public ResponseEntity<List<DirectorPortalBean>> getNonAppliedPostings(@RequestBody DirectorPortalBean directorForm) {
		return  new ResponseEntity<List<DirectorPortalBean>>(DPSInterface.NonAppliedPostings(directorForm.getUserRegisteredId()),HttpStatus.OK);
	}
	
	@PostMapping("getappliedpostings")
	@Transactional(readOnly = true)
	public ResponseEntity<List<DirectorPortalBean>> getAppliedPostings(@RequestBody DirectorPortalBean directorForm) {
		return  new ResponseEntity<List<DirectorPortalBean>>(DPSInterface.AppliedPostings(directorForm.getUserRegisteredId()),HttpStatus.OK);
	}
}
