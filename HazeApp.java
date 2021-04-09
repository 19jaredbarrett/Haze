import javax.swing.*;

import java.sql.SQLException;

public class HazeApp {
    public static final SqlServerConnection conn  = new SqlServerConnection();
    // we make global variables for all things JFrame, so we can access these variables outside of the class
    public static JPanel defaultPanel;
    public static JPanel signUpPanel;
    public static JPanel userPanel;
    public static JFrame frame;
    public static JTextField userText;
    public static JPasswordField pwText;
    public static JButton signInButton;
    public static JButton signUpButton;
    public static JTextArea appDesc;

    public static void main(String[] args) throws SQLException {

        defaultPanel = new JPanel();
        frame = new JFrame();
        frame.setSize(720,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(defaultPanel);

        panel.setLayout(null);

        // username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        defaultPanel.add(userLabel);

        // username entry field
        userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        defaultPanel.add(userText);

        // password label
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setBounds(250, 20, 80, 25);
        defaultPanel.add(pwLabel);

        // password entryfield
        pwText = new JPasswordField(20);
        pwText.setBounds(330, 20, 165, 25);
        defaultPanel.add(pwText);

        // sign in/up buttonsJLabel signInButton = new JButton("Sign in");
        signInButton = new JButton("Sign in");
        signInButton.setBounds(500, 20, 80, 25);
        defaultPanel.add(signInButton);
        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(500, 50, 80, 25);
        defaultPanel.add(signUpButton);
        
        SignInActionHandler signInHandler = new SignInActionHandler();
        signInButton.addActionListener(signInHandler);
        SignUpActionHandler signUpHandler = new SignUpActionHandler();
        signUpButton.addActionListener(signUpHandler);

       /*
            From here on out, we are dealing with the database: adding applications, price, numDownloads table
        */

        // set apps label
        JLabel appsLabel = new JLabel("Applications");
        appsLabel.setBounds(140, 50, 80, 25);
        defaultPanel.add(appsLabel);
        // create ScrollPane with scrollbar, set the table as what we're showing
        // get a table with apps

        JScrollPane scrollPane = new JScrollPane(conn.getAppsTable());
        scrollPane.setBounds(10, 80, 350, 300 );
        defaultPanel.add(scrollPane);
        // create the text area that will display the description of the app
        appDesc = new JTextArea("App: \n" + "Description: ");
        appDesc.setBounds(370, 80, 300 , 300);
        appDesc.setLineWrap(true);
        appDesc.setWrapStyleWord(true);
        defaultPanel.add(appDesc);




        frame.setVisible(true);

    }

    /**
     * Function: clear the Jframe AKA make blank
     */
    public void clearJFrame() {
        frame = new JFrame();
    	frame.setSize(720,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Function: create a interface that allows the user
     * to create a username and password.
     * This also will insert username and password into database.
     */
    public void makeCreateAccountInterface() {
        signUpPanel = new JPanel();
    	
    	// username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        signUpPanel.add(userLabel);
        
     // username entry field
        userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        signUpPanel.add(userText);
        
     // password label
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setBounds(250, 20, 80, 25);
        signUpPanel.add(pwLabel);

        // password entryfield
        pwText = new JPasswordField(20);
        pwText.setBounds(330, 20, 165, 25);
        signUpPanel.add(pwText);
        
        //sign up button
        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(500, 20, 80, 25);
        signUpPanel.add(signUpButton);
        
        //sign in button
        signInButton = new JButton("Have an account?");
        signInButton.setBounds(500, 50, 80, 25);
        signUpPanel.add(signInButton);
        
        
        // Adding action listeners to buttons
        SignInActionHandler signInHandler = new SignInActionHandler();
        signInButton.addActionListener(signInHandler);
        SignUpActionHandler signUpHandler = new SignUpActionHandler();
        signUpButton.addActionListener(signUpHandler);
        frame.setVisible(true);
    }
    public static void CreateUserInterface(String user) {
    	userPanel = new JPanel();
    	
    	// username label at head of profile page
        JLabel userLabel = new JLabel("Welcome " + user + "!");
        userLabel.setBounds(10, 20, 80, 25);
        userPanel.add(userLabel);
        frame.setVisible(true);
    }
    
    public static void login(String username) {
    	clearJFrame();
        CreateUserInterface(username);
        frame.add(userPanel);

    }
    
    public static void signUp() {
    	clearJFrame();
    	makeCreateAccountInterface();
        frame.add(signUpPanel);
        
        SignUpActionHandler signUpHandler = new SignUpActionHandler();
        signUpButton.addActionListener(signUpHandler);
    }
}
}
