package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.LoginInterface;

//test case 1 allows us to test whether the customer's login information
//like e-mail and password belongs to the correct customer. 
public class TestCase1 extends LoginInterface{
	LoginInterface test = new LoginInterface();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		test=null;
	}

	@Test
	public void positiveTest() {
		
		
		String email = "elcin@gmail.com";
		String password = "123";
		
		String customer = test.getCustomer(email, password).getName();
		assertEquals("elcin", customer);
		
	}
	
	@Test
	public void negativeTest() {
		
		LoginInterface test = new LoginInterface();
		String email = "elcin@gmail.com";
		String password = "123";
		
		String customer = test.getCustomer(email, password).getName();
		assertNotEquals("simge", customer);
	}


}
