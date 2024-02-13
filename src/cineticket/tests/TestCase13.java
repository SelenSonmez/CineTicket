package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.SystemOwnerReservationsInterface;

//Test case 13 allows us to check if the customers and the seat numbers that they booked seem to system
//owner reservation list are in correct order
public class TestCase13 {
	SystemOwnerReservationsInterface sori = new SystemOwnerReservationsInterface();
	int seatNumber;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void positiveTest() {
		//This customer booked a ticket for seat 3
		int index = 0;
		String email = "elcin@gmail.com";
		
		for(int i = 0; i<sori.getCustomerMails().size(); i++) {
			
			if(sori.getCustomerMails().get(i).getText().equals(email)) {
				index = i;
				break;
			}

		}
		seatNumber = Integer.parseInt(sori.getSeatNumber().get(index).getText());
		assertEquals(3,seatNumber);
		
	}
	
	@Test
	public void negativeTest() {
		//This customer booked a ticket for seat 5
		int index = 0;
		String email = "siimgeaydogan@gmail.com";
		
		for(int i = 0; i<sori.getCustomerMails().size(); i++) {
			
			if(sori.getCustomerMails().get(i).getText().equals(email)) {
				index = i;
				break;
			}

		}
		seatNumber = Integer.parseInt(sori.getSeatNumber().get(index).getText());
		assertNotEquals(3,seatNumber);
		
	}

}
