/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;

import model.Student;
import model.Search;
import model.Progress;

/**
 *
 * @author joao
 */
public class ProgressoDAO extends DAO {

    private Progress progresso;

    public ProgressoDAO(Progress progresso) {
        this.progresso = progresso;
    }

    public ProgressoDAO() {
    }

    public Progress pegarProgresso(String nome) {
        String sql = "select * from Progresso where nomeProgresso = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, nome);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Progress(bd.rs.getInt("id"),
                    bd.rs.getString("nomeProgresso"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }

    public Progress pegarProgresso(int id) {
        String sql = "select * from Progresso where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Progress(bd.rs.getInt("id"),
                    bd.rs.getString("nomeProgresso"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }

    public Progress pegarProgresso(Student aluno) {
        PesquisaDAO pesquisaDAO = new PesquisaDAO(new Search(aluno.id));
        Search pesquisa = pesquisaDAO.ultimaPesquisa();

        if (pesquisa == null) {
            return null;
        } else {
            ProgressoDAO progressoDAO = new ProgressoDAO();
            Progress progresso = progressoDAO.pegarProgresso(pesquisa.progressId);
            System.out.println("Progresso está em: " + progresso.name);
            return progresso;
        }

    }
}
