package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.MoviesInterface;

//test case 27 allows us to test the name of the movie that shown in right movie theater 2
//according to the customer movie interface.
public class TestCase27 {
	
	MoviesInterface test = new MoviesInterface();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		test = null;
	}

	@Test
	public void positiveTest() {
		int index = 0;
		int theaterID = 2;
		for(int i = 0; i<test.getMovieTheaterNo().size(); i++) {
			
			if(Integer.parseInt(test.getMovieTheaterNo().get(i).getText()) == theaterID) {
		    index = i;
		  break;
			}

		}
		 String movieName = test.getMovieNames().get(index).getText();
		assertEquals("Eiffel", movieName);
		    
	}
	
	@Test
	public void negativeTest() {
		int index = 0;
		int theaterID = 2;
		for(int i = 0; i<test.getMovieTheaterNo().size(); i++) {
			
			if(Integer.parseInt(test.getMovieTheaterNo().get(i).getText()) == theaterID) {
		    index = i;
		  break;
			}

		}
		 String movieName = test.getMovieNames().get(index).getText();
		assertNotEquals("Dune", movieName);
		    
	}

}
