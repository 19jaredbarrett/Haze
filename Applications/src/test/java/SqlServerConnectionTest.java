import DbClasses.App;
import DbClasses.ApplicationsTableModel;
import DbClasses.User;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SqlServerConnectionTest {
    SqlServerConnection conn = new SqlServerConnection();

    @Test
    void getAppsTable() throws SQLException {
        JTable appsTable =  conn.getAppsTable(ApplicationsTableModel.ORDER_BY_NAME,1 );
        // assert there is  38 rows
        assertEquals(38,appsTable.getRowCount());
        assertEquals(4, appsTable.getColumnCount());
        // the least alphabetically will be displayed: Among Us
        assertEquals("Among Us",appsTable.getValueAt(0, 0));
        assertEquals(3.99,(double)appsTable.getValueAt(0, 1), 0.001);
        assertEquals(25639874,(int)appsTable.getValueAt(0, 2));
        assertEquals(6,(int)appsTable.getValueAt(0, 3));
        // test order by downloads, descending:
        appsTable =  conn.getAppsTable(ApplicationsTableModel.ORDER_BY_DOWNLOADS,0 );
        assertEquals("Clash Of Clans",appsTable.getValueAt(0, 0));
        assertEquals(0.0,(double)appsTable.getValueAt(0, 1), 0.001);
        assertEquals(532012000,(int)appsTable.getValueAt(0, 2));
        assertEquals(0,(int)appsTable.getValueAt(0, 3));
        // test order by Price, descending
        appsTable =  conn.getAppsTable(ApplicationsTableModel.ORDER_BY_PRICE,0 );
        assertEquals("Civilization VI",appsTable.getValueAt(0, 0));
        assertEquals(60.0, (double)appsTable.getValueAt(0, 1), 0.001);
        assertEquals(8153214,(int)appsTable.getValueAt(0, 2));
        assertEquals(1,(int)appsTable.getValueAt(0, 3));
        /* parse through the table
        for(int i = 0; i < appsTable.getRowCount(); i++) {
            for (int j = 0; j < appsTable.getColumnCount(); j++) {
                System.out.print(appsTable.getValueAt(i, j) + " ");
            }
            System.out.println();
        }*/
    }
    // TODO call conn.getAppsTable(String inputString)
    @Test
    void searchApps() throws SQLException {
        // mount & blade bannerlord and mount & blade warband
        JTable appsTable =  conn.getAppsTable("mount");
        // assert there is  38 rows
        assertEquals(2,appsTable.getRowCount());
        assertEquals(4, appsTable.getColumnCount());
        // bannerlord
        assertEquals("Mount & Blade II: Bannerlord",appsTable.getValueAt(0, 0));
        assertEquals(49.99,(double)appsTable.getValueAt(0, 1), 0.001);
        assertEquals(992219,(int)appsTable.getValueAt(0, 2));
        assertEquals(7,(int)appsTable.getValueAt(0, 3));
    }



    @Test
    void loginUser() throws SQLException {
        User u = conn.loginUser("DogeLord", new char[] {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'} );
        assertEquals("DogeLord", u.getUsername());
        assertEquals(u, conn.getCurrentUser());
    }


    @Test
    void getApps() {
        App dota = new App(2, "Dota2", "A famous 5V5 Moba Game.", 0, 5000000, 10);
        conn.setCurrentApp(dota);
        assertEquals(dota, conn.getCurrentApp());

        App darksouls = new App(1, "DarkSouls3", "A third-person fighting game, which is very hard", 49, 1000000, 10);
        conn.setCurrentApp(darksouls);
        assertEquals(darksouls, conn.getCurrentApp());
    }
    /*
        Gray box test 1: the user attempts to buy an application
        We check to see if the app is actually bought
     */
    @Test
    void buyApp() throws SQLException {

        App ex = new App(1, "DogeGame", "You play as a doge!", 10.00, 50504, 0);
        conn.setCurrentApp(ex);
        conn.loginUser("DogeLord", new char[] {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'} );
        assertTrue(conn.buyApp("this game's pretty cool :]"));
        assertEquals(5000,conn.getCurrentUser().getBalance(), 0.001);
        ex = new App(20, "Bannerlord", "You play as a doge!", 50.00, 50504, 0);
        assertTrue(conn.buyApp("ok game"));
        conn.setCurrentApp(ex);
        assertEquals(5000, conn.getCurrentUser().getBalance(), 0.001);
        assertFalse(conn.buyApp(null));
        assertEquals(5000, conn.getCurrentUser().getBalance(), 0.001);
        // price above balance
        ex = new App(30, "expensive", "very", 50000.00, 4, 0);
        conn.setCurrentApp(ex);
        assertFalse(conn.buyApp("ok game"));
        assertEquals(5000, conn.getCurrentUser().getBalance(), 0.001);
    }
    /*
        Gray box 2: User registers and logs in, then the user attempts to
        get the userapps table for the app: Counter Strike Global Offensive
     */
    @Test
    void getUserApps() throws SQLException {
        conn.registerUser("userTest", new char[] {'t', 't', 't', 't', 't'});
        conn.loginUser("userTest", new char[] {'t', 't', 't', 't', 't'} );
        // app 1
        //assertTrue(conn.buyApp(ex));
        conn.setCurrentApp(new App(1,"Counter-Strike: Global Offensive",
                "Counter-Strike: Global Offensive is a multiplayer first-person shooter developed by" +
                "Valve and Hidden Path Entertainment. It is the fourth game in the Counter-Strike series.", 0.00, 99999999, 10));
        //conn.buyApp(new App(1, "Bannerlord", "You play as a doge!", 20.00, 50504, 0));
        JTable userAppsTab = conn.getUserAppsTable();
        // dogelord's comment
        assertEquals("DogeLord",userAppsTab.getValueAt(0, 0));
        assertEquals("this game's pretty cool :]", userAppsTab.getValueAt(0, 1));
        // usertest's comment
        assertEquals("userTest",userAppsTab.getValueAt(1, 0));
        assertEquals("this game is amazing!", userAppsTab.getValueAt(1, 1));
        // testuser's comment
        assertEquals("testuser",userAppsTab.getValueAt(2, 0));
        assertEquals("great game",userAppsTab.getValueAt(2, 1));
    }
    /**
     * Gray box 3:
     * The user attempts to register different users
     * The first user should work, th second, third, and fourth should not
     * because the username is empty or the password is empty.
     * @throws SQLException well you know the drill.
     */
    @Test
    void registerUser() throws SQLException {
        User u = new User("testRegisterUser1", "sgopikasijpg", 300.0, 1);
        assertTrue(conn.registerUser(u.getUsername(), new char[] {'a','b','c'}));
        // these two examples of user is for testing the result if username or passwords are null
        User blankUsername = new User("","test",0,1);
        assertFalse(conn.registerUser(blankUsername.getUsername(), new char[] {'t','e','s','t'}));

        User blankPassword = new User("testUsername","",0,1);
        assertFalse(conn.registerUser(blankPassword.getUsername(), new char[] {}));

        // this example of user is for testing the result if username and passwords are all null
        User allblank = new User("","",0,1);
        assertFalse(conn.registerUser(allblank.getUsername(), new char[] {' '}));
    }
    @Test
    void currentApp() {
        // tests getter/setter for currentApp
        App dota = new App(2,"Dota2","A famous 5V5 Moba Game.",0,5000000,10);
        conn.setCurrentApp(dota);
        assertEquals(dota,conn.getCurrentApp());
        App darksouls = new App(1,"DarkSouls3","A third-person fighting game, which is very hard",49,1000000,10);
        conn.setCurrentApp(darksouls);
        assertEquals(darksouls,conn.getCurrentApp());

    }

}