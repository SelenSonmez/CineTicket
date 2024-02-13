package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.SystemOwnerMovieInterface;

//test case 25 allows us to test whether the movie "Eiffel" is in the right movie theater at system owner movie interface.
public class TestCase25 {
	
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
		 int theaterNo = Integer.parseInt(test.getMovieTheaterNo().get(index).getText());
		assertEquals(2, theaterNo);
		    
	}
	
	@Test
	public void negativeTest() {
		int index = 0;
		String name = "Eiffel";
		for(int i = 0; i<test.getMovieNames().size(); i++) {
			
			if(test.getMovieNames().get(i).getText().equals(name)) {
		    index = i;
		  break;
			}

		}
		 int theaterNo = Integer.parseInt(test.getMovieTheaterNo().get(index).getText());
		assertNotEquals(3, theaterNo);
		    
	}

}
