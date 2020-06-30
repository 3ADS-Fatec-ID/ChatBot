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
public class ProgressDAO extends DAO {

    private Progress progress;

    public ProgressDAO(Progress progress) {
        this.progress = progress;
    }

    public ProgressDAO() {
    }

    public Progress find(String name) {
        String sql = "select * from Progresso where nomeProgresso = ?";
        bd.getConnection();
        
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, name);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            
            return new Progress(bd.rs.getInt("id"),
                    bd.rs.getString("nomeProgresso"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }

    public Progress find(int id) {
        String sql = "select * from Progresso where id = ?";
        bd.getConnection();
        
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            
            return new Progress(bd.rs.getInt("id"),
                    bd.rs.getString("nomeProgresso"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }

    public Progress pegarProgresso(Student aluno) {
        SearchDAO pesquisaDAO = new SearchDAO(new Search(aluno.id));
        Search pesquisa = pesquisaDAO.last();

        if (pesquisa == null) {
            return null;
        } else {
            ProgressDAO progressoDAO = new ProgressDAO();
            Progress progresso = progressoDAO.find(pesquisa.progressId);
            System.out.println("Progresso está em: " + progresso.name);
            return progresso;
        }

    }
}
