package cineticket.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.CinemaSeatsDesign;
import cineticket.SeatButton;
import cineticket.SeatStatus;


//test case 9 allows us to check if the proceed button at 
//movie theater scene does what is intended to do 
public class TestCase9 {
	
	CinemaSeatsDesign c = new CinemaSeatsDesign();
	SeatButton seatButton;
	int theaterID = 1;
    int seatNumber = 7;

	@Before
	public void setUp() throws Exception {
		for(Map.Entry <Integer,ArrayList<SeatButton>> m : c.getSeatsForEachMovieTheater().entrySet()) {
			if(m.getKey()==theaterID) {
				seatButton = m.getValue().get(seatNumber-1);
				m.getValue().get(seatNumber-1).setStatus(SeatStatus.EMPTY);
			}
		}
		}

		

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void positiveTest() {
		
		seatButton.doClick();
		
		assertEquals(SeatStatus.PROCESSING,seatButton.getStatus());
	}
	
	@Test
	public void negativeTest() {
		seatButton.doClick();
		assertNotEquals(SeatStatus.EMPTY, seatButton.getStatus());
	}

}
