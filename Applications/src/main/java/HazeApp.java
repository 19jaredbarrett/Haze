import DbClasses.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;

public class HazeApp {
    public static final SqlServerConnection conn  = new SqlServerConnection();
    // we make global variables for all things JFrame, so we can access these variables outside of the class
    public static JPanel panel;
    public static JFrame frame;
    public static JTextField userText;
    public static JPasswordField pwText;
    public static JButton signInButton;
    public static JButton signUpButton;
    public static JTextArea appDesc;
    public static JScrollPane scrollPane;
    private static JLabel displayCreatedSuccess;

    public static void main(String[] args) throws SQLException {

        panel = new JPanel();
        frame = new JFrame();
        frame.setSize(720,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        displayHomePage(false);
        frame.setVisible(true);
    }

    /**
     * Displays the main page of the app
     * @throws SQLException throws a sql exception
     */
    public static void displayHomePage(boolean loggedIn) throws SQLException {
        panel.setLayout(null);
        clearPanel();
        if(loggedIn)
            updateUserInterfaceLoggedIn();
        else
            displayGuestInterface();
       /*
            From here on out, we are dealing with the database: adding applications, price, numDownloads table
        */

        // set apps label
        JLabel appsLabel = new JLabel("Applications");
        appsLabel.setBounds(140, 50, 80, 25);
        panel.add(appsLabel);
        // create ScrollPane with scrollbar, set the table as what we're showing
        // get a table with apps
        scrollPane = conn.getAppsPane(1, 1);
        panel.add(scrollPane);
        // create the text area that will display the description of the app
        appDesc = new JTextArea("App: Click on an app to reveal! \n\nDescription: ");
        appDesc.setBounds(370, 80, 300 , 250);
        appDesc.setLineWrap(true);
        appDesc.setWrapStyleWord(true);
        panel.add(appDesc);
    }


    private static void displayGuestInterface()  {
        // username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // username entry field
        userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        panel.add(userText);

        // password label
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setBounds(250, 20, 80, 25);
        panel.add(pwLabel);

        // password entryfield
        pwText = new JPasswordField(20);
        pwText.setBounds(330, 20, 165, 25);
        panel.add(pwText);

        // sign in/up buttonsJLabel signInButton = new JButton("Sign in");
        signInButton = new JButton("Sign in");
        signInButton.setBounds(500, 20, 80, 25);
        panel.add(signInButton);
        signUpButton = new JButton("Sign up");
        signUpButton.setBounds(500, 50, 80, 25);
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    displayCreateAccountInterface();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        panel.add(signUpButton);

        // add sign in/sign up action handler
        SignInActionHandler signInHandler = new SignInActionHandler();
        signInButton.addActionListener(signInHandler);
    }
    /**
     * Function: clear the Jframe AKA make blank
     */
    public static void clearPanel() {
        for(Component c :panel.getComponents())
            panel.remove(c);
        frame.invalidate();
        frame.repaint();
    }

    /**
     * Function: create a interface that allows the user
     * to create a username and password.
     * This also will insert username and password into database.
     */
    public static void displayCreateAccountInterface() throws SQLException {
        // clear the jframe
        clearPanel();

        // username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // username entry field
        userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        panel.add(userText);

        // password label
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setBounds(250, 20, 80, 25);
        panel.add(pwLabel);

        // password entryfield
        pwText = new JPasswordField(20);
        pwText.setBounds(330, 20, 165, 25);
        panel.add(pwText);

        // label confirm password
        JLabel confPassLabel = new JLabel("Confirm Password:");
        confPassLabel.setBounds(200, 50, 150, 25);
        panel.add(confPassLabel);

        // confirm the password
        JPasswordField confPassField = new JPasswordField(20);
        confPassField.setBounds(330, 50, 165, 25);
        panel.add(confPassField);
        panel.add(confPassField);


        //sign up button
        JButton registerUser = new JButton("Register");
        registerUser.setBounds(500, 20, 90, 25);
        // mouse Listener: creates user based on several conditions:
        // user doesn't exist, passwords are equal, username provided
        registerUser.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String user = userText.getText();
                char[] pass = pwText.getPassword();
                char[] confPass = confPassField.getPassword();
                String displayString;
                boolean isCreated = false;
                if(user.length() <= 5) {
                    displayString = "must provide username(>5) (T＿T)";
                } else if(pass.length <= 5) {
                    displayString = "must provide password(>5) (T＿T)";
                } else if (Arrays.equals(pass, confPass)) {

                    try {
                        isCreated = conn.registerUser(user, pass);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if(!isCreated) {
                        displayString = "User already exists ¯\\_(⊙︿⊙)_/¯";
                    } else {
                        displayString = "Successful Creation (☞ﾟヮﾟ)☞ ☜(ﾟヮﾟ☜)";
                    }
                } else {
                    displayString = "*Must match passwords (◔‸◔ )ʃ*";
                }
                // display the home page with the label
                try {
                    displayHomePage(false);
                    displaySuccess(displayString, isCreated);

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } // end method mouseClicked
        });
        panel.add(registerUser);

        //home button
        JButton homeButton = new JButton("Home");
        homeButton.setBounds(500, 50, 80, 25);
        // go to home page
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    displayHomePage(false);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        panel.add(homeButton);

        frame.invalidate();
        frame.repaint();
    }

    /**
     * displays whether we successfully logged in, signed out
     * @param displayString sets the display to either a successful login/logout or unsuccessful
     * @param isSuccess sets the color, red or green
     */
    public static void displaySuccess(String displayString, boolean isSuccess) {
        if(displayCreatedSuccess != null)
            panel.remove(displayCreatedSuccess);
        displayCreatedSuccess = new JLabel(displayString);
        displayCreatedSuccess.setBounds(230, 50, 300, 25);
        if(isSuccess)
            displayCreatedSuccess.setForeground(Color.BLUE);
        else
            displayCreatedSuccess.setForeground(Color.RED);
        panel.add(displayCreatedSuccess);
        panel.repaint();
    }


    /**
     * updates user interface
     */
    public static void updateUserInterfaceLoggedIn() {
        panel.remove(0);
        panel.remove(1);
        panel.remove(2);
        panel.remove(userText);
        panel.remove(pwText);
        panel.remove(signUpButton);
        User currUser = conn.getCurrentUser();
        JLabel loggedIn = new JLabel("Logged in as: " + currUser.getUsername() + ", AccessLevel = " + currUser.getAccessLevel());
        loggedIn.setBounds(10, 20, 400, 25);
        loggedIn.setForeground(Color.GREEN);
        panel.add(loggedIn);

        JButton buyCurrentApp = new JButton("Buy App");
        buyCurrentApp.setBounds(370, 340, 80 , 25);
        panel.add(buyCurrentApp);

        JLabel balanceLabel = new JLabel("User Balance: " + currUser.getBalance());
        balanceLabel.setBounds(460, 340, 160, 25);
        panel.add(balanceLabel);

        if(currUser.getAccessLevelInt() == 2 ) {
            //This1sMyRealPa$$word admin example
            JButton openAdminInterface = new JButton("Open Admin Panel");
            openAdminInterface.setBounds(10, 390, 140, 25);
            panel.add(openAdminInterface);
        }

        JButton signOutButton = new JButton("Sign Out");
        signOutButton.setBounds(500, 20, 90, 25);
        signOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                try {
                    displayHomePage(false);
                    displaySuccess("Logged out Successfully (☞ ͡° ͜ʖ ͡°)☞", true);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        panel.add(signOutButton);
        frame.invalidate();
        frame.repaint();
    }


}
