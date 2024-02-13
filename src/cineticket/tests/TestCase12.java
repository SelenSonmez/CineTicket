package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.SystemOwnerReservationsInterface;

//Test case 12 allows us to check if the customers and the movie tickets that they booked seem to system owner
// reservation list are in correct order
public class TestCase12 {
	SystemOwnerReservationsInterface sori = new SystemOwnerReservationsInterface();
	String movieName;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void positiveTest() {
		//This customer booked a ticket for Morbius
		int index = 0;
		String email = "elcin@gmail.com";
		
		for(int i = 0; i<sori.getCustomerMails().size(); i++) {
			
			if(sori.getCustomerMails().get(i).getText().equals(email)) {
				index = i;
				break;
			}

		}
		movieName = sori.getMovieNames().get(index).getText();
		assertEquals("Morbius",movieName);
		
	}
	
	@Test
	public void negativeTest() {
		//This customer booked a ticket for Dune
		int index = 0;
		String email = "siimgeaydogan@gmail.com";
		
		for(int i = 0; i<sori.getCustomerMails().size(); i++) {
			
			if(sori.getCustomerMails().get(i).getText().equals(email)) {
				index = i;
				break;
			}

		}
		movieName = sori.getMovieNames().get(index).getText();
		assertNotEquals("Morbius",movieName);
		
	}

}
