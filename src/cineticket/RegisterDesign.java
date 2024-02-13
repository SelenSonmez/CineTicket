package cineticket;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.DriverManager;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.ImageIcon;

public class RegisterDesign {

	JFrame frame;
	private JPasswordField _Password;
	private JTextField _Email;
	private JTextField _Surname;
	private JPasswordField _ConfirmPassword;
	private JTextField _Name;
	public Customer customer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterDesign window = new RegisterDesign();
					window.frame.setVisible(true);
					Customer customer = window.customer;
					if(customer!=null){
						System.out.println(customer.getName() + " " + customer.getSurname() + ", you are registered successfully");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
	}

	/**
	 * Create the application.
	 */

	//Creating buttons in interface
	public RegisterDesign() {
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setIconImage(icon);
		frame.setBounds(100, 100, 572, 405);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(230, 230, 255));
		frame.setTitle("Sign-Up");
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(30, 136, 72, 41);
		frame.getContentPane().add(lblNewLabel);
		frame.setLocationRelativeTo(null);

		JLabel lblSurname = new JLabel("Surname");
		lblSurname.setBounds(30, 173, 95, 29);
		frame.getContentPane().add(lblSurname);

		JLabel lblEmailAddress = new JLabel("E-mail Address");
		lblEmailAddress.setBounds(30, 210, 110, 24);
		frame.getContentPane().add(lblEmailAddress);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(30, 231, 95, 41);
		frame.getContentPane().add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(30, 268, 110, 29);
		frame.getContentPane().add(lblConfirmPassword);

		_Password = new JPasswordField();
		_Password.setBounds(150, 241, 354, 20);
		frame.getContentPane().add(_Password);

		_Email = new JTextField();
		_Email.setBounds(150, 212, 354, 20);
		frame.getContentPane().add(_Email);
		_Email.setColumns(10);

		_Surname = new JTextField();
		_Surname.setColumns(10);
		_Surname.setBounds(150, 177, 354, 20);
		frame.getContentPane().add(_Surname);

		_ConfirmPassword = new JPasswordField();
		_ConfirmPassword.setBounds(150, 272, 354, 20);
		frame.getContentPane().add(_ConfirmPassword);

		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseMotionListener(new MouseMotionAdapter() {
		      
            public void mouseMoved(MouseEvent e) {
            	btnRegister.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to complete registration </p> </html>");
            }
        });
		btnRegister.setBackground(new Color(179, 179, 255));
		btnRegister.setBounds(175, 320, 95, 35);
		frame.getContentPane().add(btnRegister);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseMotionListener(new MouseMotionAdapter() {
		      
            public void mouseMoved(MouseEvent e) {
            	btnCancel.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to go back to login page </p> </html>");
            }
        });
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginInterface loginInterface = new LoginInterface();
				loginInterface.frame.setVisible(true);
			}
		});
		btnCancel.setBackground(new Color(102, 102, 255));
		btnCancel.setBounds(313, 321, 95, 32);
		frame.getContentPane().add(btnCancel);

		JLabel lblNewLabel_1 = new JLabel("Sign Up");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.BOLD, 30));
		lblNewLabel_1.setBounds(50, 42, 170, 83);
		frame.getContentPane().add(lblNewLabel_1);

		_Name = new JTextField();
		_Name.setBounds(150, 146, 354, 20);
		frame.getContentPane().add(_Name);
		_Name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(RegisterDesign.class.getResource("/cineticket/movies-app.png")));
		lblNewLabel_2.setBounds(135, -29, 600, 439);
		frame.getContentPane().add(lblNewLabel_2);


		//if customer is not null this message will be shown after the registration
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
				if(customer!= null) {
					JOptionPane.showMessageDialog(frame,"Register is successful ","Success",JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
					LoginInterface loginInterface = new LoginInterface();
					loginInterface.frame.setVisible(true);
				}   
			}
		});
		frame.setVisible(true);
	}

	private void register() {
		String name = _Name.getText();
		String surname = _Surname.getText();
		String email = _Email.getText();
		String password = String.valueOf(_Password.getPassword());
		String confirmPassword = String.valueOf(_ConfirmPassword.getPassword());

		if(!password.equals(confirmPassword)){
			JOptionPane.showMessageDialog(frame,"Passwords must be the same", "Try Again",JOptionPane.ERROR_MESSAGE);
			return;
		}

		if(name.equals("") || surname.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {
			JOptionPane.showMessageDialog(frame,"Please fill all the required information", "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		customer = addCustomerToDatabase(name,surname,email,password);

		if(customer!= null){
			frame.dispose();
		}	
	}
	private Customer addCustomerToDatabase(String name, String surname, String email, String password) {
		Customer customer = null;

		//customer informations saved in database
		try {
			Connection con = DriverManager.getConnection("jdbc:sqlite:movie.sqlite");
			Statement statement = con.createStatement();
			String query = "INSERT INTO customers (name, surname, email, password)" + "VALUES(?,?,?,?)";

			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,surname);
			preparedStatement.setString(3,email);
			preparedStatement.setString(4,password);

			int countCustomer = preparedStatement.executeUpdate();
			if(countCustomer > 0){
				customer = new Customer();
				customer.setName(name);
				customer.setSurname(surname);
				customer.setEmail(email);
				customer.setPassword(password);

			}
			statement.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customer;
	}

	public Customer addCustomer(String name, String surname, String email, String password) {
		return addCustomerToDatabase(name,surname,email,password);
	}
}

