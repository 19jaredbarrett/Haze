package Hazemain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JTextField;
 


//import Haze.HazeApp;

public class HazeApp {
    public static final JFrame frame = new JFrame("HazeApp");
    //public static final SqlServerConnection conn  = new SqlServerConnection();
	//public static final String users = null;
    Map<String, String> users = new HashMap<>();
	public User currentUser = new User("","");

    public static void main(String[] args) throws SQLException {

    	
		 User a1 = new User("Username", "Password");
		 HazeApp AOT = new HazeApp();

     // Load App and User info
        try { 
        	Scanner scanner = new Scanner(new File("login.txt"));
        	while(scanner.hasNextLine()) {
        		String line = scanner.nextLine();
        		String userAndPass[] = line.split(",");
        		AOT.users.put(userAndPass[0],userAndPass[1]);
        	}
        	scanner.close();
        } catch(FileNotFoundException e) {
			System.out.println("login.txt file Not Found");
		}
        AOT.users.put(a1.username, a1.password);
        User user1 = new User("testName", "testPassword");
        AOT.users.put(user1.username, user1.password);
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        


        JTextField jtf = new JTextField("Username");
        JTextField jpwf = new JTextField("Password");
        JButton logInBttn = new JButton("Sign in");
        JLabel lbl = new JLabel("Don't have an account yet?");
        JButton signUpBtn = new JButton("Sign up");
        JPanel statusPanel = new JPanel();
        JPanel jp1 = new JPanel();
        
     // action listener for signUpBttn button
 		signUpBtn.addActionListener(new ActionListener() {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				String username = jtf.getText();
 				String password = jpwf.getText();
 				JFrame signupFrame = new JFrame("Signup");
 				signupFrame.setLayout(new FlowLayout());
 				User u = new User(username, password);
 				if (u.signUp(AOT)) {
 					JLabel success = new JLabel("Account created! Please click login.");
 					signupFrame.add(success);
 				} else {
 					JLabel fail = new JLabel("Account creation failed! You have already have a login account.");
 					signupFrame.add(fail);
 				}
 				jtf.setText("Username");
 				jpwf.setText("Password");
 				signupFrame.setSize(300, 60);
 				signupFrame.setVisible(true);
 			}
 		});
 		
 		
 	// action listener for login button
 			logInBttn.addActionListener(new ActionListener() {
 				@Override
 				public void actionPerformed(ActionEvent e) {
 					// read username and password text fields into string variables
 					String username = jtf.getText();
 					String password = jpwf.getText();
 					
 					JFrame loginFrame = new JFrame("Login");
 					loginFrame.setLayout(new FlowLayout());
 					
 					User u = new User(username, password);
 		
 						AOT.currentUser = u;
 						AOT.currentUser = new User("", "");
 						 
 						JLabel success = new JLabel("Login successful!");
 						loginFrame.add(success);
 						statusPanel.removeAll();
 						JLabel status = new JLabel("Currently logged in as User: " + username);
 						JButton logoutBtn = new JButton("Logout");
 						JFrame logoutFrame = new JFrame("Logout");
 						logoutFrame.setLayout(new FlowLayout());
 						logoutBtn.addActionListener(new ActionListener() {
 							@Override
 							public void actionPerformed(ActionEvent e) {
 								AOT.currentUser = new User("","");
 								JLabel logoutSuccess = new JLabel("Logout successful!");
 								logoutFrame.add(logoutSuccess);
 								logoutFrame.setSize(300, 60);
 								statusPanel.removeAll();
 								frame.setVisible(true);
 								logoutFrame.setVisible(true);
 							}
 						});
 						statusPanel.add(status);
 						statusPanel.add(logoutBtn);
 						frame.setVisible(true);
 				}
 			});


 	    statusPanel.setLayout(new FlowLayout());
        jp1.setLayout(new FlowLayout());
        frame.setLayout(new BorderLayout());
        frame.add(statusPanel, BorderLayout.NORTH);
        frame.add(jp1, BorderLayout.CENTER);

        statusPanel.add(jtf);
	    statusPanel.add(jpwf);
		statusPanel.add(logInBttn);
		jp1.add(lbl);
		jp1.add(signUpBtn);
		
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

    }
}

