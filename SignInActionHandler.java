import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
    This class will handle all of the actions the user performs like:
    Click login:
    Open admin Panel:
 */
public class SignInActionHandler extends HazeApp implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        String username = HazeApp.userText.getText();
        HazeApp.login(username);
    }
}