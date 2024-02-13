package cineticket;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ActionEvent;

public class DeleteMovieInterface {

	JFrame frame;
	private JTextField movieNameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteMovieInterface window = new DeleteMovieInterface();
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
	public DeleteMovieInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setIconImage(icon);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setLocationRelativeTo(null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter the movie name you want to delete");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(50, 68, 306, 32);
		panel.add(lblNewLabel);
		
		movieNameField = new JTextField();
		movieNameField.setBounds(120, 136, 164, 19);
		panel.add(movieNameField);
		movieNameField.setColumns(10);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				cancelButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > click to return. </p> </html>");
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		cancelButton.setBounds(216, 206, 85, 21);
		panel.add(cancelButton);
		
		//This gets the text from movieNameField and calls the getMovieNameToBeDeleted() method.
		JButton deleteButton = new JButton("Delete");
		deleteButton.addMouseMotionListener(new MouseMotionAdapter() {
	          
            public void mouseMoved(MouseEvent e) {
            	deleteButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to delete a movie </p> </html>");
            }
        });
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name = movieNameField.getText();
				movies = getMovieNameToBeDeleted(name);
				//if getMovieNameToBeDeleted() method does not return null, deleteMovies()
				//method is called
				if(movies!= null) {
					deleteMovies(movies);
					//A message displays on the screen which says movie is deleted successfully
				JOptionPane.showMessageDialog(frame,"Movie deleted! ","Success",JOptionPane.INFORMATION_MESSAGE);
				frame.dispose();
				//After the deletion, system owner is directed to the interface which has the movie list
				SystemOwnerMovieInterface somi = new SystemOwnerMovieInterface();
        		somi.frame.setVisible(true);
				}
				//If the getMovieNameToBeDeleted() method returns null, an error message displays
				//that says there is no movie in the database that has this name
				else {
					JOptionPane.showMessageDialog(frame,"There is no movie with this name, try again.","Fail",
                            JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		deleteButton.setBounds(101, 206, 85, 21);
		panel.add(deleteButton);
	}
	
	public Movies movies;
	
	//This movie gets the movie name and checks if it is already placed in the database and returns the movie
	//that has already in the database
	public static Movies getMovieNameToBeDeleted(String movieName) {
		 Movies movies = null;
	        Connection con = DatabaseConnection.createDB();
	        try {
	            Statement statement = con.createStatement();
	            String query = "SELECT movieName FROM movies WHERE movieName = ?";

	            PreparedStatement preparedStatement = con.prepareStatement(query);

	            preparedStatement.setString(1, movieName);

	            ResultSet resultSet = preparedStatement.executeQuery();

	            if(resultSet.next()){
	                movies = new Movies();
	                movies.setName(resultSet.getString("movieName"));
	                
	            }


	            //System.out.println("Movie exists");
	            statement.close();
	            con.close();

	        } catch (SQLException e) {
	           // System.out.println(e + " Movie does not exist!");
	            
	        }
		
		
		return movies;
		
	}
	
	//IThis method gets the movie which has returned from the getMovieNameToBeDeleted() method and deletes it
	//from the database 
	 public static Movies deleteMovies(Movies movie) {
	        Connection con = DatabaseConnection.createDB();
	        String movieName = null;
	        if(movie==null) {
	        	
	        }
	        else {
	        	movieName = getMovieNameToBeDeleted(movie.getName()).getName();
	        }
	        
	        try {
	            Statement statement = con.createStatement();
	            String query = "DELETE FROM movies WHERE movieName = ?";

	            PreparedStatement preparedStatement = con.prepareStatement(query);

	            preparedStatement.setString(1, movieName);

	            preparedStatement.executeUpdate();

	           // System.out.println("Movie deleted");
	            statement.close();
	            con.close();

	        } catch (SQLException e) {
	            System.out.println(e + "DELETE FAILED!");
	            
	        }
			return movie;
	       
	    }


}
