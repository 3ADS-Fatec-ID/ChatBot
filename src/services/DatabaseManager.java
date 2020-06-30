package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {

    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;

    /**
     * Driver package
     */
    private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    /**
     * The name of the database
     */
    private final String DATABASE = "BOT";
    /**
     * 1433 is the default SQL Server port 
     */
    private final String URL = "jdbc:sqlserver://localhost:1433;databasename=" + DATABASE + ";integratedSecurity=true";

    /**
     * Open the connection to the database
     *
     * @return
     */
    public boolean getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.toString());
        }
        return true;
    }

    /**
     * Close the connection.
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }
}
