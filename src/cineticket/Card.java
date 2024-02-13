package cineticket;

public class Card {
	private String cardNumber;
	private int expirationMonth;
	private int expirationYear;
	private int CVC;
	
	public Card(String cardNumber, int expirationMonth, int expirationYear, int CVC) {
		super();
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.CVC = CVC;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	public int getCVC() {
		return CVC;
	}
	public void setCVC(int cVC) {
		CVC = cVC;
	}
	

}
