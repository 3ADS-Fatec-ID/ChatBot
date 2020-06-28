/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Progresso;

/**
 *
 * @author joao
 */
public class ProgressoDAO extends DAO {

    private Progresso progresso;

    public ProgressoDAO(Progresso progresso) {
        this.progresso = progresso;
    }

    public ProgressoDAO() {
    }

    public Progresso pegarProgresso(String nome) {
        String sql = "select * from Progresso where nomeProgresso = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, nome);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Progresso(bd.rs.getInt("id"),
                    bd.rs.getString("nomeProgresso"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }
}
