package cineticket;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JCheckBox;

public class CustomerReservationsInterface {

	JFrame frame;

	private  ArrayList<JCheckBox> movieNames = new ArrayList<>();
	private ArrayList<JLabel> seatNumber = new ArrayList<>();
	private ArrayList<String> customerMails = new ArrayList<>();
	public JButton cancelButton;



	public ArrayList<JCheckBox> getMovieNames() {
		return movieNames;
	}

	public void setMovieNames(ArrayList<JCheckBox> movieNames) {
		this.movieNames = movieNames;
	}

	public ArrayList<JLabel> getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(ArrayList<JLabel> seatNumber) {
		this.seatNumber = seatNumber;
	}

	public ArrayList<String> getCustomerMails() {
		return customerMails;
	}

	public void setCustomerMails(ArrayList<String> customerMails) {
		this.customerMails = customerMails;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerReservationsInterface window = new CustomerReservationsInterface();
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
	public CustomerReservationsInterface() {
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setIconImage(icon);
		frame.setBounds(100, 100, 556, 416);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 542, 379);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ticket Reservations");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 30, 522, 37);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_3 = new JLabel("Movie Name");
		lblNewLabel_3.setBounds(77, 93, 101, 13);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Seat Numbers");
		lblNewLabel_4.setBounds(320, 93, 137, 13);
		panel.add(lblNewLabel_4);

		JLabel seatNumberLabel1 = new JLabel("");
		seatNumberLabel1.setBounds(352, 129, 45, 13);
		panel.add(seatNumberLabel1);

		JLabel seatNumberLabel2 = new JLabel("");
		seatNumberLabel2.setBounds(352, 165, 45, 13);
		panel.add(seatNumberLabel2);

		JLabel seatNumberLabel3 = new JLabel("");
		seatNumberLabel3.setBounds(352, 201, 45, 13);
		panel.add(seatNumberLabel3);

		JLabel seatNumberLabel4 = new JLabel("");
		seatNumberLabel4.setBounds(352, 242, 45, 13);
		panel.add(seatNumberLabel4);

		JLabel seatNumberLabel5 = new JLabel("");
		seatNumberLabel5.setBounds(352, 281, 45, 13);
		panel.add(seatNumberLabel5);

		JButton goBackButton = new JButton("Go Back");
		goBackButton.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				goBackButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Go back to Menu . </p> </html>");
			}
		});
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CustomerInterface ci = new CustomerInterface();
				ci.frame.setVisible(true);
			}
		});
		goBackButton.setBounds(387, 334, 85, 21);
		panel.add(goBackButton);

		//adding ticket amount labels to ticketAmount array list


		JCheckBox movieNameLabel1 = new JCheckBox("");
		movieNameLabel1.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				movieNameLabel1.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Select the movie to cancel the ticket. </p> </html>");
			}
		});
		movieNameLabel1.setBounds(56, 129, 93, 21);
		panel.add(movieNameLabel1);
		
		JCheckBox movieNameLabel2 = new JCheckBox("");
		movieNameLabel2.setBounds(56, 165, 93, 21);
		movieNameLabel2.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				movieNameLabel2.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Select the movie to cancel the ticket. </p> </html>");
			}
		});
		panel.add(movieNameLabel2);

		JCheckBox movieNameLabel3 = new JCheckBox("");
		movieNameLabel3.setBounds(56, 201, 93, 21);
		movieNameLabel3.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				movieNameLabel3.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Select the movie to cancel the ticket. </p> </html>");
			}
		});
		panel.add(movieNameLabel3);

		JCheckBox movieNameLabel4 = new JCheckBox("");
		movieNameLabel4.setBounds(56, 242, 93, 21);
		movieNameLabel4.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				movieNameLabel4.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Select the movie to cancel the ticket. </p> </html>");
			}
		});
		panel.add(movieNameLabel4);

		JCheckBox movieNameLabel5 = new JCheckBox("");
		movieNameLabel5.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				movieNameLabel5.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Select the movie to cancel the ticket. </p> </html>");
			}
		});
		movieNameLabel5.setBounds(56, 281, 93, 21);
		panel.add(movieNameLabel5);

		movieNames.add(movieNameLabel1);
		movieNames.add(movieNameLabel2);
		movieNames.add(movieNameLabel3);
		movieNames.add(movieNameLabel4);
		movieNames.add(movieNameLabel5);

		for(JCheckBox c : movieNames) {
			c.setVisible(false);
		}
		//adding seat number labels to seatNumber array list

		seatNumber.add(seatNumberLabel1);
		seatNumber.add(seatNumberLabel2);
		seatNumber.add(seatNumberLabel3);
		seatNumber.add(seatNumberLabel4);
		seatNumber.add(seatNumberLabel5);

		cancelButton = new JButton("Cancel");
		cancelButton.addMouseMotionListener(new MouseMotionAdapter() {		
			public void mouseMoved(MouseEvent e) {
				cancelButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Cancel the ticket cancellation . </p> </html>");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(frame, "Are you sure you want to cancel your reservation?","Confirmation",0);
				if(choice==JOptionPane.YES_OPTION) {
					for(JCheckBox c : movieNames) {
						if(c.isSelected()) {

							DatabaseConnection.cancelTicket(LoginInterface.getCustomer().getEmail(),
									c.getText(),
									Integer.parseInt(seatNumber.get(movieNames.indexOf(c)).getText()));

						}
					}
					frame.dispose();				
					CustomerReservationsInterface cri = new CustomerReservationsInterface();
					cri.frame.setVisible(true);
				}
			}
		});
		cancelButton.setBounds(292, 334, 85, 21);
		panel.add(cancelButton);



		try {
			Connection con = DriverManager.getConnection(
					"jdbc:sqlite:movie.sqlite");
			Statement statement =con.createStatement();

			String query = "SELECT * FROM tickets WHERE customerEmail = ?";

			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, LoginInterface.getCustomer().getEmail());

			ResultSet resultSet = preparedStatement.executeQuery();


			int count = 0;
			while (resultSet.next()){

				// it gets the ticket reservations from the database and adds to the related reservation attribute
				//array lists to be shown later in the ticket reservation list

				customerMails.add(resultSet.getString(1));
				movieNames.get(count).setText(resultSet.getString(2));
				movieNames.get(count).setVisible(true);
				seatNumber.get(count).setText(resultSet.getString(3));

				count++;

			}
			statement.close();
			con.close();
		}
		catch (Exception e) {

			//When the system couldn't get the ticket reservation information from the database, an error message will be displayed
			//on the screen.

			JOptionPane.showMessageDialog(frame,"The system could not" +
					" fetch the tickets at the moment. Please try again later.","Try Again",
					JOptionPane.ERROR_MESSAGE);
		}


	}
}
