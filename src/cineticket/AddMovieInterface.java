package cineticket;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class AddMovieInterface {

	JFrame frame;
	private JTextField movieNameField;
	private static JTextField movieTimeField;
	private static JTextField theaterNoField;
	private static JTextField priceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMovieInterface window = new AddMovieInterface();
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
	public AddMovieInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 528, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Movie Name");
		lblNewLabel.setBounds(57, 78, 106, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblMovieTime = new JLabel("Movie Time");
		lblMovieTime.setBounds(57, 128, 106, 27);
		frame.getContentPane().add(lblMovieTime);
		
		JLabel lblNewLabel_1_1 = new JLabel("Movie Theater");
		lblNewLabel_1_1.setBounds(57, 172, 106, 27);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Movie Price");
		lblNewLabel_1_1_1.setBounds(57, 217, 106, 27);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		movieNameField = new JTextField();
		movieNameField.setBounds(173, 86, 205, 19);
		frame.getContentPane().add(movieNameField);
		movieNameField.setColumns(10);
		
		movieTimeField = new JTextField();
		movieTimeField.setColumns(10);
		movieTimeField.setBounds(173, 136, 205, 19);
		frame.getContentPane().add(movieTimeField);
		
		theaterNoField = new JTextField();
		theaterNoField.setColumns(10);
		theaterNoField.setBounds(173, 180, 205, 19);
		frame.getContentPane().add(theaterNoField);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(173, 225, 205, 19);
		frame.getContentPane().add(priceField);
		
		//This button calls the addMovie() function and displays an message that says movie is successfully
		//added
		JButton addButton = new JButton("Add ");
		addButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				addButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Click to add movie to movie list. </p> </html>");
			}
		});
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMovie();
				JOptionPane.showMessageDialog(frame,"Movie added! ","Success",JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				SystemOwnerMovieInterface somi = new SystemOwnerMovieInterface();
        		somi.frame.setVisible(true);
			}
		});
		addButton.setBounds(145, 276, 85, 21);
		frame.getContentPane().add(addButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				cancelButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Go back to movie list. </p> </html>");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				SystemOwnerMovieInterface somi = new SystemOwnerMovieInterface();
				somi.frame.setVisible(true);
			}
		});
		cancelButton.setBounds(293, 276, 85, 21);
		frame.getContentPane().add(cancelButton);
		
		
	}
	 public static Movies movies;
	 
	 //if the movie is not null, this method gets the texts which contains movie information and calls the
	 //insertMovies() method.
	 private void addMovie() {

	        String name = movieNameField.getText();
	        String time = movieTimeField.getText();
	        int theater = Integer.parseInt(theaterNoField.getText());
	        String price = priceField.getText();
	        
	        movies = insertMovies(name,time,theater,price);
	        if(movies!= null){
	            frame.dispose();
	        }	
	 }
	 
//This method gets the movie name, movie time, movie theater and movie prices and insert the movie which
//has these informations inserted into the database
	    public static Movies insertMovies(String movieName, String movieTime, int movieTheater, String moviePrice) {

	        Movies movies = null;
	        Connection con = DatabaseConnection.createDB();
	        
	        try {
	            Statement statement = con.createStatement();
	            String query = "INSERT INTO movies (movieName, movieTime, theaterID, moviePrice)" +
	                    "VALUES(?,?,?,?)";

	            PreparedStatement preparedStatement = con.prepareStatement(query);
	            preparedStatement.setString(1, movieName);
	            preparedStatement.setString(2, movieTime);
	            preparedStatement.setInt(3, movieTheater);
	            preparedStatement.setString(4, moviePrice);

	            int countmovies = preparedStatement.executeUpdate();
	            if (countmovies > 0) {
	                movies = new Movies();
	                movies.setName(movieName);
	                movies.setTime(movieTime);
	                movies.setTheaterNo(movieTheater);
	                movies.setPrice(moviePrice);

	            }
	           // System.out.println("Movie created");
	            statement.close();
	            con.close();

	        } catch (SQLException e) {
	            System.out.println(e + " DUPLICATED DATA!");
	        }

	        return movies;
	    }
}
