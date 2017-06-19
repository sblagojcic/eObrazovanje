package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Exam;
import ftn.project.eObrazovanje.service.ExamService;

public class ExamControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private ExamService examService;
	
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
		List<Exam> exams = examService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", exams);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, exams.size());
	}
	
	@Test
	public void testFindMaxExam()
	{
		Long id = Long.MAX_VALUE;
		Exam exam = examService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", exam);
	}
	
	@Test
	public void testCreate()
	{
		Exam exam = new Exam();
		exam.setPass(true);;
		
		Exam createdExam = examService.save(exam);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdExam);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdExam.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", true, createdExam.getPass());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Exam exam = examService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", exam);
		
		examService.remove(testId);
		
		Exam deletedExam = examService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Exam entitet nije obrisan", deletedExam);
	}
}
