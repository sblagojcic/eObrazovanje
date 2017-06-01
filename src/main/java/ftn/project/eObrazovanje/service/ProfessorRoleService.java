package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.ProfessorRole;
import ftn.project.eObrazovanje.repository.ProfessorRoleRepository;

@Service
public class ProfessorRoleService {

	@Autowired
	ProfessorRoleRepository professorRoleRepository;
	
    public ProfessorRole findOne(Long id) {
		return professorRoleRepository.findOne(id);
	}

	public List<ProfessorRole> findAll() {
		return professorRoleRepository.findAll();
	}
	
	public Page<ProfessorRole> findAll(Pageable page) {
		return professorRoleRepository.findAll(page);
	}

	public ProfessorRole save(ProfessorRole role) {
		return professorRoleRepository.save(role);
	}

	public void remove(Long id) {
		professorRoleRepository.delete(id);
	}

}


