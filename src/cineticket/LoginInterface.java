package cineticket;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Font;

public class LoginInterface {

	JFrame frame;
	private JTextField _Email;
	private JPasswordField _Password;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginInterface window = new LoginInterface();
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
	public LoginInterface() {
		
		
		UIManager.put("Button.background", new Color(128, 128, 255));			
		UIManager UI = new UIManager();
		UI.put("OptionPane.background",new Color(204, 204, 255));
		UI.put("Panel.background", new Color(204, 204, 255));
		UI.put("ToolTip.font", new Font("Arial", Font.BOLD, 16));
		//UI.put("ToolTip.size",new Size(30,30));
		
		frame = new JFrame();
		Image icon = Toolkit.getDefaultToolkit().getImage("movies-app.png");
		frame.setIconImage(icon);
		frame.setTitle("Log-in");
		frame.setBounds(100, 100, 539, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 10, 536, 339);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel emailField = new JLabel("E-mail address");
		emailField.setFont(new Font("Tahoma", Font.BOLD, 12));
		emailField.setForeground(Color.WHITE);
		emailField.setBounds(45, 127, 94, 28);
		panel.add(emailField);
		
		JLabel passwordField = new JLabel("Password");
		passwordField.setForeground(Color.WHITE);
		passwordField.setFont(new Font("Tahoma", Font.BOLD, 12));
		passwordField.setBounds(55, 171, 94, 28);
		panel.add(passwordField);
		
		_Email = new JTextField();
		_Email.setBounds(142, 133, 230, 19);
		panel.add(_Email);
		_Email.setColumns(10);
		
		_Password = new JPasswordField();
		_Password.setBounds(142, 177, 230, 19);
		panel.add(_Password);
		
		JButton loginButton = new JButton("Login");
		loginButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				loginButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3 ;  \" > Click to login to application. </p> </html>");
			}
		});
		loginButton.setForeground(Color.WHITE);
		loginButton.setBounds(161, 219, 85, 21);
		panel.add(loginButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setForeground(Color.WHITE);
		cancelButton.addMouseMotionListener(new MouseMotionAdapter() {
	          
            public void mouseMoved(MouseEvent e) {
            	cancelButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > Click to quit </p> </html>");
            }
        });
		cancelButton.setBounds(267, 219, 85, 21);
		panel.add(cancelButton);
		
		JButton toRegisterButton = new JButton("If you dont have an account, Click to register");
		toRegisterButton.setForeground(Color.WHITE);
		toRegisterButton.addMouseMotionListener(new MouseMotionAdapter() {
	          
            public void mouseMoved(MouseEvent e) {
            	toRegisterButton.setToolTipText("<html> <p style = \"margin: 10px; color: #8652b3; background: \" > click to register </p> </html>");
            }
        });
		toRegisterButton.setBounds(119, 273, 292, 21);
		panel.add(toRegisterButton);
		
			
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LoginInterface.class.getResource("/cineticket/movies-app.png")));
		lblNewLabel.setBounds(10, -35, 546, 374);
		panel.add(lblNewLabel);
		
		loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = _Email.getText();
                String password = String.valueOf(_Password.getPassword());

                customer = getRegisteredCustomer(email, password);

                if (customer != null) {
                	frame.dispose();
                	if(customer.getEmail().equals("123") && customer.getPassword().equals("123")){
                		SystemOwnerInterface soInterface = new SystemOwnerInterface();
                        soInterface.frame.setVisible(true);
                	}
                	else {
                    CustomerInterface customerInterface = new CustomerInterface();
                    customerInterface.frame.setVisible(true);
                	}
                }
                else{
                    JOptionPane.showMessageDialog(frame,"Invalid email or password","Try Again",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
		
        //Cancel Button exits from the application
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {           
				frame.dispose();
            }
        });

        //Register button directs to customer to the Register page.

        toRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	frame.dispose();
                RegisterDesign register = new RegisterDesign();
                register.frame.setVisible(true);
                
            }
        });
		
	}

	

    public static Customer customer;

    public static Customer getCustomer() {
		return customer;
	}

	//This method gets the related customer information which enters their email addresses and passwords
    // to the system
    private Customer getRegisteredCustomer(String email, String password) {
        Customer customer = null;
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:sqlite:movie.sqlite");
            Statement statement = con.createStatement();

            String query = "SELECT * FROM customers WHERE email=? AND password=?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                customer = new Customer();
                customer.setName(resultSet.getString("name"));
                customer.setSurname(resultSet.getString("surname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setPassword(resultSet.getString("password"));

            }

            statement.close();
            con.close();

        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(frame,"The system is not available" +
                            "at the moment. Please try again later. ","Try Again",
                    JOptionPane.ERROR_MESSAGE);
        }
        if(customer!= null)
        System.out.println("Welcome "+customer.getName());
        
        return customer;
		
		
	}
    
    public Customer getCustomer(String email,String password) {
    	
    	return getRegisteredCustomer(email,password);
    }

	}

