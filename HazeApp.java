import javax.swing.*;
import javax.swing.border.Border;
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

    public static void main(String[] args) throws SQLException, SQLException {

        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
        frame.setSize(720,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        panel.setLayout(null);

        // username label
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        // username entry field
        JTextField userText = new JTextField(20);
        userText.setBounds(80, 20, 165, 25);
        panel.add(userText);

        // password label
        JLabel pwLabel = new JLabel("Password:");
        pwLabel.setBounds(250, 20, 80, 25);
        panel.add(pwLabel);

        // password entryfield
        JPasswordField pwText = new JPasswordField(20);
        pwText.setBounds(330, 20, 165, 25);
        panel.add(pwText);

        // sign in/up buttons
        JButton signInButton = new JButton("Sign in");
        signInButton.setBounds(500, 20, 80, 25);
        panel.add(signInButton);
        JButton signUpButton = new JButton("Sign up");
        signUpButton.setBounds(500, 50, 80, 25);
        panel.add(signUpButton);

       /*
            From here on out, we are dealing with the database: adding applications, price, numDownloads table
        */

        // set apps label
        JLabel appsLabel = new JLabel("Applications");
        appsLabel.setBounds(140, 50, 80, 25);
        panel.add(appsLabel);
        // create ScrollPane with scrollbar, set the table as what we're showing
        // get a table with apps
        JScrollPane scrollPane = new JScrollPane(conn.getAppsTable());
        scrollPane.setBounds(10, 80, 350, 300 );
        panel.add(scrollPane);



        frame.setVisible(true);

    }
}