package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.repository.SubjectRepository;

@Service
public class SubjectService {

	@Autowired
	SubjectRepository subjectRepository;
	
    public Subject findOne(Long id) {
		return subjectRepository.findOne(id);
	}

	public List<Subject> findAll() {
		return subjectRepository.findAll();
	}
	
	public Page<Subject> findAll(Pageable page) {
		return subjectRepository.findAll(page);
	}

	public Subject save(Subject subject) {
		return subjectRepository.save(subject);
	}

	public void remove(Long id) {
		subjectRepository.delete(id);
	}

	public Page<Subject> findAllByNameLike(String filter1, Pageable page) {
		
		return subjectRepository.findAllByNameLike('%'+filter1+'%',page);
	}

}

