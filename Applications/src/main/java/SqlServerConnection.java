import DbClasses.App;
import DbClasses.ApplicationsTableModel;
import DbClasses.ConnectionProvider;
import DbClasses.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlServerConnection implements ConnectionProvider {
    private static final HikariConfig conf = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection conn;
    private User currentUser;
    private App currentApp;

    //HazePa$$word123
    public SqlServerConnection() {
        conf.setJdbcUrl("jdbc:sqlserver://localhost\\\\SQLEXPRESS:1433;databaseName=Apps201;");
        conf.setUsername("cse201Login");
        conf.setPassword("HazePa$$word123");
        conf.addDataSourceProperty("cachePrepStmts", "true");
        conf.addDataSourceProperty("prepStmtCacheSize", "250");
        conf.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        conf.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds = new HikariDataSource(conf);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /**
     * @return returns an apps table
     * @throws SQLException if the statement screws up
     */
    public JScrollPane getAppsPane(int order, int isAsc) throws SQLException {
        conn = getConnection();

        // a rectangle of arraylists
        ArrayList<App> appsList = new ArrayList<App>();
        String call = "{call getApps(?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(call)) {
            // set order and isAsc to true
            stmt.setInt(1, order);
            stmt.setInt(2, isAsc);
            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String desc = rs.getString(3);
                    double price = rs.getDouble(4);
                    int numDownloads = rs.getInt(5);
                    int rating = rs.getInt(6);
                    currentApp = new App(id, name, desc, price, numDownloads, rating);
                    appsList.add(currentApp);
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        ApplicationsTableModel model = new ApplicationsTableModel(appsList, order-1, isAsc == 1);
        // create the tabel and return it
        JTable appsTable = new JTable(model);
        // set table column dimensions and format numbers to be in the center of the column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        appsTable.setDefaultRenderer(Integer.class, centerRenderer);
        appsTable.setDefaultRenderer(Double.class, centerRenderer);
        appsTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        appsTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        appsTable.getColumnModel().getColumn(3).setPreferredWidth(40);

        // add mouse listener to the header of this table to completely get a new table
        appsTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = appsTable.columnAtPoint(e.getPoint());
                // set appsTable to the new table model
                HazeApp.panel.remove(HazeApp.scrollPane);

                try {
                    HazeApp.scrollPane = getAppsPane(col+1, model.getIsAsc());
                    HazeApp.panel.add(HazeApp.scrollPane);
                    HazeApp.panel.repaint();
                    HazeApp.panel.invalidate();

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

        });
        // add mouse listener for when the user clicks a cell
        appsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int row = appsTable.rowAtPoint(evt.getPoint());
                int col = appsTable.columnAtPoint(evt.getPoint());
                if (row >= 0 && col >= 0) {
                    // handle cell click
                    // get cell 1: the app name
                    // String appName = appsTable.get
                    String textAreaString = "App Id: ";
                    App clickedApp = model.getApp(row);
                    textAreaString += clickedApp.getId() + "\n\nName: ";
                    textAreaString += clickedApp.getAppName() + "\n\nDescription: ";
                    textAreaString += clickedApp.getDescription() + "\n\nPrice: ";
                    textAreaString += clickedApp.getPrice() + "\n\nNumDownloads: ";
                    textAreaString += clickedApp.getNumDownloads() + "\n\nHaze Rating (1-10): ";
                    textAreaString += clickedApp.getRating();
                    HazeApp.appDesc.setText(textAreaString);
                }
            }
        });
        // create scroll pane and set the bounds
        JScrollPane scrollPane= new JScrollPane(appsTable);
        scrollPane.setBounds(10, 80, 350, 450 );
        return scrollPane;
    } // end getScrollPane method

    /**
     * Registers a user into the system if it exists
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean registerUser(String username, char[] pass) throws SQLException {
        conn = getConnection();
        User u = new User(username, pass);
        boolean isSuccess = false;
        String call = "{call registerUser(?, ?, ?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(call)) {
            // set order and isAsc to true
            stmt.setString(1, u.getUsername());
            stmt.setString(2, String.valueOf(pass));
            stmt.setFloat(3, (float) u.getBalance());
            stmt.setInt(4, u.getAccessLevelInt());
            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                if (rs.next()) {
                    isSuccess = true;
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return isSuccess;
    }

    public User loginUser(String username, char[] pass) throws SQLException {
        // return null if blank objects
        if (username.isEmpty() || pass.length == 0)
            return null;
        conn = getConnection();
        User u = new User(username, pass);
        User returnUser = null;
        String call = "{call loginUser(?, ?)}";
        try (CallableStatement stmt = conn.prepareCall(call)) {
            stmt.setString(1, u.getUsername());
            stmt.setString(2, String.valueOf(pass));
            boolean hasResult = stmt.execute();
            if (hasResult) {
                ResultSet rs = stmt.getResultSet();
                if (rs.next()) {
                    returnUser = new User(rs.getString(2),
                            "blank_passsword",
                            rs.getDouble(4),
                            rs.getInt(5));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public App getCurrentApp() {
        return currentApp;
    }

}
