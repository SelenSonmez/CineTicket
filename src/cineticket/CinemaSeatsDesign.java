package cineticket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolTip;
import javax.swing.UIManager;

import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class CinemaSeatsDesign {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */

	private static String movieNameToBook;
	private boolean isOwner;
	private int theaterNo;

	public static String getMovieNameToBook() {
		return movieNameToBook;
	}
	
	public static void setMovieNameToBook(String movieNameToBook) {
		CinemaSeatsDesign.movieNameToBook = movieNameToBook;
	}

	private static ArrayList<SeatButton> selectedSeats = new ArrayList<>();
	

	public static void setSelectedSeats(ArrayList<SeatButton> selectedSeats) {
		CinemaSeatsDesign.selectedSeats = selectedSeats;
	}

	private static HashMap <Integer, ArrayList<SeatButton>> seatsForEachMovieTheater = new HashMap<>();


	public static ArrayList<SeatButton> getSelectedSeats() {
		return selectedSeats;
	}


	public static HashMap<Integer, ArrayList<SeatButton>> getSeatsForEachMovieTheater() {
		return seatsForEachMovieTheater;
	}
	public static ArrayList<Integer> movieTheaterNo = new ArrayList<>();


	public boolean getIsOwner() {
		return isOwner;
	}


	public int getTheaterNo() {
		return theaterNo;
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CinemaSeatsDesign window = new CinemaSeatsDesign();
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
	public CinemaSeatsDesign() {
		this.isOwner = false;
		initialize();	
	}

	public CinemaSeatsDesign(boolean isOwner) {	
		this.isOwner = isOwner;
		initialize();		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		selectedSeats.removeAll(selectedSeats);
		frame = new JFrame();
		frame.setTitle("Seat Purchase");		//title of the frame
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setIconImage(icon);
		frame.getContentPane().setBackground(new Color(230, 230, 255));	
		frame.setBounds(100, 100, 450, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel ScreenLabel = new JLabel("-------------------------------Screen-------------------------------");
		ScreenLabel.setBounds(69, 286, 320, 14);
		frame.getContentPane().add(ScreenLabel);
		JButton btnProceed = new JButton("Proceed");
		btnProceed.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Proceeds to checkout menu when clicked. </p> </html>");
		btnProceed.setBackground(new Color(128, 128, 255));
		btnProceed.addActionListener(new ActionListener() {//after pressing the proceed button a pop up window appears if the user wants to confirm 
			public void actionPerformed(ActionEvent e) {//the seat selection and then the are directed to the payment page.

				int choice = JOptionPane.showConfirmDialog(frame, "Confirm seat selection?","Confirmation",0);
				if(choice==JOptionPane.YES_OPTION) {
					frame.dispose();
					PaymentInterface paymentInterface = new PaymentInterface(selectedSeats.size());
					paymentInterface.frame.setVisible(true);

				}				
			}
		});
		btnProceed.setBounds(165, 252, 97, 23);
		if(!isOwner) {//if the user is an owner they won't see the proceed button 
			frame.getContentPane().add(btnProceed);
		}
		
		//ComboBox for choosing which movie seats to display.
		JComboBox comboBox = new JComboBox(DatabaseConnection.getMovieNamesForCombobox());


		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					int index = comboBox.getSelectedIndex();
					movieNameToBook = comboBox.getSelectedItem().toString();
					
					for(Map.Entry <Integer,ArrayList<SeatButton>> m : seatsForEachMovieTheater.entrySet()) {
						//traverses map of seats and numbers to display seat design.
						//Whichever movie is chosen in the combo box, the hash map is traversed and 
						//visibility of the seats is arranged accordingly.
						if(m.getKey()!=(index+1)) {
							for(int i = 0; i<9; i++) {
								m.getValue().get(i).setVisible(false);
							}
						}
						else {
							for(int i = 0; i<9; i++) {
								m.getValue().get(i).setVisible(true);
							}
						}
					}			
				}
				else {
					
				}
			}
		});

		theaterNo = DatabaseConnection.getTheaterID(comboBox.getSelectedItem().toString());
		comboBox.setBounds(10, 11, 414, 22);
		frame.getContentPane().add(comboBox);
		JButton btnGoBack = new JButton("Go Back");
		
		btnGoBack.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnGoBack.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Go back to movie list menu. </p> </html>");
			}
		});
		
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				MoviesInterface mi = new MoviesInterface();
				mi.frame.setVisible(true);
			}
		});
		btnGoBack.setBounds(321, 261, 89, 23);
		frame.getContentPane().add(btnGoBack);
		//boolean list to save the seat taken status (true to taken false to empty).
		ArrayList<Boolean> list = DatabaseConnection.getSeats(theaterNo);

		for(int k=1; k<6; k++) {
			int count = 1;
			ArrayList<SeatButton> arrayList = new ArrayList<SeatButton>();
			for(int i = 0; i < 3; i++) {//for loops to create grid buttons
				for(int j = 0; j < 3; j++) {
					SeatButton button = new SeatButton(""+count);	
					//if(button.) {
						
					//}		
					arrayList.add(button);
					button.setBounds(j*130+55, i*45+39+i*27, 56, 45);	

					Color color = new Color(204, 204, 255);
					Color selectedColor = new Color(128, 128, 255);

					//When the interface of cinema seats displayed, the seats belongs to the first movie theater are shown.
					for(Map.Entry <Integer,ArrayList<SeatButton>> m : seatsForEachMovieTheater.entrySet()) {
						if(m.getKey()==1) {
							for(int a = 0; a<9; a++) {
								m.getValue().get(a).setVisible(true);
								
								
							}
						}
						else {
							button.setVisible(false);
						}
					}

					frame.getContentPane().add(button);

					if(isOwner) {
						button.setStatus(SeatStatus.EMPTY);
						count++;
						continue;
					}
					button.addActionListener(new ActionListener() {	//when pressed on seat buttons, they change their status as either 'Processing' or 'Empty.'
						@Override
						public void actionPerformed(ActionEvent e) {
							
							if(button.getStatus()== SeatStatus.PROCESSING) {
								button.setStatus(SeatStatus.EMPTY);
								button.setBackground(color);	//the seat buttons also change their color accordingly to pressing.
								selectedSeats.remove(button);
							}else if(button.getStatus() == SeatStatus.EMPTY) {					
								button.setBackground(selectedColor);
								button.setStatus(SeatStatus.PROCESSING);
								selectedSeats.add(button);
							}	

						}					
					});
					count++;
				}
			}
			seatsForEachMovieTheater.put(k, arrayList);
		}
		for(Map.Entry <Integer,ArrayList<SeatButton>> m : seatsForEachMovieTheater.entrySet()) {
			//Traverses the hash map and changes the seat status according to via getSeats method in DatabaseConnection. 
			//And disables the taken seats to prevent re-purchasing.
			for(int b = 0; b<9; b++) {

				if(DatabaseConnection.getSeats(m.getKey()).get(b)) {
					m.getValue().get(b).setStatus(SeatStatus.TAKEN);
					m.getValue().get(b).setEnabled(false);
				}
				else {
					m.getValue().get(b).setStatus(SeatStatus.EMPTY);
					m.getValue().get(b).setBackground(new Color(204, 204, 255));
				}
			}
			for(SeatButton a : m.getValue()) {
				if(a.isEnabled()) {
					a.addMouseMotionListener(new MouseMotionAdapter() {
						@Override
						public void mouseMoved(MouseEvent e) {
							a.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Go Back to movie list menu. </p> </html>");
						}
					});
				}	
			}
		}
		
	}
}







