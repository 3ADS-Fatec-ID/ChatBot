/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Searchable;

/**
 *
 * @author joao
 */
public class SearchableDAO extends DAO {

    private Searchable searchable;

    public SearchableDAO() {
    }

    public SearchableDAO(Searchable searchable) {
        this.searchable = searchable;
    }

    public Searchable find(int id) {
        String sql = "SELECT * FROM Pesquisavel WHERE id = ?";
        bd.getConnection();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Searchable(bd.rs.getInt("id"),
                    bd.rs.getInt("id_Acervo"),
                    bd.rs.getInt("id_Duvida"));
        } catch (SQLException erro) {
            System.err.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
