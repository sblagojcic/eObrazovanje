package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Student;
import ftn.project.eObrazovanje.service.StudentService;

@Transactional
public class StudentControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private StudentService studentService;

	//private StudentController studentController;
	
	@Before
	public void setUp()
	{
		//pre testa
	}
	
	@After
	public void tearDown()
	{
		//ciscenje posle testa
	}
	
	@Test
	public void testFindAll()
	{
		List<Student> students = studentService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista studenata prazna", students);
		Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, students.size());
	}
	
	@Test
	public void testFindMaxStudent()
	{
		Long id = Long.MAX_VALUE;
		Student student = studentService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", student);
	}
	
	@Test
	public void testCreate()
	{
		Student student = new Student();
		student.setName("Informatika");
		
		Student createdStudent = studentService.save(student);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdStudent);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdStudent.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Informatika", createdStudent.getName());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Student student = studentService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", student);
		
		studentService.remove(testId);
		
		Student deletedStudent = studentService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Student entitet nije obrisan", deletedStudent);
	}
}
