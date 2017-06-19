package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Professor;
import ftn.project.eObrazovanje.service.ProfessorService;

@Transactional
public class ProfessorControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private ProfessorService professorService;
	
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
		List<Professor> professors = professorService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", professors);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, professors.size());
	}
	
	@Test
	public void testFindMaxProfessor()
	{
		Long id = Long.MAX_VALUE;
		Professor professor = professorService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", professor);
	}
	
	@Test
	public void testCreate()
	{
		Professor professor = new Professor();
		professor.setName("Informatika");
		
		Professor createdProfessor = professorService.save(professor);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdProfessor);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdProfessor.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Informatika", createdProfessor.getName());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Professor professor = professorService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", professor);
		
		professorService.remove(testId);
		
		Professor deletedProfessor = professorService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Professor entitet nije obrisan", deletedProfessor);
	}
}
