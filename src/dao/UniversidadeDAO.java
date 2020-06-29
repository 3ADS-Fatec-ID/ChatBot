/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Universidade;

/**
 *
 * @author joao
 */
public class UniversidadeDAO extends DAO {

    private Universidade universidade;

    public UniversidadeDAO() {
    }

    public UniversidadeDAO(Universidade universidade) {
        this.universidade = universidade;
    }

    public Universidade pegarUniversidade() {
        String sql = "select * from Universidade where nomeUniversidade like '%?%'";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, universidade.nomeUniversidade);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Universidade(bd.rs.getInt("id"),
                    bd.rs.getInt("id_endereco"),
                    bd.rs.getString("nomeUniversidade"),
                    bd.rs.getString("descricaoUniversidade")
            );
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }
}
