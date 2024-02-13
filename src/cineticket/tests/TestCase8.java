package cineticket.tests;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import cineticket.Card;
import cineticket.Customer;
import cineticket.DatabaseConnection;
import cineticket.LoginInterface;
import cineticket.PaymentInterface;


//Test case 8 allows us to check if the card that belongs to customer and the card information of the customer that stored in the database is same

public class TestCase8 {
	
	LoginInterface login = new LoginInterface();
	private Card card;
	private Card card2;
	private Card card3;
	
	private Customer customer;

	@Before
	public void setUp() throws Exception {
		
		customer = login.getCustomer("selen@gmail.com","123");
		card2 = DatabaseConnection.getCardInfoOfCustomer("selen@gmail.com");
		customer.setCard(card2);
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void positiveTest() {
		
		card =customer.getCard();
		
		assertSame(card, card2);
		
		
	}
	@Test
	public void negativeTest() {
		
		card = login.getCustomer("selen@gmail.com","123").getCard();
		
		card3 = DatabaseConnection.getCardInfoOfCustomer("elcin@gmail.com");

		assertNotSame(card,card3);
	}

}
