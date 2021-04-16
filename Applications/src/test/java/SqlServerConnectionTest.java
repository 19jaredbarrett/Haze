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
        // test order by downloads, descending
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
    void registerUser() throws SQLException {
        User u = new User("testAnothaOne", "sgopikasijpg", 300.0, 1);
        assertTrue(conn.registerUser(u.getUsername(), new char[] {'a','b','c'}));
    }

    @Test
    void loginUser() throws SQLException {
        User u = conn.loginUser("DogeLord", new char[] {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'} );
        assertEquals("DogeLord", u.getUsername());
        assertEquals(u, conn.getCurrentUser());
    }
    @Test
    void buyApp() throws SQLException {
        App ex = new App(1, "DogeGame", "You play as a doge!", 10.00, 50504, 0);
        conn.loginUser("DogeLord", new char[] {'p', 'a', 's', 's', 'w', 'o', 'r', 'd'} );
        assertTrue( conn.buyApp(ex));
        assertEquals(990.0,conn.getCurrentUser().getBalance(), 0.001);
        ex = new App(1, "Bannerlord", "You play as a doge!", 50.00, 50504, 0);
        assertTrue(conn.buyApp(ex));
        assertEquals(940.0, conn.getCurrentUser().getBalance(), 0.001);
        assertFalse(conn.buyApp(null));
        assertEquals(940.0, conn.getCurrentUser().getBalance(), 0.001);
        // price above balance
        ex = new App(1, "expensive", "very", 5000.00, 4, 0);
        assertFalse(conn.buyApp(ex));
        assertEquals(940.0, conn.getCurrentUser().getBalance(), 0.001);
    }

    @Test
    void getUserApps() throws SQLException {
        User u = new User("userTest", new char[] {'t', 't', 't', 't', 't'});
        conn.registerUser("userTest", new char[] {'t', 't', 't', 't', 't'});
        conn.loginUser("userTest", new char[] {'t', 't', 't', 't', 't'} );
        App ex = new App(1, "DogeGame", "You play as a doge!", 10.00, 50504, 0);
        // app 1
        assertTrue(conn.buyApp(ex));
        conn.buyApp(new App(1, "Bannerlord", "You play as a doge!", 20.00, 50504, 0));
        ArrayList<App> userApps = conn.getUserApps();
        assertEquals("DogeGame",userApps.get(0).getAppName());
        assertEquals("Bannerlord",userApps.get(1).getAppName());
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