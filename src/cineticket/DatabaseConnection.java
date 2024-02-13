package cineticket;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class DatabaseConnection {

    //Database which contains related customer, movie, seats and theater information is created and connected
    //to the java project

    public static Connection createDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:movie.sqlite");
            //System.out.println("Connected!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e + " ");
        }
        return con;

    }

    //Related tables are created by sqlite to be executed for once

    public static void CreateTables() {

        Connection con = createDB();
        String createCustomerTable = "CREATE TABLE IF NOT EXISTS customers(" +
                "name varchar(200) NOT NULL," +
                "surname varchar(200) NOT NULL," +
                "email varchar(200) NOT NULL UNIQUE," +
                "password varchar(200) NOT NULL," +
                "cardNumber varchar(200)"+
                ")";
        String createmovieTable = "CREATE TABLE IF NOT EXISTS movies(" +
                "movieName varchar(200) NOT NULL UNIQUE," +
                "movieTime varchar(200) NOT NULL," +
                "theaterID int(11) NOT NULL UNIQUE," +
                "moviePrice varchar(200) NOT NULL" +
                ")";

        
        String createSeats = "CREATE TABLE IF NOT EXISTS seat("+
        		"theaterID int(11) NOT NULL,"+
        		"seatNumber int(3) NOT NULL,"+
        		"isTaken int(1) NOT NULL"+
        		")";
        String createCardInfo = "CREATE TABLE IF NOT EXISTS cardInfo("+
        		"cardNumber TEXT UNIQUE,"+
        		"expirationMonth INTEGER, "+
        		"expirationYear INTEGER, "+
        		"CVC INTEGER "+
        		")";
        
       /* String querySeat = "INSERT INTO seat(theaterID, seatNumber, isTaken)"+
       		 "VALUES(?,?,0)";                   
        try {
        	
        	
			for(int i = 1;i<6;i++) {
				for(int j = 1;j<10;j++) {
				PreparedStatement preparedStatement = con.prepareStatement(querySeat);
				preparedStatement.setInt(1, i);
				preparedStatement.setInt(2, j);
				preparedStatement.executeUpdate();
				
			}
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}*/


        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(createCustomerTable);
            statement.executeUpdate(createmovieTable);
            statement.executeUpdate(createCardInfo);
            //statement.executeUpdate(createSeats);
            System.out.println("Created successfully");
        } catch (SQLException e) {
            System.out.println(e + "");

        }

    }

    //This method is for the system owner to insert new movies into the system

    public static Movies movies;

    public static Movies insertMovies(String movieName, String movieTime, int movieTheater, String moviePrice) {

        Movies movies = null;
        Connection con = createDB();
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
            System.out.println("Movie created");
            statement.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e + " DUPLICATED DATA!");
        }

        return movies;
    }

    //This method is for the system owner to delete movies that are not shown in the theater
    // from the system

    public static Movies deleteMovies(String movieName) { 
    	Movies movies = null;
        Connection con = createDB();
        try {
            Statement statement = con.createStatement();
            String query = "DELETE FROM movies WHERE movieName = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, movieName);

            preparedStatement.executeUpdate();

            System.out.println("Movie deleted");
            statement.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e + "DELETE FAILED!");
        }
        return movies;
    }
    public static ArrayList<Boolean> getSeats(int theaterID){ //This method returns isTaken attributes of seats from the database and puts it in an arraylist.
    	ArrayList<Boolean> list = new ArrayList<>();
    	String querySelectSeat = "SELECT isTaken From seat WHERE theaterID = ?";
    	
    	Connection con = createDB();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(querySelectSeat);
			preparedStatement.setInt(1, theaterID);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				if(resultSet.getInt("isTaken") == 1) {
					list.add(true);
					continue;
				}
				list.add(false);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return list;    	
    }
    public static Tickets ReserveTickets(String cMail, String mName, int seatNumber) {
    	Tickets ticket = null;
    	String query ="INSERT INTO tickets (customerEmail, movieName, seatNumbers)" +
                "VALUES(?,?,?)";
    	
    	Connection con = createDB();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,cMail);
			preparedStatement.setString(2,mName);
			preparedStatement.setInt(3,seatNumber);
			int countTickets = preparedStatement.executeUpdate();
            if (countTickets > 0) {
                ticket = new Tickets();
                ticket.setCustomerMail(cMail);
                ticket.setMovieName(mName);
                ticket.setAmountOfTickets(seatNumber);
						
		
            } 
            System.out.println("done");	
		}
		catch (SQLException e) {
			//When there is a SQLException while updating data, it will be catched here.
			e.printStackTrace();
		}
		return ticket;
    	
    }
    
    public static void TakeSeat(int theaterID,int seatNumber){ //This method updates data whenever a customer takes a seat.
    	String query = "UPDATE seat SET isTaken = ? WHERE seatNumber = ? AND theaterID = ?";
    	
    	
    	Connection con = createDB();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,1);
			preparedStatement.setInt(2,seatNumber);
			preparedStatement.setInt(3,theaterID);
			preparedStatement.executeUpdate();
							
			System.out.println("Update Successful!!");
			con.close();
		} 
		catch (SQLException e) {
			//When there is a SQLException while updating data, it will be catched here.
			System.out.println("Update failed!");
			e.printStackTrace();
		}
      	
    }
    
    public static void UpdateSeats(int theaterID, int key){ //This method is for updating data from seat table in the database.
    	String query = "UPDATE seat SET isTaken = ? where seatNumber = ? AND theaterID = ?";
    	
    	Connection con = createDB();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,0);
			preparedStatement.setInt(2,key);
			preparedStatement.setInt(3,theaterID);
			preparedStatement.executeUpdate();
							
			System.out.println("Update Successful!!");
		} 
		catch (SQLException e) {
			//When there is a SQLException while updating data, it will be catched here.
			System.out.println("Update failed!");
			e.printStackTrace();
		}
      	
    }
    
    public static String[] getMovieNamesForCombobox() { //This method gets all the movie names from the database and puts it in an string array. This array will be used for assigning combobox elements later.
    	ArrayList<String>movieName =new ArrayList<>();
    	String[] movieNameArray = null;
		try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement =con.createStatement();

            String query = "SELECT * FROM movies LIMIT 0,5";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            int count = 0;
            while (resultSet.next()){

                // it gets the movies from the database and adds to the related movie attribute
                //array lists to be shown later in the movie list

                movieName.add(resultSet.getString("movieName"));               
                count++;
            }
            movieNameArray = new String[movieName.size()];
            
            for(int i = 0; i<movieName.size(); i++) {
            	movieNameArray[i] = movieName.get(i);
            	
            }       
            statement.close();
            con.close();
        }
        catch (Exception e) {

            //When the system couldn't get the movie information from the database, an error message will be displayed
            //on the screen.

            JOptionPane.showMessageDialog(CinemaSeatsDesign.frame,"The system could not" +
                            " fetch the movie at the moment. Please try again later.","Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
		return movieNameArray;
    }
    
    public static int getTheaterID(String movieName) { // This method returns theaterID from the database by getting movie name.
    	
    	int theaterID = 0;
    	
    	try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement =con.createStatement();

            String query = "SELECT theaterID FROM movies WHERE movieName = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,movieName);

            ResultSet resultSet = preparedStatement.executeQuery();
            
            theaterID = resultSet.getInt("theaterID");

          
            statement.close();
            con.close();
        }
        catch (Exception e) {

            //When the system couldn't get the movie information from the database, an error message will be displayed
            //on the screen.

            JOptionPane.showMessageDialog(CinemaSeatsDesign.frame,"The system could not" +
                            " fetch the movie at the moment. Please try again later.","Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
		return theaterID;
    	
    }
    
    public static void cancelTicket(String customerMail, String movieName, int seatNumber) {
    	
        try {
        	Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement =con.createStatement();
            String query = "DELETE FROM tickets WHERE customerEmail = ? AND movieName = ? AND seatNumbers = ?";

            PreparedStatement preparedStatement = con.prepareStatement(query);

            preparedStatement.setString(1, customerMail);
            preparedStatement.setString(2, movieName);
            preparedStatement.setInt(3, seatNumber);

            preparedStatement.executeUpdate();
           
            DatabaseConnection.UpdateSeats(getTheaterID(movieName),seatNumber);
            
          
            statement.close();
            con.close();

        } catch (SQLException a) {
            System.out.println(a + "DELETE FAILED!");
        }
    }

    public static void insertCard(String mail,String cardNumber, int expirationMonth, int expirationYear, int CVC) {

        Connection con = createDB();
        try {
            Statement statement = con.createStatement();
            String query = "INSERT INTO cardInfo (cardNumber, expirationMonth, expirationYear, CVC)" +
                    "VALUES(?,?,?,?)";
            
            String query2 = "UPDATE customers SET cardNumber = ? where email = ?";
            
            PreparedStatement preparedStatement2 = con.prepareStatement(query2);
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.setInt(2, expirationMonth);
            preparedStatement.setInt(3, expirationYear);
            preparedStatement.setInt(4, CVC);
            preparedStatement.executeUpdate();
            preparedStatement2.setString(1, cardNumber);
            preparedStatement2.setString(2, mail);
            
            preparedStatement2.executeUpdate();

            System.out.println("Card created");
            statement.close();
            con.close();

        } catch (SQLException e) {
            System.out.println(e + " DUPLICATED DATA!");
        }    	
    }
    
    public static Card getCardInfoOfCustomer(String mail) {
    	Card card = null;
    	try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement =con.createStatement();

            String query = "SELECT cardNumber FROM customers WHERE email = ?";
            
            String cardQuery = "SELECT expirationMonth, expirationYear, CVC From cardInfo WHERE cardNumber = ? ";

            PreparedStatement preparedStatement = con.prepareStatement(query);
            PreparedStatement preparedStatement2 = con.prepareStatement(cardQuery);
            preparedStatement.setString(1,mail);
         
            ResultSet resultSet = preparedStatement.executeQuery();
            
            
            String cardNumber = resultSet.getString("cardNumber"); 
            preparedStatement2.setString(1, cardNumber);
            ResultSet resultSet2 = preparedStatement2.executeQuery();
            int expirationMonth = resultSet2.getInt("expirationMonth");
            int expirationYear = resultSet2.getInt("expirationYear");
            int CVC = resultSet2.getInt("CVC");
            card = new Card(cardNumber,expirationMonth,expirationYear,CVC);
            
            statement.close();
            con.close();
        }
        catch (Exception e) {

            //When the system couldn't get the movie information from the database, an error message will be displayed
            //on the screen.

            JOptionPane.showMessageDialog(CinemaSeatsDesign.frame,"The system could not" +
                            " fetch the movie at the moment. Please try again later.","Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
    	return card;
    }
}
    
    





