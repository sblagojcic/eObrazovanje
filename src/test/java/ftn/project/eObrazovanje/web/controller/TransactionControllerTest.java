package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.Transaction;
import ftn.project.eObrazovanje.service.TransactionService;

@Transactional
public class TransactionControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private TransactionService transactionService;
	
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
		List<Transaction> transactions = transactionService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", transactions);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, transactions.size());
	}
	
	@Test
	public void testFindMaxTransaction()
	{
		Long id = Long.MAX_VALUE;
		Transaction transaction = transactionService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", transaction);
	}
	
	@Test
	public void testCreate()
	{
		Transaction transaction = new Transaction();
		transaction.setBankAccount("Raiffeisen");
		
		Transaction createdTransaction = transactionService.save(transaction);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdTransaction);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdTransaction.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Raiffeisen", createdTransaction.getBankAccount());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		Transaction transaction = transactionService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", transaction);
		
		transactionService.remove(testId);
		
		Transaction deletedTransaction = transactionService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani Transaction entitet nije obrisan", deletedTransaction);
	}
}
