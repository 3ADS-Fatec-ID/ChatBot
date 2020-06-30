/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Pesquisa;

/**
 *
 * @author joao
 */
public class PesquisaDAO extends DAO {

    private Pesquisa pesquisa;

    public PesquisaDAO(Pesquisa pesquisa) {
        this.pesquisa = pesquisa;
    }

    public PesquisaDAO() {
    }

    public boolean criarPesquisa() {
        String sql = "insert into Pesquisa values (?,?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, pesquisa.corpo);
            bd.st.setTimestamp(2, getCurrentTimeStamp());
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setInt(4, pesquisa.idProgresso);
            bd.st.setInt(5, pesquisa.idUsuario);
            bd.st.executeUpdate();
            return true;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public Pesquisa ultimaPesquisa() {
        String sql = "SELECT TOP 1  * FROM Pesquisa WHERE id_Usuario = ? ORDER BY id DESC";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, pesquisa.idUsuario);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Pesquisa(bd.rs.getInt("id"),
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
