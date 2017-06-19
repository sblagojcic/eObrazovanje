package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Subject;
import ftn.project.eObrazovanje.service.SubjectService;

@Transactional
public class SubjectControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private SubjectService subjectService;
	
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
		List<Subject> subjects = subjectService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", subjects);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, subjects.size());
	}
	
	@Test
	public void testFindMaxSubject()
	{
		Long id = Long.MAX_VALUE;
		Subject subject = subjectService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", subject);
	}
	
	@Test
	public void testCreate()
	{
		Subject subject = new Subject();
		subject.setName("Informatika");
		
		Subject createdSubject = subjectService.save(subject);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdSubject);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdSubject.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Informatika", createdSubject.getName());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Subject subject = subjectService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", subject);
		
		subjectService.remove(testId);
		
		Subject deletedSubject = subjectService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Subject entitet nije obrisan", deletedSubject);
	}
	
}
