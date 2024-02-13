package cineticket.tests;

import static org.junit.Assert.*;

import java.util.Objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.AddMovieInterface;
import cineticket.DatabaseConnection;
import cineticket.DeleteMovieInterface;
import cineticket.Movies;

public class TestCase6 {
	Movies movie = null;
	Boolean isNull = false;
	
	@Before
	public void setUp() throws Exception {
		
		
	}

	@After
	public void tearDown() throws Exception {
	
		DatabaseConnection.deleteMovies(movie.getName());
	}

	//CHECKS IF AN ALREADY EXISTED MOVIE CAN BE INSERTED INTO DATABASE OR NOT
	@Test
	
	public void positiveTest() {
		
		movie = DatabaseConnection.insertMovies("name","time" , 5, "price");
		
		if(movie == null) {
			isNull = true;
		}
		assertEquals(false,isNull);
		
		
	}
	
	//CHECKS IF AN ALREADY EXISTED MOVIE CAN BE INSERTED INTO DATABASE OR NOT
	@Test
	public void negativeTest() {
		movie = DatabaseConnection.insertMovies("name","time" , 6, "price");
		
		if(movie == null) {
			isNull = true;
		}
		assertNotEquals(true,isNull);
		
	}

}
