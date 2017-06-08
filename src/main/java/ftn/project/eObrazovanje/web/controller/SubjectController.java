package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.service.SubjectService;
import ftn.project.eObrazovanje.web.dto.SubjectDTO;

@RestController
@RequestMapping(value = "api/subjects")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<SubjectDTO>> getSubjects() {
		List<Subject> subjects = subjectService.findAll();
		List<SubjectDTO> subjectsDTO = new ArrayList<SubjectDTO>();
		for (Subject subject : subjects) {
			subjectsDTO.add(new SubjectDTO(subject));
		}
		return new ResponseEntity<>(subjectsDTO, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Subject>> getSubjectsPage(
			@RequestParam(value = "pageNumber", required = false) int pageNumber, Pageable pageable) {
		if (pageNumber < 0) {
			pageNumber = 0;
		}
		PageRequest page = null;
		try {
			page = new PageRequest(pageNumber,20);
		} catch (Exception e) {
			page = (PageRequest) pageable;
		}
		Page<Subject> subjects = subjectService.findAll(page);

		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new SubjectDTO(subject), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());
		subject.setSemester(subjectDTO.getSemester());

		subject = subjectService.save(subject);
		return new ResponseEntity<>(new SubjectDTO(subject), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) {
		Subject subject = subjectService.findOne(subjectDTO.getId());
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		subject.setName(subjectDTO.getName());
		subject.setSemester(subjectDTO.getSemester());

		subject = subjectService.save(subject);
		return new ResponseEntity<>(new SubjectDTO(subject), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		subjectService.remove(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

}
