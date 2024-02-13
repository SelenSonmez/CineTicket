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

public class SystemOwnerReservationsInterface {

	JFrame frame;
	private ArrayList<JLabel> customerMails = new ArrayList<>();
    private  ArrayList<JLabel> movieNames = new ArrayList<>();
    private ArrayList<JLabel> seatNumber = new ArrayList<>();
	
	public ArrayList<JLabel> getCustomerMails() {
		return customerMails;
	}

	public void setCustomerMails(ArrayList<JLabel> customerMails) {
		this.customerMails = customerMails;
	}

	public ArrayList<JLabel> getMovieNames() {
		return movieNames;
	}

	public void setMovieNames(ArrayList<JLabel> movieNames) {
		this.movieNames = movieNames;
	}

	

	public ArrayList<JLabel> getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(ArrayList<JLabel> seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SystemOwnerReservationsInterface window = new SystemOwnerReservationsInterface();
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
	public SystemOwnerReservationsInterface() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Customer Email");
		lblNewLabel_1.setBounds(36, 93, 101, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("Movie Name");
		lblNewLabel_3.setBounds(232, 93, 101, 13);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Amount of Tickets");
		lblNewLabel_4.setBounds(354, 93, 137, 13);
		panel.add(lblNewLabel_4);
		
		JLabel customerMailLabel1 = new JLabel("");
		customerMailLabel1.setBounds(40, 129, 182, 13);
		panel.add(customerMailLabel1);
		
		JLabel customerMailLabel = new JLabel("");
		customerMailLabel.setBounds(40, 165, 182, 13);
		panel.add(customerMailLabel);
		
		JLabel customerMailLabel3 = new JLabel("");
		customerMailLabel3.setBounds(40, 201, 182, 13);
		panel.add(customerMailLabel3);
		
		JLabel customerMailLabel4 = new JLabel("");
		customerMailLabel4.setBounds(40, 242, 182, 13);
		panel.add(customerMailLabel4);
		
		JLabel customerMailLabel5 = new JLabel("");
		customerMailLabel5.setBounds(40, 281, 182, 13);
		panel.add(customerMailLabel5);
		
		JLabel movieNameLabel1 = new JLabel("");
		movieNameLabel1.setBounds(242, 129, 67, 13);
		panel.add(movieNameLabel1);
		
		JLabel movieNameLabel2 = new JLabel("");
		movieNameLabel2.setBounds(242, 165, 67, 13);
		panel.add(movieNameLabel2);
		
		JLabel movieNameLabel3 = new JLabel("");
		movieNameLabel3.setBounds(242, 201, 67, 13);
		panel.add(movieNameLabel3);
		
		JLabel movieNameLabel4 = new JLabel("");
		movieNameLabel4.setBounds(242, 242, 67, 13);
		panel.add(movieNameLabel4);
		
		JLabel movieNameLabel5 = new JLabel("");
		movieNameLabel5.setBounds(242, 281, 67, 13);
		panel.add(movieNameLabel5);
		
		JLabel seatNumberLabel1 = new JLabel("");
		seatNumberLabel1.setBounds(386, 129, 45, 13);
		panel.add(seatNumberLabel1);
		
		JLabel seatNumberLabel2 = new JLabel("");
		seatNumberLabel2.setBounds(386, 165, 45, 13);
		panel.add(seatNumberLabel2);
		
		JLabel seatNumberLabel3 = new JLabel("");
		seatNumberLabel3.setBounds(386, 201, 45, 13);
		panel.add(seatNumberLabel3);
		
		JLabel seatNumberLabel4 = new JLabel("");
		seatNumberLabel4.setBounds(386, 242, 45, 13);
		panel.add(seatNumberLabel4);
		
		JLabel seatNumberLabel5 = new JLabel("");
		seatNumberLabel5.setBounds(386, 281, 45, 13);
		panel.add(seatNumberLabel5);
		
		JButton goBackButton = new JButton("Go Back");
		goBackButton.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                goBackButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to go back to home page </p> </html>");
            }
        });
		goBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
	            SystemOwnerInterface soi = new SystemOwnerInterface();
	            soi.frame.setVisible(true);
			}
		});
		goBackButton.setBounds(387, 334, 85, 21);
		panel.add(goBackButton);
		
		//adding customer email labels to customerMails array list
        customerMails.add(customerMailLabel1);
        customerMails.add(customerMailLabel);
        customerMails.add(customerMailLabel3);
        customerMails.add(customerMailLabel4);
        customerMails.add(customerMailLabel5);


        //adding movie name labels to movieNames array list
        movieNames.add(movieNameLabel1);
        movieNames.add(movieNameLabel2);
        movieNames.add(movieNameLabel3);
        movieNames.add(movieNameLabel4);
        movieNames.add(movieNameLabel5);

        //adding seat number labels to seatNumber array list

        seatNumber.add(seatNumberLabel1);
        seatNumber.add(seatNumberLabel2);
        seatNumber.add(seatNumberLabel3);
        seatNumber.add(seatNumberLabel4);
        seatNumber.add(seatNumberLabel5);
	
        
 
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement =con.createStatement();

            String query = "SELECT * FROM tickets LIMIT 0,5";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();


            int count = 0;
            while (resultSet.next()){

                // it gets the ticket reservations from the database and adds to the related reservation attribute
                //array lists to be shown later in the ticket reservation list

                customerMails.get(count).setText(resultSet.getString(1));
                movieNames.get(count).setText(resultSet.getString(2));
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
