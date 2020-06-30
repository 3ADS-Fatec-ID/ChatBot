/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.University;

/**
 *
 * @author joao
 */
public class UniversidadeDAO extends DAO {

    private University universidade;

    public UniversidadeDAO() {
    }

    public UniversidadeDAO(University universidade) {
        this.universidade = universidade;
    }

    public University pegarUniversidade() {
        String sql = "select * from Universidade where nomeUniversidade like ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, "%" + universidade.name + "%");
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new University(bd.rs.getInt("id"),
                    bd.rs.getInt("id_endereco"),
                    bd.rs.getString("nomeUniversidade"),
                    bd.rs.getString("descricaoUniversidade")
            );
        } catch (SQLException erro) {
            System.out.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
