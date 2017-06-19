package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Obligation;
import ftn.project.eObrazovanje.service.ObligationService;

@Transactional
public class ObligationControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private ObligationService obligationService;
	
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
		List<Obligation> obligations = obligationService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", obligations);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, obligations.size());
	}
	
	@Test
	public void testFindMaxObligation()
	{
		Long id = Long.MAX_VALUE;
		Obligation obligation = obligationService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", obligation);
	}
	
	@Test
	public void testCreate()
	{
		Obligation obligation = new Obligation();
		obligation.setObligationType("obligatory");
		
		Obligation createdObligation = obligationService.save(obligation);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdObligation);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdObligation.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "obligatory", createdObligation.getObligationType());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Obligation obligation = obligationService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", obligation);
		
		obligationService.remove(testId);
		
		Obligation deletedObligation = obligationService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Obligation entitet nije obrisan", deletedObligation);
	}
}
