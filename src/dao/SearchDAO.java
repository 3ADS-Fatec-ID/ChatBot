/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Keyword;
import model.Search;

/**
 *
 * @author joao
 */
public class SearchDAO extends DAO {

    private Search search;

    public SearchDAO(Search search) {
        this.search = search;
    }

    public SearchDAO() {
    }

    public boolean add() {
        String sql = "insert into Pesquisa values (?,?,?,?,?)";
        bd.getConnection();

        try {
            bd.st = bd.con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            bd.st.setString(1, search.body);
            bd.st.setTimestamp(2, getCurrentTimeStamp());
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setInt(4, search.progressId);
            bd.st.setInt(5, search.userId);
            bd.st.executeUpdate();
            
            try (ResultSet generatedKeys = bd.st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    search.id = generatedKeys.getInt(1);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }
	    
    public boolean addHistory(int idKeyword,Search search) {
        String sql = "insert into Pesquisa_Historico values (?,?,?)";
        bd.getConnection();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setTimestamp(1, getCurrentTimeStamp());
            bd.st.setInt(2, idKeyword);
            bd.st.setInt(3, search.id);
            bd.st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public Search last() {
        String sql = "SELECT TOP 1  * FROM Pesquisa WHERE id_Usuario = ? ORDER BY id DESC";
        bd.getConnection();
        
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, search.userId);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Search(bd.rs.getInt("id"),
                    bd.rs.getInt("id_Progresso"),
                    bd.rs.getInt("id_Usuario"),
                    bd.rs.getString("corpo"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
