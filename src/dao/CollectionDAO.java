/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Collection;

/**
 *
 * @author joao
 */
public class CollectionDAO extends DAO {

    private Collection collection;

    public CollectionDAO() {
    }

    public CollectionDAO(Collection collection) {
        this.collection = collection;
    }

    public Collection find(int id) {
        String sql = "SELECT * FROM Acervo WHERE id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Collection(bd.rs.getInt("id"),
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
