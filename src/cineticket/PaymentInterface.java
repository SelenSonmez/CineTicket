package cineticket;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JToggleButton;

public class PaymentInterface {

	public JFrame frame;
	private JTextField cardNoField;
	private JTextField monthField;
	private JTextField cvcNoField;
	private JTextField yearField;
	private JLabel lblPrice;
	private static JButton goBackButton;
	private int selectedSeatSize;
	private int price = 5;

	public static JButton getGoBackButton() {
		return goBackButton;
	}

	public JLabel getLblPrice() {
		return lblPrice;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInterface window = new PaymentInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaymentInterface() {
		initialize();
		this.selectedSeatSize = 0;
	}

	public PaymentInterface(int selectedSeatSize) {
		this.selectedSeatSize = selectedSeatSize;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setTitle("Payment");
		frame.setIconImage(icon);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		lblPrice = new JLabel("New label");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(10, 11, 288, 14);
		lblPrice.setText("The price you need to pay is " + (price * selectedSeatSize) + "$");
		frame.getContentPane().add(lblPrice);

		//Buttons and Labels of the class.
		JButton confirmPaymentButton = new JButton("Confirm Payment");
		confirmPaymentButton.addMouseMotionListener(new MouseMotionAdapter() {
		      
            public void mouseMoved(MouseEvent e) {
            	confirmPaymentButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to complete payment process </p> </html>");
            }
        });
		confirmPaymentButton.setBounds(145, 227, 136, 23);
		frame.getContentPane().add(confirmPaymentButton);


		goBackButton = new JButton("Go Back");
		goBackButton.addMouseMotionListener(new MouseMotionAdapter() {
		      
            public void mouseMoved(MouseEvent e) {
            	goBackButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to go back to seat selection </p> </html>");
            }
        });
		goBackButton.setBounds(309, 227, 115, 23);
		frame.getContentPane().add(goBackButton);

		LoginInterface.getCustomer().setCard(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()));
		cardNoField = new JTextField();		
		cardNoField.setBounds(10, 86, 288, 20);
		if(LoginInterface.getCustomer().getCard()!=null) {
		cardNoField.setText(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()).getCardNumber());
		}
		if(!(cardNoField.getText().equalsIgnoreCase("")))
			cardNoField.setEnabled(false);
		frame.getContentPane().add(cardNoField);
		cardNoField.setColumns(10);
		

		monthField = new JTextField();
		monthField.setBounds(10, 141, 70, 20);
		if(LoginInterface.getCustomer().getCard()!=null) {
		monthField.setText(Integer.toString(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()).getExpirationMonth()).toString());
		}
		if(!(monthField.getText().equalsIgnoreCase("")))
			monthField.setEnabled(false);
		frame.getContentPane().add(monthField);
		monthField.setColumns(10);

		cvcNoField = new JTextField();
		cvcNoField.setBounds(10, 196, 70, 20);
		if(LoginInterface.getCustomer().getCard()!=null ) {
		cvcNoField.setText(Integer.toString(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()).getCVC()).toString());
		}
		if(!(cvcNoField.getText().equalsIgnoreCase("")))
			cvcNoField.setEnabled(false);
		frame.getContentPane().add(cvcNoField);
		cvcNoField.setColumns(10);

		yearField = new JTextField();
		yearField.setColumns(10);
		yearField.setBounds(122, 141, 70, 20);
		if(LoginInterface.getCustomer().getCard()!=null) {
			
		yearField.setText(Integer.toString(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()).getExpirationYear()).toString());
		}
		if(!(yearField.getText().equalsIgnoreCase(""))) 
			yearField.setEnabled(false);
		frame.getContentPane().add(yearField);

		JLabel lblCardNo = new JLabel("Card Number:");
		lblCardNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCardNo.setBounds(10, 61, 84, 14);
		frame.getContentPane().add(lblCardNo);

		JLabel lblMonthAndYear = new JLabel("Month/Year:");
		lblMonthAndYear.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMonthAndYear.setBounds(10, 116, 84, 14);
		frame.getContentPane().add(lblMonthAndYear);

		JLabel lblCvcNo = new JLabel("CVC:");
		lblCvcNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCvcNo.setBounds(10, 171, 84, 14);
		frame.getContentPane().add(lblCvcNo);

		JLabel lblSlash = new JLabel("/");
		lblSlash.setHorizontalAlignment(SwingConstants.CENTER);
		lblSlash.setBounds(78, 144, 46, 14);
		frame.getContentPane().add(lblSlash);

		
		confirmPaymentButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {

					//Written to check if user provide only integers to the fields.
					//System.out.println(DatabaseConnection.getCardInfoOfCustomer(LoginInterface.getCustomer().getEmail()));
					
					
					String cardNumberText = cardNoField.getText();
					//long cardNumberText = Long.parseLong(cardNoField.getText());
					int monthText = Integer.parseInt(monthField.getText());
					int yearText = Integer.parseInt(yearField.getText());
					int cvcText = Integer.parseInt(cvcNoField.getText());

					int cardNumberDigit = cardNoField.getText().length();
					int monthDigit = monthField.getText().length();
					int yearDigit = yearField.getText().length();
					int cvcDigit = cvcNoField.getText().length();
					
					
					DatabaseConnection.insertCard(LoginInterface.getCustomer().getEmail(),cardNumberText, monthText, yearText, cvcText);
					Card card = new Card(cardNumberText,monthText, yearText, cvcText);
					LoginInterface.getCustomer().setCard(card);
					
					//condition to check whether use enter the desired digits of their card.
					if(cardNumberDigit != 16 || monthDigit != 2 || yearDigit != 2 || cvcDigit != 3) {
						throw new Exception();
					}
					JOptionPane.showMessageDialog(frame,"Payment completed successfully","Success",
							JOptionPane.INFORMATION_MESSAGE);
					
					//Arraylist to get the selected seats from the user's previous selection of seats..
					ArrayList<SeatButton> arr = CinemaSeatsDesign.getSelectedSeats();
					if(CinemaSeatsDesign.getMovieNameToBook()==null) {
						System.out.println("Grid");
						CinemaSeatsDesign.setMovieNameToBook("Batman");
					}
					for(int i = 0; i<arr.size();i++) {
						//writes to database the seats taken.
						DatabaseConnection.TakeSeat(DatabaseConnection.getTheaterID(CinemaSeatsDesign.getMovieNameToBook()),
								Integer.parseInt(arr.get(i).getText()));
						DatabaseConnection.ReserveTickets(LoginInterface.getCustomer().getEmail()
								,CinemaSeatsDesign.getMovieNameToBook(), Integer.parseInt(arr.get(i).getText()));
					}
					
					
					frame.dispose();
					//after successfully completing the payment, the user is directed to customer interface screen.
					CustomerInterface customerInterface = new CustomerInterface();
					customerInterface.frame.setVisible(true);

				}

				catch(Exception exception) {
					JOptionPane.showMessageDialog(frame, "Please provide valid inputs.","Error",JOptionPane.ERROR_MESSAGE);

					//Written to clear all input fields.
					cardNoField.setText("");
					monthField.setText("");
					yearField.setText("");
					cvcNoField.setText("");
				}
			}
		});

		//go back button to return back to cinemaSeatsDesign class
		goBackButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {				
				frame.dispose();
				CinemaSeatsDesign csd = new CinemaSeatsDesign();
				CinemaSeatsDesign.frame.setVisible(true);
			}
		});
	}
}

