/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Acervo;

/**
 *
 * @author joao
 */
public class AcervoDAO extends DAO {

    private Acervo acervo;

    public AcervoDAO() {
    }

    public AcervoDAO(Acervo acervo) {
        this.acervo = acervo;
    }

    public Acervo pesquisarAcervo() {
        String sql = "SELECT * FROM Acervo WHERE id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, acervo.id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Acervo(bd.rs.getInt("id"),
                    bd.rs.getString("autor"),
                    bd.rs.getString("tema"),
                    bd.rs.getString("orientador"));
        } catch (SQLException erro) {
            System.err.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
