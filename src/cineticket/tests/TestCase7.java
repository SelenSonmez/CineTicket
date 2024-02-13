package cineticket.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.AddMovieInterface;
import cineticket.DatabaseConnection;
import cineticket.DeleteMovieInterface;
import cineticket.Movies;

public class TestCase7 {
	AddMovieInterface addMovie = new AddMovieInterface();
	DeleteMovieInterface deleteMovie = new DeleteMovieInterface();
	DatabaseConnection db = new DatabaseConnection();
	
	int size = 0;
	

	@Before
	//To check multiple times, the movie we wanted to delete is inserted before the deletion in case of
	//preventing errors or wrong results
	public void setUp() throws Exception {
		addMovie.insertMovies("movieName","movieTime" , 5, "moviePrice");
		for(int i = 0; i<5; i++) {
			System.out.println(db.getMovieNamesForCombobox()[i]);
		}
		
		size = db.getMovieNamesForCombobox().length;
		
	}

	@After
	public void tearDown() throws Exception {
		deleteMovie.deleteMovies(deleteMovie.getMovieNameToBeDeleted("movieName"));
		addMovie = null;
		deleteMovie = null;
	}

	//CHECKS IF A MOVIE THAT IS NOT PLACED IN THE DATABASE CAN BE DELETED OR NOT
	@Test
	public void positiveTest() {
	
		
		
		deleteMovie.deleteMovies(deleteMovie.getMovieNameToBeDeleted("movieName"));
		int deletedSize = db.getMovieNamesForCombobox().length;
		
		System.out.println(size);
		assertTrue(deletedSize != size);
		
	}
	
	@Test
	public void negativeTest() {
		
		deleteMovie.deleteMovies(deleteMovie.getMovieNameToBeDeleted("wrongMovieName"));
			
		int deletedSize = db.getMovieNamesForCombobox().length;
		
		assertFalse(deletedSize != size);
		
	}

}
