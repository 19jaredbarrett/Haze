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
public class SignInActionHandler  implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        String username = HazeApp.userText.getText();
        char[] password = HazeApp.pwText.getPassword();
        if(username.length() == 0 || password.length == 0) {
            HazeApp.displaySuccess("Provide username and password (●__●)", false);
            return;
        }
        User u = null;
        try {
            u = HazeApp.conn.loginUser(username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        if( u == null) {
            HazeApp.displaySuccess("Incorrect Username or Password (╯°□°）╯", false);
        } else {
            HazeApp.conn.setCurrentUser(u);
            HazeApp.updateUserInterfaceLoggedIn();
            HazeApp.displaySuccess("Success (☞ ͡° ͜ʖ ͡°)☞ (☞ ͡° ͜ʖ ͡°)☞ (☞ ͡° ͜ʖ ͡°)☞", true);
        }

    }
}