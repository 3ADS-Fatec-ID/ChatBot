/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.PalavraChave;

/**
 *
 * @author joao
 */
public class PalavraChaveDAO extends DAO {

    private PalavraChave palavraChave;

    public PalavraChaveDAO() {
    }

    public PalavraChaveDAO(PalavraChave palavraChave) {
        this.palavraChave = palavraChave;
    }

    public PalavraChave pesquisarPalavraChave(int palavraChave) {
        String sql = "SELECT * FROM Palavra_Chave WHERE id LIKE ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, palavraChave);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new PalavraChave(bd.rs.getInt("id"), bd.rs.getString("nomePalavraChave"));
        } catch (SQLException erro) {
            System.err.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
