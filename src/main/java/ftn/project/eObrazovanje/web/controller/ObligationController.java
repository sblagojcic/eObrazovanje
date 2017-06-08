package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Obligation;
import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.service.ObligationService;
import ftn.project.eObrazovanje.web.dto.ObligationDTO;

@RestController
@RequestMapping(value = "api/obligations")
public class ObligationController {
	@Autowired
	private ObligationService obligationService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ObligationDTO>> getAllStudents() {
		List<Obligation> obligations = obligationService.findAll();
		List<ObligationDTO> obligationsDTO = new ArrayList<ObligationDTO>();
		for (Obligation obligation : obligations) {
			obligationsDTO.add(new ObligationDTO(obligation));
		}
		return new ResponseEntity<>(obligationsDTO, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ObligationDTO> getObligation(@PathVariable Long id) {
		Obligation obligation = obligationService.findOne(id);
		if (obligation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ObligationDTO> saveobligation(@RequestBody ObligationDTO obligation1) {
		Obligation obligation = new Obligation();
		Subject subject= new Subject(obligation1.getSubject().getId(), obligation1.getSubject().getName(), obligation1.getSubject().getSemester(),null, null,null);
		obligation.setDateOfObligation(obligation1.getDateOfObligation());
		obligation.setObligationType(obligation1.getObligationType());
		obligation.setPoints(obligation1.getPoints());
		obligation.setSubject(subject);
		obligation = obligationService.save(obligation);

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.CREATED);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ObligationDTO> updateobligation(@RequestBody ObligationDTO obligation1) {

		Obligation obligation = obligationService.findOne(obligation1.getId());
		if (obligation == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Subject subject= new Subject(obligation1.getSubject().getId(), obligation1.getSubject().getName(), obligation1.getSubject().getSemester(),null, null,null);
		obligation.setDateOfObligation(obligation1.getDateOfObligation());
		obligation.setObligationType(obligation1.getObligationType());
		obligation.setPoints(obligation1.getPoints());
		obligation.setSubject(subject);
		obligation = obligationService.save(obligation);

		return new ResponseEntity<>(new ObligationDTO(obligation), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN')")
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
	
	
	// dodati  metodu getFor(id)
}