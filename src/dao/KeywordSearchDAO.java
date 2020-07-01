/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import model.Student;
import model.KeywordSearch;
import model.Search;

/**
 *
 * @author joao
 */
public class KeywordSearchDAO extends DAO {

    private KeywordSearch keywordSearch;

    public KeywordSearchDAO() {
    }

    public KeywordSearchDAO(KeywordSearch keywordSearch) {
        this.keywordSearch = keywordSearch;
    }

    public ArrayList<KeywordSearch> list(String keyword, Student student, Search search) {
        String sql = "SELECT pcp.* FROM Palavra_Chave AS pc\n"
                + "    INNER JOIN Palavra_Chave_Pesquisa AS pcp ON pc.id = pcp.id_Palavra_Chave\n"
                + "    INNER JOIN Curso_Universidade AS c ON c.id_curso = pcp.id_curso\n"
                + "    INNER JOIN Usuario AS u ON u.id_curso_universidade = c.id\n"
                + "    WHERE pc.nomePalavraChave like ? AND u.id = ?";
        bd.getConnection();
        ArrayList<KeywordSearch> keywordSearches = new ArrayList<>();

        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, '%' + keyword + '%');
            bd.st.setInt(2, student.id);
            bd.rs = bd.st.executeQuery();
            int id_Palavra_Chave;
            while (bd.rs.next()) {
            	id_Palavra_Chave = bd.rs.getInt("id_Palavra_Chave");
            	SearchDAO searchDAO = new SearchDAO();
            	searchDAO.addHistory(id_Palavra_Chave, search);
            	keywordSearches.add(new KeywordSearch(
            			bd.rs.getInt("id"),
            			id_Palavra_Chave,
                        bd.rs.getInt("id_curso"),
                        bd.rs.getInt("id_Pesquisavel"))
                );
            }

            return keywordSearches;
        } catch (SQLException erro) {
            System.err.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
