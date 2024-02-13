package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.SystemOwnerMovieInterface;

//test case 16 allows us to test whether the movie "Eiffel" has the right price at system owner movie interface.
public class TestCase16 {
	
	SystemOwnerMovieInterface test = new SystemOwnerMovieInterface();
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
		String name = "Eiffel";
		for(int i = 0; i<test.getMovieNames().size(); i++) {
			
			if(test.getMovieNames().get(i).getText().equals(name)) {
		    index = i;
		  break;
			}

		}
		String price = test.getMoviePrices().get(index).getText();
		assertEquals("$5", price);
		    
		    
	}
	
	@Test
	public void negativeTest() {
		int index = 0;
		String name = "Eiffel";
		for(int i = 0; i<test.getMovieNames().size(); i++) {
			
			if(test.getMoviePrices().get(i).getText().equals(name)) {
		    index = i;
		  break;
			}

		}
		String price = test.getMoviePrices().get(index).getText();
		assertNotEquals("$10", price);
		    
		    
	}

}
