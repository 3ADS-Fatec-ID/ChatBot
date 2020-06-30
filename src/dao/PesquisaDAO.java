/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Search;

/**
 *
 * @author joao
 */
public class PesquisaDAO extends DAO {

    private Search pesquisa;

    public PesquisaDAO(Search pesquisa) {
        this.pesquisa = pesquisa;
    }

    public PesquisaDAO() {
    }

    public boolean criarPesquisa() {
        String sql = "insert into Pesquisa values (?,?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, pesquisa.body);
            bd.st.setTimestamp(2, getCurrentTimeStamp());
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setInt(4, pesquisa.progressId);
            bd.st.setInt(5, pesquisa.userId);
            bd.st.executeUpdate();
            return true;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public Search ultimaPesquisa() {
        String sql = "SELECT TOP 1  * FROM Pesquisa WHERE id_Usuario = ? ORDER BY id DESC";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, pesquisa.userId);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Search(bd.rs.getInt("id"),
                    bd.rs.getInt("id_Progresso"),
                    bd.rs.getInt("id_Usuario"),
                    bd.rs.getString("corpo"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }
}
