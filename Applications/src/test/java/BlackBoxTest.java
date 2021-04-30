import DbClasses.App;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BlackBoxTest {

    public BlackBoxTest() throws SQLException, FileNotFoundException {
        //final InputStream original = System.in;
        //final FileInputStream fips = new FileInputStream(new File("[path_to_file]"));
        //System.setIn(fips);
        HazeApp.main(null);
    }
    /*
        Black box: logs in the user
     */
    @Test
    void loginTest() {
        HazeApp.userText.setText("Dogelord");
        HazeApp.pwText.setText("password");
        HazeApp.signInButton.doClick();
        // we have to set it equal to DogeLord, since in the database the name is stored as DogeLord
        assertEquals(HazeApp.conn.getCurrentUser().getUsername(), "DogeLord");
        // the reason it is equal to blank_password is because we do not want to store the passwords
        // of the users in java
        assertEquals(HazeApp.conn.getCurrentUser().getPassword(), "blank_passsword" );
        assertEquals(HazeApp.conn.getCurrentUser().getAccessLevel(), "Admin");
        // test logging in when the user does not exist
        MouseEvent clickedSignOut= new MouseEvent(HazeApp.signOutButton,MouseEvent.MOUSE_CLICKED, 0, 0, 505, 55, 1, false);
        HazeApp.signOutButton.dispatchEvent(clickedSignOut);
        // assert user is now null since we have signed out
        assertNull(HazeApp.conn.getCurrentUser());
        HazeApp.userText.setText("Dogesef");
        HazeApp.pwText.setText("passwordsdf");
        HazeApp.signInButton.doClick();
        assertNull(HazeApp.conn.getCurrentUser());

    }
    @Test
    void registerUserTest() {
        // click signupbutton
        MouseEvent clickedSignUp = new MouseEvent(HazeApp.signUpButton,MouseEvent.MOUSE_CLICKED, 0, 0, 505, 55, 1, false);
        HazeApp.signUpButton.dispatchEvent(clickedSignUp);
        HazeApp.userText.setText("newUsername");
        HazeApp.pwText.setText("password");
        HazeApp.confPwText.setText("password");
        MouseEvent clickedRegister = new MouseEvent(HazeApp.registerUser,MouseEvent.MOUSE_CLICKED, 0, 0, 505, 25, 1, false);
        HazeApp.registerUser.dispatchEvent(clickedRegister);
        // now that we have registered, we should be able to login with this new user!
        HazeApp.userText.setText("newUsername");
        HazeApp.pwText.setText("password");
        HazeApp.signInButton.doClick();
        assertEquals("newUsername", HazeApp.conn.getCurrentUser().getUsername() );
        assertEquals("blank_passsword", HazeApp.conn.getCurrentUser().getPassword()  );
        // sign out now
        MouseEvent clickedSignOut= new MouseEvent(HazeApp.signOutButton,MouseEvent.MOUSE_CLICKED, 0, 0, 505, 55, 1, false);
        HazeApp.signOutButton.dispatchEvent(clickedSignOut);
        // assert user is now null since we have signed out
        // for when the user tries to enter a username less than 5, we should not be able to login with this username
        HazeApp.signUpButton.dispatchEvent(clickedSignUp);
        HazeApp.userText.setText("user");
        HazeApp.pwText.setText("password");
        HazeApp.confPwText.setText("password");
        HazeApp.registerUser.dispatchEvent(clickedRegister);
        HazeApp.userText.setText("user");
        HazeApp.pwText.setText("password");
        HazeApp.signInButton.doClick();
        assertNull(HazeApp.conn.getCurrentUser());
    }
    /*
        Black box testing for searching an application
     */
    @Test
    void searchAppTest() {
        HazeApp.searchText.setText("BannerLord");
        MouseEvent clickedSearch = new MouseEvent(HazeApp.searchBtn,MouseEvent.MOUSE_CLICKED, 0, 0, 505, 55, 1, false);
        HazeApp.searchBtn.dispatchEvent(clickedSearch);
        assertEquals("Mount & Blade II: Bannerlord",HazeApp.appsTable.getValueAt(0, 0));

        // search for hades
        HazeApp.searchText.setText("hades");
        HazeApp.searchBtn.dispatchEvent(clickedSearch);
        assertEquals("Hades", HazeApp.appsTable.getValueAt(0, 0));
        // search for among us
        HazeApp.searchText.setText("am");
        HazeApp.searchBtn.dispatchEvent(clickedSearch);
        assertEquals("Among Us", HazeApp.appsTable.getValueAt(0, 0));
        // search for app that doesn't exist
        HazeApp.searchText.setText("BanngsagdasesagrLorsagasgd");
        HazeApp.searchBtn.dispatchEvent(clickedSearch);
        // 0 number of rows
        assertEquals(0, HazeApp.appsTable.getRowCount());
    }

}
