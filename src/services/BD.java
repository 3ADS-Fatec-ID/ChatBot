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
	private final String URL = "jdbc:sqlserver://localhost:1433;databasename="+BANCO+";integratedSecurity=true&characterEncoding=utf-8";
	
	/**
	 * Realiza a conex�o ao banco de dados
	 * @return - true em caso de sucesso, ou false caso contr�rio
	 */
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL);
			//System.out.println("Conectou!");
	
		}
		catch(SQLException erro) {
			System.out.println("Falha na conex�o ao banco! " + erro.toString());
		}
		catch(ClassNotFoundException erro) {
			System.out.println("Driver n�o encontrado!");
		}
		return true;
	}
	
	/**
	 * Encerra a conex�o (con, st, rs)
	 */
	public void close() {
		try {
			if (rs!=null) rs.close();
			if (st!=null) st.close();
			if (con!=null) {
				con.close();
				//System.out.println("Desconectou!");
			}
		}
		catch(SQLException e) {
		}
	}
	
	
//	public static void main(String[] args) {
//		BD bd = new BD();
//		bd.getConnection();
		// executo a a��o
//		bd.close();
//	}
	
}
