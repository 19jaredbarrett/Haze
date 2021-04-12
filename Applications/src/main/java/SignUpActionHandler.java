import DbClasses.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/*
    This class will handle all of the actions the user performs like:
    Click login:
    Open admin Panel:
 */
public class SignUpActionHandler implements ActionListener {
    private static JPasswordField confPassField;
    private static JPasswordField passwordField;
    private static JTextField userText;
    @Override
    public void actionPerformed(ActionEvent event) {

        try {
            HazeApp.displayCreateAccountInterface();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}