package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Professor;
import ftn.project.eObrazovanje.repository.ProfessorRepository;


@Service
public class ProfessorService {

	@Autowired
	ProfessorRepository professorRepository;
	
    public Professor findOne(Long id) {
		return professorRepository.findOne(id);
	}

	public List<Professor> findAll() {
		return professorRepository.findAll();
	}
	
	public Page<Professor> findAll(Pageable page) {
		return professorRepository.findAll(page);
	}

	public Professor save(Professor Professor) {
		return professorRepository.save(Professor);
	}

	public void remove(Long id) {
		professorRepository.delete(id);
	}


	
	public Page<Professor> findFilteredProfessor(String name, String username, String lastname,  Pageable page) {
		return professorRepository.findAllByNameLikeOrUserNameLikeOrLastNameLike('%'+name+'%', '%'+username+'%', '%'+lastname+'%', page);
	}

}

