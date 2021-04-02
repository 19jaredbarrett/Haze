import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HazeApp {
    public static final SqlServerConnection conn  = new SqlServerConnection();
    public static JLabel userLabel;
    public static JTextField userText;
    public static JLabel pwLabel;
    public static JPasswordField pwText;
    public static JButton signInbutton;
    public static JButton signUpButton;

    public static void main(String[] args) throws SQLException {
    	
    	JPanel panel = new JPanel();
    	JFrame frame = new JFrame();
    	frame.setSize(720,640);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.add(panel);
    	
    	panel.setLayout(null);
    	
    	JLabel userLabel = new JLabel("User:");
    	userLabel.setBounds(10, 20, 80, 25);
    	panel.add(userLabel);
    	
    	JTextField userText = new JTextField(20);
    	userText.setBounds(50, 20, 165, 25);
    	panel.add(userText);
    	
    	JLabel pwLabel = new JLabel("Password:");
    	pwLabel.setBounds(230, 20, 80, 25);
    	panel.add(pwLabel);
    	
    	JPasswordField pwText = new JPasswordField(20);
    	pwText.setBounds(300, 20, 165, 25);
    	panel.add(pwText);
    	
    	JButton signInButton = new JButton("Sign in");
    	signInButton.setBounds(480, 20, 80, 25);
    	panel.add(signInButton);
    	JButton signUpButton = new JButton("Sign up");
    	signUpButton.setBounds(570, 20, 80, 25);
    	panel.add(signUpButton);
    
        // add the list of apps to the jframe
        JPanel appList = new JPanel();
        frame.add(appList);
        String[] listOfApps = conn.getApps();
        appList.add(new JList<String>(listOfApps));

        frame.setVisible(true);

    }
}
