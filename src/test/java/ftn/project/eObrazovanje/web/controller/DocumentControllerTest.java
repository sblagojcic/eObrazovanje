package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Document;
import ftn.project.eObrazovanje.service.DocumentService;

@Transactional
public class DocumentControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private DocumentService documentService;
	
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
		List<Document> documents = documentService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", documents);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, documents.size());
	}
	
	@Test
	public void testFindMaxDocument()
	{
		Long id = Long.MAX_VALUE;
		Document document = documentService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", document);
	}
	
	@Test
	public void testCreate()
	{
		Document document = new Document();
		document.setName("Informatika");
		
		Document createdDocument = documentService.save(document);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdDocument);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdDocument.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Informatika", createdDocument.getName());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Document document = documentService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", document);
		
		documentService.remove(testId);
		
		Document deletedDocument = documentService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Document entitet nije obrisan", deletedDocument);
	}
}
