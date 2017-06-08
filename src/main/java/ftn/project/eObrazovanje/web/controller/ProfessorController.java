package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

import ftn.project.eObrazovanje.model.Professor;
import ftn.project.eObrazovanje.model.ProfessorRole;
import ftn.project.eObrazovanje.service.ProfessorRoleService;
import ftn.project.eObrazovanje.service.ProfessorService;
import ftn.project.eObrazovanje.web.dto.ProfessorDTO;
import ftn.project.eObrazovanje.web.dto.ProfessorRoleDTO;

@RestController
@RequestMapping(value = "api/professors")
public class ProfessorController {

	@Autowired
	ProfessorService professorService;

	@Autowired
	ProfessorRoleService professorRoleService;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ProfessorDTO>> getProfessors() {
		List<Professor> professors = professorService.findAll();
		List<ProfessorDTO> professorsDTO = new ArrayList<ProfessorDTO>();
		for (Professor professor : professors) {
			professorsDTO.add(new ProfessorDTO(professor));
		}
		return new ResponseEntity<>(professorsDTO, HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Professor>> getProfessorsPage(
			@RequestParam(value = "pageNumber", required = false) int pageNumber, Pageable pageable) {
		if (pageNumber < 0) {
			pageNumber = 0;
		}
		PageRequest page = null;
		try {
			page = new PageRequest(pageNumber, 20);
		} catch (Exception e) {
			page = (PageRequest) pageable;
		}
		Page<Professor> professors = professorService.findAll(page);

		return new ResponseEntity<>(professors, HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN','ROLE_STUDENT')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ProfessorDTO> getProfessor(@PathVariable Long id) {
		Professor professor = professorService.findOne(id);
		if (professor == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new ProfessorDTO(professor), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ProfessorDTO> saveProfessor(@RequestBody ProfessorDTO professorDTO) {
		Professor professor = new Professor();
		professor.setAddress(professorDTO.getAddress());
		professor.setRole("PROFESSOR");
		professor.setName(professorDTO.getName());
		professor.setLastName(professorDTO.getLastName());
		professor.setUserName(professorDTO.getUserName());
		professor.setPassword(professorDTO.getPassword());
		professor.setGender(professorDTO.getGender());
		professor.setDateOfBirth(professorDTO.getDateOfBirth());
		professor.setJMBG(professorDTO.getJMBG());
		professor.setTitle(professorDTO.getTitle());

		professor = professorService.save(professor);
		return new ResponseEntity<>(new ProfessorDTO(professor), HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_PROFESSOR')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ProfessorDTO> updateProfessor(@RequestBody ProfessorDTO professorDTO) {
		Professor professor = professorService.findOne(professorDTO.getId());
		if (professor == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		professor.setAddress(professorDTO.getAddress());
		professor.setName(professorDTO.getName());
		professor.setLastName(professorDTO.getLastName());
		professor.setUserName(professorDTO.getUserName());
		professor.setPassword(professorDTO.getPassword());
		professor.setGender(professorDTO.getGender());
		professor.setDateOfBirth(professorDTO.getDateOfBirth());
		professor.setJMBG(professorDTO.getJMBG());
		professor.setTitle(professorDTO.getTitle());

		professor = professorService.save(professor);
		return new ResponseEntity<>(new ProfessorDTO(professor), HttpStatus.OK);
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
		Professor professor = professorService.findOne(id);
		if (professor == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		for (ProfessorRole pr : professor.getRoles()) {
			pr.setProfessor(null);
			professorRoleService.remove(pr.getId());
		}

		professorService.remove(id);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PreAuthorize("hasAnyRole('ROLE_PROFESSOR','ROLE_ADMIN')")
	@RequestMapping(value = "/{id}/roles", method = RequestMethod.GET)
	public ResponseEntity<List<ProfessorRoleDTO>> getRoles(@PathVariable Long id) {
		Professor professor = professorService.findOne(id);
		if (professor == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		Set<ProfessorRole> professorRoles = professor.getRoles();
		List<ProfessorRoleDTO> professorRolesDTO = new ArrayList<ProfessorRoleDTO>();
		for (ProfessorRole professorRole : professorRoles) {
			professorRolesDTO.add(new ProfessorRoleDTO(professorRole));
		}
		return new ResponseEntity<>(professorRolesDTO, HttpStatus.OK);
	}
}
