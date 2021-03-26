import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class HazeApp {
    public static final JFrame frame = new JFrame("HazeApp");
    public static final SqlServerConnection conn  = new SqlServerConnection();

    public static void main(String[] args) throws SQLException {


        JFrame.setDefaultLookAndFeelDecorated(true);



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
        // add the list of apps to the jframe
        JPanel appList = new JPanel();
        frame.add(appList);
        String[] listOfApps = conn.getApps();
        appList.add(new JList<String>(listOfApps));



        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
