package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Obligation;
import ftn.project.eObrazovanje.service.ObligationService;
import ftn.project.eObrazovanje.web.dto.ObligationDTO;

@RestController
@RequestMapping(value = "api/obligations")
public class ObligationController {
	@Autowired
	private ObligationService obligationService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ObligationDTO>> getAllObligations() {
		List<Obligation> obligations = obligationService.findAll();
		List<ObligationDTO> obligationsDTO = new ArrayList<ObligationDTO>();
		for (Obligation obligation : obligations) {
			obligationsDTO.add(new ObligationDTO(obligation));
		}
		return new ResponseEntity<>(obligationsDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Obligation>> getObligationsPage(
			@RequestParam(value = "pageNumber", required = false) int pageNumber, Pageable pageable) {
		if (pageNumber < 0) {
			pageNumber = 0;
		}
		PageRequest page = null;
		try {
			page = new PageRequest(pageNumber, 1);
		} catch (Exception e) {
			page = (PageRequest) pageable;
		}
		Page<Obligation> obligations = obligationService.findAll(page);

		return new ResponseEntity<>(obligations, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ObligationDTO> getObligation(@PathVariable Long id) {
		Obligation obligation = obligationService.findOne(id);
		if (obligation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ObligationDTO> saveobligation(@RequestBody ObligationDTO obligation1) {
		Obligation obligation = new Obligation();
		obligation.setDateOfObligation(obligation.getDateOfObligation());
		obligation.setExam(obligation1.getExam());
		obligation.setOblitagionType(obligation1.getObligationType());
		obligation.setPoints(obligation1.getMaxPoints());
		obligation.setSubject(obligation.getSubject());
		obligation = obligationService.save(obligation);

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ObligationDTO> updateobligation(@RequestBody ObligationDTO obligation1) {

		Obligation obligation = obligationService.findOne(obligation1.getId());
		if (obligation == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		obligation.setDateOfObligation(obligation.getDateOfObligation());
		obligation.setExam(obligation1.getExam());
		obligation.setOblitagionType(obligation1.getObligationType());
		obligation.setPoints(obligation1.getMaxPoints());
		obligation.setSubject(obligation.getSubject());
		obligation = obligationService.save(obligation);

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteObligation(@PathVariable Long id) {
		Obligation obligation = obligationService.findOne(id);
		if (obligation != null) {
			obligationService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}