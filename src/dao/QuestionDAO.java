/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Question;

/**
 *
 * @author joao
 */
public class QuestionDAO extends DAO {

    private Question question;

    public QuestionDAO() {
    }

    public QuestionDAO(Question question) {
        this.question = question;
    }

    public Question find(int id) {
        String sql = "SELECT * FROM Duvida WHERE id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();

            return new Question(bd.rs.getInt("id"),
                    bd.rs.getString("nomeDuvida"),
                    bd.rs.getString("descricaoDuvida"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
