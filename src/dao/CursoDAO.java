/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Curso;

/**
 *
 * @author joao
 */
public class CursoDAO extends DAO {

    private Curso curso;

    public CursoDAO() {
    }

    public CursoDAO(Curso curso) {
        this.curso = curso;
    }

    public Curso pegarCurso(int idUniversidade) {
        String sql = "SELECT *, c_u.id as Id_Curso_Universidade FROM Curso c, Curso_Universidade c_u WHERE c.id = c_u.id_curso AND c_u.id_universidade = ? AND (c.nomeCurso LIKE ? OR c.siglaCurso like ?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, idUniversidade);
            bd.st.setString(2, '%' + curso.nomeCurso + '%');
            bd.st.setString(3, '%' + curso.siglaCurso + '%');

            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Curso(bd.rs.getInt("id"),
                    bd.rs.getString("siglaCurso"),
                    bd.rs.getString("nomeCurso"),
                    bd.rs.getInt("Id_Curso_Universidade"));
        } catch (SQLException erro) {
            System.out.println(erro.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
