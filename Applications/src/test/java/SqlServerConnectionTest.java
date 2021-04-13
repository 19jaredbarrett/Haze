import DbClasses.ApplicationsTableModel;
import DbClasses.User;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;

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

    }

    @Test
    void registerUser() throws SQLException {
        User u = new User("testAnothaOne", "sgopikasijpg", 300.0, 1);
        assertTrue(conn.registerUser(u.getUsername(), new char[] {'a','b','c'}));
    }

    @Test
    void loginUser() throws SQLException {
        User u = conn.loginUser("DogeLord", new char[] {'T','h','i','s','1','s','M','y','R','e','a','l','P','a','$','$','w','o','r','d'} );
        assertEquals("DogeLord", u.getUsername());
    }

    @Test
    void getApps() {
    }
}