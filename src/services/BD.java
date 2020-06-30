package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BD {

    //interfaces
    public Connection con = null;
    public PreparedStatement st = null;
    public ResultSet rs = null;

    private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private final String BANCO = "BOT";
    private final String URL = "jdbc:sqlserver://localhost:1433;databasename=" + BANCO + ";integratedSecurity=true";

    /**
     * Realiza a conexão ao banco de dados
     *
     * @return - true em caso de sucesso, ou false caso contrário
     */
    public boolean getConnection() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println( e.toString());
        }
        return true;
    }

    /**
     * Encerra a conexão (con, st, rs)
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
