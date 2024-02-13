package cineticket;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.UIManager;

public class Application {


    public static void main(String[] args) {

        //These are executed just only for once to create related tables

//        DatabaseConnection.createDB();
//        DatabaseConnection.CreateTables();
        /*DatabaseConnection.insertMovies("Batman","11.00",1,"$5");
        DatabaseConnection.insertMovies("Eiffel","11.00",2,"$5");
        DatabaseConnection.insertMovies("Dune","11.00",3,"$5");
        DatabaseConnection.insertMovies("Morbius","11.00",4,"$5");
        DatabaseConnection.insertMovies("Venom","11.00",5,"$5");*/
    
    	
       LoginInterface loginInterface = new LoginInterface();
       loginInterface.frame.setVisible(true);     

    }
}
