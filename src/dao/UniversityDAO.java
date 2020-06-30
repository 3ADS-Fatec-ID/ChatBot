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
public class UniversityDAO extends DAO {

    private University university;

    public UniversityDAO() {
    }

    public UniversityDAO(University university) {
        this.university = university;
    }

    public University find() {
        String sql = "select * from Universidade where nomeUniversidade like ?";
        bd.getConnection();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, "%" + university.name + "%");
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new University(bd.rs.getInt("id"),
                    bd.rs.getInt("id_endereco"),
                    bd.rs.getString("nomeUniversidade"),
                    bd.rs.getString("descricaoUniversidade")
            );
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
