package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.service.SubjectService;
import ftn.project.eObrazovanje.web.dto.SubjectDTO;
import ftn.project.eObrazovanje.web.dto.SubjectRoleDTO;

@RestController
@RequestMapping(value = "api/subjects")
public class SubjectController {

	@Autowired
	SubjectService subjectService;
	
	@Autowired
	ProfessorRoleService professorRoleService;
	
	@Autowired
	ObligationService obligationService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<SubjectDTO>> getSubjects() {
		List<Subject> subjects = subjectService.findAll();
		List<SubjectDTO> subjectsDTO = new ArrayList<SubjectDTO>();
		for (Subject subject : subjects) {
			subjectsDTO.add(new SubjectDTO(subject));
		}
		return new ResponseEntity<>(subjectsDTO, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Subject>> getSubjectsPage(Pageable page) {
		Page<Subject> subjects = subjectService.findAll(page);

		return new ResponseEntity<>(subjects, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new SubjectDTO(subject), HttpStatus.OK);
	}

	@RequestMapping(value="/add",method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<SubjectDTO> saveSubject(@RequestBody SubjectDTO subjectDTO) {
		Subject subject = new Subject();
		subject.setName(subjectDTO.getName());
		subject.setSemester(subjectDTO.getSemester());
		
		subject = subjectService.save(subject);
		return new ResponseEntity<>(new SubjectDTO(subject), HttpStatus.OK);
	}

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

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (SubjectRole pr : subject.getRoles()) {
			pr.setSubject(null);
			subjectRoleService.remove(pr.getId());
		}

		subjectService.remove(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public ResponseEntity<List<SubjectRoleDTO>> getRoles(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Set<SubjectRole> subjectRoles = subject.getRoles();
		List<SubjectRoleDTO> subjectRolesDTO = new ArrayList<SubjectRoleDTO>();
		for (SubjectRole subjectRole : subjectRoles) {
			subjectRolesDTO.add(new SubjectRoleDTO(subjectRole));
		}
		return new ResponseEntity<>(subjectRolesDTO, HttpStatus.OK);
	}
	
	/*@RequestMapping(value = "/{id}/students", method = RequestMethod.GET)
	public ResponseEntity<List<studentServiceDTO>> getRoles(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Set<SubjectRole> subjectRoles = subject.getRoles();
		List<SubjectRoleDTO> subjectRolesDTO = new ArrayList<SubjectRoleDTO>();
		for (SubjectRole subjectRole : subjectRoles) {
			subjectRolesDTO.add(new SubjectRoleDTO(subjectRole));
		}
		return new ResponseEntity<>(subjectRolesDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public ResponseEntity<List<SubjectRoleDTO>> getRoles(@PathVariable Long id) {
		Subject subject = subjectService.findOne(id);
		if (subject == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Set<SubjectRole> subjectRoles = subject.getRoles();
		List<SubjectRoleDTO> subjectRolesDTO = new ArrayList<SubjectRoleDTO>();
		for (SubjectRole subjectRole : subjectRoles) {
			subjectRolesDTO.add(new SubjectRoleDTO(subjectRole));
		}
		return new ResponseEntity<>(subjectRolesDTO, HttpStatus.OK);
	}*/
}

