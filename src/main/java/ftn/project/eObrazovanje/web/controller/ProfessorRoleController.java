package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ftn.project.eObrazovanje.model.Professor;
import ftn.project.eObrazovanje.model.ProfessorRole;
import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.service.ProfessorRoleService;
import ftn.project.eObrazovanje.service.ProfessorService;
import ftn.project.eObrazovanje.web.dto.ProfessorRoleDTO;

@RestController
@RequestMapping(value = "api/professorRoles")
public class ProfessorRoleController {

    @Autowired
    ProfessorRoleService professorRoleService;

//    @Autowired
//    SubjectService subjectService;

    @Autowired
    ProfessorService professorService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProfessorRoleDTO>> getProfessorRoles() {
        List<ProfessorRole> professorRoles = professorRoleService.findAll();
        List<ProfessorRoleDTO> profesorRolesDTO = new ArrayList<ProfessorRoleDTO>();
        for (ProfessorRole professorRole : professorRoles) {
            profesorRolesDTO.add(new ProfessorRoleDTO(professorRole));
        }
        return new ResponseEntity<>(profesorRolesDTO, HttpStatus.OK);
    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/subjects/{id}")
//    public ResponseEntity<List<ProfessorRoleDTO>> getProfessorRolesForSubject(@PathVariable Integer id) {
//        Subject subject = subjectService.findOne(id);
//        List<ProfessorRole> professorRoles = professorRoleService.findBySubject(subject);
//        List<ProfessorRoleDTO> profesorRolesDTO = new ArrayList<ProfessorRoleDTO>();
//        for (ProfessorRole professorRole : professorRoles) {
//            profesorRolesDTO.add(new ProfessorRoleDTO(professorRole));
//        }
//        return new ResponseEntity<>(profesorRolesDTO, HttpStatus.OK);
//    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ProfessorRoleDTO> saveProfessorRole(@RequestBody ProfessorRoleDTO professorRoleDTO) {
        if (professorRoleDTO.getProfessorDTO() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (professorRoleDTO.getSubjectDTO() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

//        Subject subject = subjectService.findOne(professorRoleDTO.getSubjectDTO().getId());
//        if (subject == null)
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Professor professor = professorService.findOne(professorRoleDTO.getProfessorDTO().getId());
        if (professor == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        ProfessorRole professorRole = new ProfessorRole();
        professorRole.setProfessor(professor);
 //       professorRole.setSubject(subject);
        professorRole.setRole(professorRoleDTO.getRole());

        professorRole = professorRoleService.save(professorRole);
        return new ResponseEntity<>(new ProfessorRoleDTO(professorRole), HttpStatus.OK);


    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<ProfessorRoleDTO> updateProfessorRole(@RequestBody ProfessorRoleDTO professorRoleDTO) {
        ProfessorRole professorRole = professorRoleService.findOne(professorRoleDTO.getId());
        if (professorRole == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        professorRole.setRole(professorRoleDTO.getRole());
        professorRole = professorRoleService.save(professorRole);

        return new ResponseEntity<>(new ProfessorRoleDTO(professorRole), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteResponsePayment(@PathVariable Long id) {
        ProfessorRole professorRole = professorRoleService.findOne(id);
        if (professorRole == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
        	professorRoleService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);

        }

    }


}
