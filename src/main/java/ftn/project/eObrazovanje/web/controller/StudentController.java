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

import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.web.dto.StudentDTO;

@RestController
@RequestMapping(value = "api/students")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> getAllStudents() {
		List<Student> students = studentService.findAll();
		List<StudentDTO> studentsDTO = new ArrayList<StudentDTO>();
		for (Student student : students) {
			studentsDTO.add(new StudentDTO(student));
		}
		return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Student>> getStudentsPage(
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
		Page<Student> students = studentService.findAll(page);

		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<StudentDTO> getStudent(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<StudentDTO> savestudent(@RequestBody StudentDTO student1) {
		Student student = new Student(student1.getGender(), student1.getDateOfBirth(), student1.getAddress(),
				student1.getJMBG(), student1.getPicturePath(), null, null, null, null);
		student.setName(student1.getName());
		student.setUserName(student1.getUserName());
		student.setLastName(student1.getLastName());
		student.setPassword(student1.getPassword());
		student = studentService.save(student);

		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<StudentDTO> updatestudent(@RequestBody StudentDTO student1) {
		// a student must exist
		Student student = studentService.findOne(student1.getId());
		if (student == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		student.setAddress(student1.getAddress());
		student.setDateOfBirth(student1.getDateOfBirth());
		student.setGender(student1.getGender());
		student.setJMBG(student1.getJMBG());
		student.setLastName(student1.getLastName());
		student.setName(student1.getName());
		student.setPassword(student1.getPassword());
		student.setPicturePath(student1.getPicturePath());
		student.setUserName(student1.getUserName());
		student.setRole(student1.getRole());
		student = studentService.save(student);

		return new ResponseEntity<>(new StudentDTO(student), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		Student student = studentService.findOne(id);
		if (student != null) {
			studentService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}