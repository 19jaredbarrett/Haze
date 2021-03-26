package Haze;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HazeApp {


	public static void main(String[] args){


		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("HazeApp");

		JTextField jtf = new JTextField("Username");
		JTextField jpwf = new JTextField("Password");
		JButton logInBttn = new JButton("Sign in");
		JLabel lbl = new JLabel("Don't have an account yet?");
		JButton signUpBttn = new JButton("Sign up");
		JPanel jp1 = new JPanel();
		JPanel jp2 = new JPanel();

		
		jp1.setLayout(new FlowLayout());
		jp2.setLayout(new FlowLayout());
		frame.setLayout(new BorderLayout());
		frame.add(jp1, BorderLayout.NORTH);
		frame.add(jp2, BorderLayout.CENTER);
		
		jp1.add(jtf);
		jp1.add(jpwf);
		jp1.add(logInBttn);
		jp2.add(lbl);
		jp2.add(signUpBttn);
		
		frame.setSize(600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}