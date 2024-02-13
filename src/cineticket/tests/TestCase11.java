package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.Customer;
import cineticket.LoginInterface;
import cineticket.RegisterDesign;

//Checking if a registered customer can login when the password was wrong
public class TestCase11 {
	RegisterDesign register = new RegisterDesign();
	LoginInterface login = new LoginInterface();
	boolean isPasswordTrue;
	String password;

	@Before
	public void setUp() throws Exception {
	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void positiveTest() {
		password = "123";
		//A customer has already registered with this email and the password was 123
		//Checking if he can log into the system
		if(login.getCustomer("uzunbayir.serhat@ieu.edu.tr",password)!=null) {
			isPasswordTrue = true;
		}
		else {
			isPasswordTrue = false;
		}
		assertEquals(true, isPasswordTrue);
		
	}
	
	@Test
	public void negativeTest() {
		password = "abc";
		//A customer has already registered with this email and the password was not abc
		//Checking if he can log into the system
		if(login.getCustomer("uzunbayir.serhat@ieu.edu.tr",password)!=null) {
			isPasswordTrue = true;
		}
		else {
			isPasswordTrue = false;
		}
		
		assertNotEquals(true,isPasswordTrue);
		
	}
	}


