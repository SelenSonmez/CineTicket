package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.MoviesInterface;

//test case 2 allows us to test whether the movies are in the right movie theater.
public class TestCase2 {
	
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
		String name = "Batman";
		for(int i = 0; i<test.getMovieNames().size(); i++) {
			
			if(test.getMovieNames().get(i).getText().equals(name)) {
		    index = i;
		  break;
			}

		}
		 int theaterNo = Integer.parseInt(test.getMovieTheaterNo().get(index).getText());
		assertEquals(1, theaterNo);
		    
	}
	
	@Test
	public void negativeTest() {
		int index = 0;
		String name = "Batman";
		for(int i = 0; i<test.getMovieNames().size(); i++) {
			
			if(test.getMovieNames().get(i).getText().equals(name)) {
		    index = i;
		  break;
			}

		}
		 int theaterNo = Integer.parseInt(test.getMovieTheaterNo().get(index).getText());
		assertNotEquals(2, theaterNo);
		    
	}

}
