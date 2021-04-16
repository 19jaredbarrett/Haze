import DbClasses.App;
import DbClasses.ApplicationsTableModel;
import DbClasses.ConnectionProvider;
import DbClasses.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class SqlServerConnection implements ConnectionProvider {
    private static final HikariConfig conf = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection conn;
    private JTable appsTable;
    private User currentUser;
    private ApplicationsTableModel model;
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
     * Get an apps Table based on ORDER_BY_NAME through ORDER_BY_RATING and isAsc
     * In case of collision, order by name descending
     * @return returns an apps table
     * @throws SQLException if the statement screws up
     */
    public JTable getAppsTable(int order, int isAsc) throws SQLException {
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
        model = new ApplicationsTableModel(appsList, order, isAsc == 1);
        // create the tabel and return it
        appsTable = new JTable(model);
        addTableFeatures();
        return appsTable;
    } // end getApps method

    /**
     * OVERLOADING METHOD
     * This gets an apps table based on a searchString
     * @param searchString the string we want to search
     * @return  apps table with apps that contain the substring: searchString
     * @throws SQLException statement fails
     */
    public JTable getAppsTable(String searchString) throws SQLException {
        conn = getConnection();

        // a rectangle of arraylists
        ArrayList<App> appsList = new ArrayList<App>();
        String call = "{call searchApps(?)}";
        try (CallableStatement stmt = conn.prepareCall(call)) {
            // set order and isAsc to true
            stmt.setString(1, searchString);
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
        model = new ApplicationsTableModel(appsList, ApplicationsTableModel.ORDER_BY_NAME, true);
        // create the tabel and return it
        appsTable = new JTable(model);
        addTableFeatures();
        return appsTable;
    } // end getApps method

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
        currentUser = returnUser;
        return returnUser;
    }

    /**
     * This method adds the action listeners and sets the width/height of the table
     * Called by functions getAppsTable(int order, int isAsc) and
     * getAppsTable(String searchString)
     */
    private void addTableFeatures() {
        // set table column dimensions and format numbers to be in the center of the column
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        appsTable.setDefaultRenderer(Integer.class, centerRenderer);
        appsTable.setDefaultRenderer(Double.class, centerRenderer);
        appsTable.getColumnModel().getColumn(0).setPreferredWidth(130);
        appsTable.getColumnModel().getColumn(1).setPreferredWidth(40);
        appsTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        // set font and size for better table readability
        appsTable.setRowHeight(24);
        appsTable.setFont(new Font("Helvetica", Font.PLAIN, 12));

        // add mouse listener to the header of this table to completely get a new table
        appsTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = appsTable.columnAtPoint(e.getPoint());
                // set appsTable to the new table model
                HazeApp.panel.remove(HazeApp.scrollPane);
                try {
                    JTable appsTable = getAppsTable(col, model.getIsAsc());
                    HazeApp.scrollPane = new JScrollPane(appsTable);
                    HazeApp.scrollPane.setBounds(10, 80, 350, 450 );
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
                    // format number of downloads with commas
                    DecimalFormat formatCommas = new DecimalFormat("#,###,###,###");
                    textAreaString += formatCommas.format(clickedApp.getNumDownloads()) + "\n\nDoge's review (1-10): ";
                    int rating = clickedApp.getRating();
                    textAreaString += rating;
                    // Special ratings, display whether it is good or bad!
                    if(rating == 10)
                        HazeApp.displaySuccess("GOD-LIKE, Doge's top 3 games ＜(。_。)＞", true);
                    else if (rating == 0)
                        HazeApp.displaySuccess("Gambling for kids! Beware...", false);
                    else if (rating == -1)
                        HazeApp.displaySuccess("Battle Royales are trash (ಠ_ಠ)", false);
                    else if (rating == 1)
                        HazeApp.displaySuccess("Huge Disappointment for Doge \uD83D\uDE14", false);
                    else  HazeApp.displaySuccess("", false);
                    HazeApp.appDesc.setText(textAreaString);
                    HazeApp.appDesc.repaint();
                }
            }
        });
        // create scroll pane and set the bounds
    }

    /**
     * This method attempts to buy an app.
     * @param boughtApp
     */
    public boolean buyApp(App boughtApp) {

        return false;
    }

    /**
     * This method gets the current user's apps
     * @return
     */
    public ArrayList<App> getUserApps() {

        return new ArrayList<App>();
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
