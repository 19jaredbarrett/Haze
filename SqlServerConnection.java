

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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



    public String[] getApps() throws SQLException {
        conn = getConnection();
        ArrayList<String> appNames = new ArrayList<>();
        String call = "SELECT Apps.appName FROM Apps";
        try(CallableStatement stmt = conn.prepareCall(call)) {
            boolean hasResult = stmt.execute();
            if(hasResult) {
                ResultSet rs = stmt.getResultSet();
                while(rs.next()) {
                    appNames.add(rs.getString(1));
                }
            }
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        String[] appArr = new String[appNames.size()];
        appArr = appNames.toArray(appArr);
        return appArr;
    }

}
