package ftn.project.eObrazovanje.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
    StudentRepository studentRepository;
	
    public Student findOne(Long id) {
		return studentRepository.findOne(id);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	public Page<Student> findAll(Pageable page) {
		return studentRepository.findAll(page);
	}

	public Student save(Student Student) {
		return studentRepository.save(Student);
	}

	public void remove(Long id) {
		studentRepository.delete(id);
	}
}