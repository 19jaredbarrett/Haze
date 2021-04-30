import DbClasses.ApplicationsTableModel;
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
    public static JTextField userText, searchText;
    public static JPasswordField pwText, confPwText;
    public static JButton signInButton;
    public static JButton signUpButton, searchBtn, signOutButton;
    public static JButton registerUser;
    public static JTextArea appDesc;
    public static JTable appsTable;
    public static JScrollPane scrollPane;
    private static JLabel displayCreatedSuccess;
    protected static JScrollPane userAppsPane;
    private static JLabel userAppsLbl;
    private static JButton adminRemoveBtn, modCommentBtn, memberReqBtn;
    private static JTextField buyCommentTxt;


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
        panel.setBackground(Color.lightGray);
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
        appsTable = conn.getAppsTable(ApplicationsTableModel.ORDER_BY_NAME, 1);
        HazeApp.scrollPane = new JScrollPane(appsTable);
        HazeApp.scrollPane.setBounds(10, 80, 350, 450 );
        panel.add(scrollPane);
        // create the text area that will display the description of the app
        appDesc = new JTextArea("App: Click on an app to reveal! \n\nDescription: ");
        appDesc.setBounds(370, 80, 300 , 250);
        appDesc.setLineWrap(true);
        appDesc.setWrapStyleWord(true);
        panel.add(appDesc);
        // add search functionality!
        addSearchButton();
    }


    private static void displayGuestInterface()  {
        conn.setCurrentUser(null);
        conn.setCurrentApp(null);
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
        confPwText = new JPasswordField(20);
        confPwText.setBounds(330, 50, 165, 25);
        panel.add(confPwText);
        panel.add(confPwText);


        //sign up button
        registerUser = new JButton("Register");
        registerUser.setBounds(500, 20, 90, 25);
        // mouse Listener: creates user based on several conditions:
        // user doesn't exist, passwords are equal, username provided
        registerUser.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String user = userText.getText();
                char[] pass = pwText.getPassword();
                char[] confPass = confPwText.getPassword();
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
     * An empty displayString parameter clears the success label of our jframe
     * @param displayString sets the display to either a successful login/logout or unsuccessful
     * @param isSuccess sets the color, red or green
     */
    public static void displaySuccess(String displayString, boolean isSuccess) {
        if(displayCreatedSuccess != null) {
            panel.remove(displayCreatedSuccess);
            // if the string is empty, we just want to clear the success label of our panel
            if(displayString.isEmpty()) {
                displayCreatedSuccess = null;
                panel.repaint();
                return;
            }
        } else if(displayString.isEmpty())
            return;
        displayCreatedSuccess = new JLabel(displayString);
        displayCreatedSuccess.setBounds(230, 50, 300, 25);
        if(isSuccess)
            displayCreatedSuccess.setForeground(Color.BLUE);
        else
            displayCreatedSuccess.setForeground(Color.RED);
        displayCreatedSuccess.setFont(new Font("Helvetica", Font.BOLD, 13));
        displayCreatedSuccess.setBackground(new Color(1f,1f,1f,0.5f));
        displayCreatedSuccess.setOpaque(true);
        panel.add(displayCreatedSuccess);
        panel.repaint();
    }


    /**
     * updates user interface
     */
    public static void updateUserInterfaceLoggedIn() throws SQLException {
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
        JLabel balanceLabel = new JLabel("User Balance: " + currUser.getBalance());
        balanceLabel.setBounds(460, 340, 160, 25);
        panel.add(balanceLabel);


        // add buyApp functionality
        JLabel commentLbl = new JLabel("Comment:");
        commentLbl.setBounds(370, 370, 75, 25);
        panel.add(commentLbl);
        buyCommentTxt = new JTextField();
        buyCommentTxt.setBounds(440,370, 200, 25);
        panel.add(buyCommentTxt);
        JButton buyCurrentApp = new JButton("Buy App");
        buyCurrentApp.setBounds(370, 340, 80 , 25);
        buyCurrentApp.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                String commentTxt = buyCommentTxt.getText();
                if (conn.getCurrentApp() == null) {
                    displaySuccess("Must select an App!", false);
                } else if (conn.getCurrentUser().getBalance() < conn.getCurrentApp().getPrice()) {
                    displaySuccess("You don't have enough money :[", false);
                } else {
                    boolean hasAlready = true;
                    try {
                        hasAlready = conn.buyApp(commentTxt);
                        if(!hasAlready) {
                            displaySuccess("Bought successfully", true);
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    balanceLabel.setText("User Balance: " + currUser.getBalance());
                    if(!hasAlready)
                        displaySuccess(conn.getCurrentApp().getAppName() + " purchased つ◕_◕つ" , true);
                    else {
                        displaySuccess("You have this app already!", false);
                    }
                }
            }
        });
        panel.add(buyCurrentApp);

        signOutButton = new JButton("Sign Out");
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
        // access level panel implementation !!!!!
        JButton accessInterfaceBtn = new JButton(currUser.getAccessLevel() + " Comment options");
        accessInterfaceBtn.setBounds(370, 400, 190, 25);
        if(currUser.getAccessLevelInt() >= 1) {
            panel.setBackground(Color.pink);
            JButton dispAllComments = new JButton("Display all");
            dispAllComments.setBounds(565, 400, 110, 25);
            panel.add(dispAllComments);
            dispAllComments.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    removeAccessInterface();
                    if(userAppsPane != null) {
                        panel.remove(userAppsPane);
                        userAppsPane = null;
                    }
                    try {
                        appsTable = conn.getAllUserAppsTable();
                        userAppsPane = new JScrollPane(appsTable);
                        addUserAppsPane(userAppsPane);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                    displaySuccess("All comments are meow displayed(⌐▨_▨)", true);
                }
            });

        }

            //dogelord admin example
        accessInterfaceBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                displayAccessInterface(conn.getCurrentUser());
                displaySuccess(currUser.getAccessLevel() + " interface opened (⌐▨_▨)", true);
            }
        });
            // moderator: add moderator button

        panel.add(accessInterfaceBtn);

        frame.invalidate();
        frame.repaint();
    }

    public static void removeAccessInterface() {

            if(memberReqBtn != null) {
                panel.remove(memberReqBtn);
                if(modCommentBtn != null) {
                    panel.remove(modCommentBtn);
                    if (adminRemoveBtn != null)
                        panel.remove(adminRemoveBtn);
                }

            }
            return;
    }
    /**
     * This method adds the interface for each access level: admin, moderator, member
     * @param currUser
     */
    public static void displayAccessInterface(User currUser) {
        if(userAppsPane != null) {
            panel.remove(userAppsPane);
            panel.remove(userAppsLbl);
            userAppsPane = null;
        }
        // this switch case ensures we are still able to
        switch(currUser.getAccessLevelInt()) {
            // admin
            case 2:
                adminRemoveBtn = new JButton("Remove UserApp comment");
                adminRemoveBtn.setBounds(405, 430, 200, 25);
                adminRemoveBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        if(conn.getCurrentUserApp() == null) {
                            displaySuccess("Must have one selected", false);
                            return;
                        }
                        boolean isSuccess = false;
                        try {
                            isSuccess = conn.removeUserApp();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if(isSuccess) {
                            displaySuccess("Removed successfuly ;)", true);
                        } else {
                            displaySuccess("Already deleted :]", false);
                        }
                    }
                });
                panel.add(adminRemoveBtn );
            // moderator
            case 1:
                modCommentBtn = new JButton("Modify UserApp comment");
                modCommentBtn.setBounds(415, 460, 180, 25);
                modCommentBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        String currentComment = buyCommentTxt.getText();
                        if(conn.getCurrentUserApp() == null) {
                            displaySuccess("Must have one selected", false);
                            return;
                        }
                        boolean isSuccess = false;
                        try {
                            isSuccess = conn.updateUserApp(currentComment);
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        if(isSuccess) {
                            displaySuccess("Updated successfully ;)", true);
                        } else {
                            displaySuccess("Already deleted :]", false);
                        }
                    }
                });
                panel.add(modCommentBtn);
            // member
            default:
                memberReqBtn = new JButton("Request Application");
                memberReqBtn.setBounds(415, 490, 180, 25);
                memberReqBtn.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                    }
                });
                panel.add(memberReqBtn);
        }
    }

    /**
     * Adds search button to our program!
     */
    private static void addSearchButton() {
        searchText = new JTextField(20);
        searchText.setBounds(100, 540, 165, 25);
        searchBtn = new JButton("Search");
        searchBtn.setBounds(10, 540,85,25);
        searchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {

                String searchString = searchText.getText();
                if(searchString.isEmpty())
                    displaySuccess("Provide a search string please!", false);
                else if(searchString.length() > 100)
                    displaySuccess("LESS THAN 100 CHARS", false);
                else {
                    displaySuccess("Searching...¯\\_(ツ)_/¯ \uD83E\uDD14", true);
                    panel.remove(scrollPane);
                    try {
                        appsTable = conn.getAppsTable(searchString);
                        scrollPane = new JScrollPane(appsTable);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    scrollPane.setBounds(10, 80, 350, 450 );
                    panel.add(scrollPane);
                    panel.repaint();
                }

            }
        });
        panel.add(searchBtn);
        panel.add(searchText);
    }

    public static void addUserAppsPane(JScrollPane userAppsPaneParam) {
        // Add userApps Label, below will show the user's apps

            if(userAppsPane != null) {
                if(userAppsLbl != null)
                    panel.remove(userAppsLbl);
                panel.remove(userAppsPane);
            }

            if(conn.getCurrentApp() != null) {
                userAppsLbl = new JLabel(conn.getCurrentApp().getAppName() + " comments", SwingConstants.CENTER);

            } else {
                userAppsLbl = new JLabel("All apps");
            }
        userAppsLbl.setBounds(370, 425, 315, 25);

        panel.add(userAppsLbl);
        userAppsPane = userAppsPaneParam;
        userAppsPane.setBounds(370, 450, 315, 145);
        panel.add(userAppsPane);
    }

}
