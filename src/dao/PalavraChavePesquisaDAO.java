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

/**
 *
 * @author joao
 */
public class PalavraChavePesquisaDAO extends DAO {

    private KeywordSearch palavraChavePesquisa;

    public PalavraChavePesquisaDAO() {
    }

    public PalavraChavePesquisaDAO(KeywordSearch palavraChavePesquisa) {
        this.palavraChavePesquisa = palavraChavePesquisa;
    }

    public ArrayList<KeywordSearch> listarPalavraChavePesquisas(String palavraChave, Student aluno) {
        String sql = "SELECT pcp.* FROM Palavra_Chave AS pc\n"
                + "    INNER JOIN Palavra_Chave_Pesquisa AS pcp ON pc.id = pcp.id_Palavra_Chave\n"
                + "    INNER JOIN Curso_Universidade AS c ON c.id_curso = pcp.id_curso\n"
                + "    INNER JOIN Usuario AS u ON u.id_curso_universidade = c.id\n"
                + "    WHERE pc.nomePalavraChave like ? AND u.id = ?";
        bd.getConnection();
        ArrayList<KeywordSearch> palavras = new ArrayList<>();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, '%' + palavraChave + '%');
            bd.st.setInt(2, aluno.id);
            bd.rs = bd.st.executeQuery();

            while (bd.rs.next()) {
                palavras.add(new KeywordSearch(
                                bd.rs.getInt("id"),
                                bd.rs.getInt("id_Palavra_Chave"),
                                bd.rs.getInt("id_curso"),
                                bd.rs.getInt("id_Pesquisavel"))
                );
            }

            return palavras;
        } catch (SQLException erro) {
            System.err.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
