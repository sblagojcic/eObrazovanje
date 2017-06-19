package ftn.project.eObrazovanje.web.controller;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ftn.project.eObrazovanje.EObrazovanjeApplicationTests;
import ftn.project.eObrazovanje.model.User;
import ftn.project.eObrazovanje.service.UserService;

@Transactional
public class UserControllerTest extends EObrazovanjeApplicationTests {
	@Autowired
	private UserService userService;
	
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
		List<User> users = userService.findAll();
		Assert.assertNotNull("JUnit Test neuspesan! Lista predmeta prazna", users);
		//Assert.assertEquals("JUnit Test neuspesan! Ocekivana dva clana liste", 2, users.size());
	}
	
	@Test
	public void testFindMaxUser()
	{
		Long id = Long.MAX_VALUE;
		User user = userService.findOne(id);
		
		Assert.assertNull("JUnit Test neuspesan! Lista je popunjena", user);
	}
	
	@Test
	public void testCreate()
	{
		User user = new User();
		user.setName("Informatika");
		
		User createdUser = userService.save(user);
		
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdUser);
		Assert.assertNotNull("JUnit Test neuspesan! Predmet nije kreiran", createdUser.getId());
		Assert.assertEquals("JUnit Test neuspesan! Predmet nije kreiran", "Informatika", createdUser.getName());
		
	}
	
	@Test
	public void testDelete()
	{
		Long testId = new Long(1);
		
		User user = userService.findOne(testId);
		
		Assert.assertNotNull("JUnit test neuspesan! Ocekivano !null svojstvo u testDelete metodi", user);
		
		userService.remove(testId);
		
		User deletedUser = userService.findOne(testId);
		
		Assert.assertNull("JUnit test neuspesan! Ocekivani User entitet nije obrisan", deletedUser);
	}
}
