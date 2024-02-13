package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.DatabaseConnection;


//Test case 30 allows us to check if a customer can cancel their tickets properly
public class TestCase30 {
	DatabaseConnection db = new DatabaseConnection();

	
	
	@Before
	public void setUp() throws Exception {
		//Customer cancels the ticket before the test
		DatabaseConnection.cancelTicket("selen@gmail.com","Eiffel", 1);
		
		
	}

	@After
	public void tearDown() throws Exception {
		//Customer books the same ticket after the test
		DatabaseConnection.TakeSeat(2, 1);
		DatabaseConnection.ReserveTickets("selen@gmail.com", "Eiffel", 1);
	}

	@Test
	public void positiveTest() {
		//Checking if customer could cancel his/her ticket
		assertEquals(false,DatabaseConnection.getSeats(2).get(0));
		
	}
	
	@Test
	public void negativeTest() {
		//Checking if customer could not cancel his/her ticket
		assertNotEquals(true,DatabaseConnection.getSeats(2).get(0));
		
	}

}
