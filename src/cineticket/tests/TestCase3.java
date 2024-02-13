package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.MoviesInterface;
//test case 3 allows us to test whether the movies are in the right time
public class TestCase3 {
	
	MoviesInterface test = new MoviesInterface();
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
		
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
		 String movieTime = test.getMovieTimes().get(index).getText();
		
		assertEquals("11.00", movieTime);
		    
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
		String movieTime = test.getMovieTimes().get(index).getText();
		assertNotEquals("12.00", movieTime);
		    
	}

}
