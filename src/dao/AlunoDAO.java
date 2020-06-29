package dao;

import java.sql.SQLException;
import java.sql.Date;
import model.Aluno;

public class AlunoDAO extends DAO {

    private final Aluno aluno;

    public AlunoDAO(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean cadastrar() {
        String sql = "insert into Usuario (nomeUsuario, id_telegram,criado_em, editado_em) values (?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, aluno.nomeUsuario);
            bd.st.setLong(2, aluno.idTelegram);
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setTimestamp(4, getCurrentTimeStamp());
            bd.st.executeUpdate();
            System.out.println("Cadastro OK!");
            return true;
        } catch (SQLException erro) {
            System.out.println(erro);
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean alterarCurso() {
        String sql = "update Usuario set id_curso_universidade = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, aluno.idCursoUniversidade);
            bd.st.setInt(2, aluno.id);
            int n = bd.st.executeUpdate();
            return n == 1;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean alterarUniversidade() {
        String sql = "update Usuario set id_Universidade = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, aluno.idUniversidade);
            bd.st.setInt(2, aluno.id);
            int n = bd.st.executeUpdate();
            return n == 1;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean alterarTermoAceite() {
        String sql = "update Usuario set termoAceite = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setBoolean(1, aluno.termoAceite);
            bd.st.setInt(2, aluno.id);
            int n = bd.st.executeUpdate();
            return n == 1;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean excluir() {
        String sql = "delete from Usuario where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, aluno.id);
            int n = bd.st.executeUpdate();
            return n == 1;
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean verificarCadastro() {
        String sql = "select * from Usuario where id_telegram = ? AND termoAceite = 1";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, aluno.idTelegram);
            bd.rs = bd.st.executeQuery();
            return bd.rs.next();
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public Aluno encontrarAluno() {
        String sql = "select * from Usuario where id_telegram = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, aluno.idTelegram);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Aluno(bd.rs.getInt("id"),
                    bd.rs.getInt("id_curso_universidade"),
                    bd.rs.getInt("id_telegram"),
                    bd.rs.getBoolean("termoAceite"),
                    bd.rs.getString("nomeUsuario"),
                    bd.rs.getInt("id_Universidade"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }

    public void cancelarUniversidade() {
        String sql = "update Usuario set id_Universidade = NULL where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, aluno.idUniversidade);
            bd.st.setInt(2, aluno.id);
            int n = bd.st.executeUpdate();
        } catch (SQLException erro) {
            System.out.println(erro.toString());
        } finally {
            bd.close();
        }
    }

}
