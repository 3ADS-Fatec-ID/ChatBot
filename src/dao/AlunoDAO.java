package dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;
import model.Aluno;

public class AlunoDAO extends DAO {

    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = localDateTime.toLocalDate();

    private final Aluno aluno;

    public AlunoDAO(Aluno aluno) {
        this.aluno = aluno;
    }

    public boolean cadastrar() {
        String sql = "insert into Usuario values (?,?,?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, aluno.nomeUsuario);
            bd.st.setLong(2, aluno.idTelegram);
            bd.st.setBoolean(3, aluno.termoAceite);
            bd.st.setInt(4, aluno.idCursoUniversidade);
            bd.st.setDate(5, Date.valueOf(localDate));
            bd.st.setDate(6, Date.valueOf(localDate));
            bd.st.executeUpdate();
            return true;
        } catch (SQLException erro) {
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
        String sql = "select * from Usuario where id_telegram = ?";
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
                    bd.rs.getString("nomeUsuario"));
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }

}
