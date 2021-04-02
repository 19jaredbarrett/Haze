

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.swing.*;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class SqlServerConnection implements ConnectionProvider{
    private static final HikariConfig conf = new HikariConfig();
    private static HikariDataSource ds;
    private static Connection conn;
    //HazePa$$word123
    public SqlServerConnection ()  {
        conf.setJdbcUrl("jdbc:sqlserver://localhost\\\\SQLEXPRESS:1433;databaseName=Apps201;");
        conf.setUsername("cse201Login");
        conf.setPassword("HazePa$$word123");
        conf.addDataSourceProperty( "cachePrepStmts" , "true" );
        conf.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        conf.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        conf.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds = new HikariDataSource(conf);


    }

    @Override
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }


    /**
     * 
     * @return
     * @throws SQLException
     */
    public JTable getAppsTable() throws SQLException {
        conn = getConnection();

        // a rectangle of arraylists
        ArrayList<App> appsList = new ArrayList<App>();
        String call = "{call getApps(?, ?)}";
        try(CallableStatement stmt = conn.prepareCall(call)) {
            // set order and isAsc to true
            stmt.setInt(1, 1);
            stmt.setInt(2, 1);
            boolean hasResult = stmt.execute();
            if(hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String desc = rs.getString(3);
                    double price = rs.getDouble(4);
                    int numDownloads = rs.getInt(5);
                    App currInfo = new App(id, name, desc, price, numDownloads);
                    appsList.add(currInfo);
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       ApplicationsTableModel model = new ApplicationsTableModel(appsList);
        // create the tabel and return it
        JTable appsTable = new JTable(model);
        return appsTable;
    }
    public String[] getApps() throws SQLException {
        conn = getConnection();
        ArrayList<String> appsList = new ArrayList<>();
        String call = "SELECT Apps.appName FROM Apps";
        try(CallableStatement stmt = conn.prepareCall(call)) {
            boolean hasResult = stmt.execute();
            if(hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()) {
                    appsList.add(rs.getString(1));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] appArr = new String[appsList.size()];
        appArr = appsList.toArray(appArr);
        return appArr;
    }

}
