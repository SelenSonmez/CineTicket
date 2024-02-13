package cineticket.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.DatabaseConnection;


//test case 10 allows us to check if the taken seat seems as taken when a seat is taken
public class TestCase10 {
	
	DatabaseConnection db = new DatabaseConnection();
	int theaterID = 1;
    int seatNumber = 3;
  

	@Before
	//Seat 3 is taken before the test
	public void setUp() throws Exception {
		db.TakeSeat(theaterID,seatNumber);
	}

	@After
	//Seat 3 made available after the test
	public void tearDown() throws Exception {
		
		db.UpdateSeats(theaterID,seatNumber);
	}

	@Test
	//Checking if the seat 3 is taken
	public void positiveTest() {
		
			db.getSeats(theaterID).get(seatNumber);
			
			assertEquals(true, db.getSeats(theaterID).get(seatNumber-1) );
	}
	
	@Test
	//Checking if the seat 3 is not taken
	public void negativeTest() {
		
			db.getSeats(theaterID).get(seatNumber);
			
			assertNotEquals(false, db.getSeats(theaterID).get(seatNumber-1));
	}

}
