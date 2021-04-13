import DbClasses.User;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SqlServerConnectionTest {
    SqlServerConnection conn = new SqlServerConnection();

    @Test
    void getAppsTable() throws SQLException {
        conn.getConnection();
        JTable appsTable =  conn.getAppsTable(1,1 );
        for(int i = 0; i < appsTable.getRowCount(); i++) {
            for (int j = 0; j < appsTable.getColumnCount(); j++) {
                System.out.print(appsTable.getValueAt(i, j) + " ");
            }
            System.out.println();
        }
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