package ftn.project.eObrazovanje.web.controller;

import java.util.ArrayList;
import java.util.List;

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

import ftn.project.eObrazovanje.model.Exam;
import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.service.ExamService;
import ftn.project.eObrazovanje.service.StudentService;
import ftn.project.eObrazovanje.web.dto.ExamDTO;

@RestController
@RequestMapping(value = "api/exams")
public class ExamController {

	@Autowired
	ExamService examService;

	@Autowired
	StudentService studentService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getExams() {
		List<Exam> exams = examService.findAll();
		List<ExamDTO> examsDTO = new ArrayList<ExamDTO>();
		for (Exam exam : exams) {
			examsDTO.add(new ExamDTO(exam));
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Exam>> getExamsPage(Pageable page) {
		Page<Exam> exams = examService.findAll(page);

		return new ResponseEntity<>(exams, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ExamDTO> saveExam(@RequestBody ExamDTO examDTO) {
		Exam exam = new Exam();
		exam.setPoints(examDTO.getPoints());
		exam.setPass(examDTO.isPass());
		Student student = studentService.findOne(examDTO.getStudentID());
		exam.setStudent(student);
		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ExamDTO> getExam(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteResponsePayment(@PathVariable Long id) {
		Exam exam = examService.findOne(id);
		if (exam == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else {
			examService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<ExamDTO> updateProfessor(@RequestBody ExamDTO examDTO) {
		Exam exam = examService.findOne(examDTO.getId());
		if (exam == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		exam.setPass(examDTO.isPass());
		exam.setPoints(examDTO.getPoints());
		Student student = studentService.findOne(examDTO.getStudentID());
		exam.setStudent(student);
		exam = examService.save(exam);
		return new ResponseEntity<>(new ExamDTO(exam), HttpStatus.OK);
	}

	@RequestMapping(value = "/getFor/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<ExamDTO>> getExamForUser(@PathVariable Long id) {
		List<Exam> exams = examService.findAll();
		List<ExamDTO> examsDTO = new ArrayList<ExamDTO>();
		for (Exam exam : exams) {
			if (exam.getStudent().getId().equals(id)) {
				examsDTO.add(new ExamDTO(exam));
			}
		}
		return new ResponseEntity<>(examsDTO, HttpStatus.OK);
	}
}
