import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
    This class will handle all of the actions the user performs like:
    Click login:
    Open admin Panel:
 */
public class SignUpActionHandler extends HazeApp implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent event) {
        HazeApp.signUp();
    }
}