package model;

import java.sql.SQLException;
import services.BD;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.sql.Date;

public class AlunoDAO extends Aluno {

    LocalDateTime localDateTime = LocalDateTime.now();
    LocalDate localDate = localDateTime.toLocalDate();

    public BD bd;
    private String sql;

    public AlunoDAO() {
        bd = new BD();
    }

    public String cadastrar() {
        sql = "insert into Usuario values (?,?,?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, nomeUsuario);
            bd.st.setLong(2, idTelegram);
            bd.st.setBoolean(3, termoAceite);
            bd.st.setInt(4, idCursoUniversidade);
            bd.st.setDate(5, Date.valueOf(localDate));
            bd.st.setDate(6, Date.valueOf(localDate));
            bd.st.executeUpdate();
            return "Usu�rio cadastrado com sucesso!";
        } catch (SQLException erro) {
            return "Falha: " + erro;
        } finally {
            bd.close();
        }

    }

    public String alterarCurso() {
        sql = "update Usuario set id_curso_universidade = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, idCursoUniversidade);
            bd.st.setInt(2, id);
            int n = bd.st.executeUpdate();
            if (n == 1) {
                return "Curso alterado com sucesso!";
            } else {
                return "Curso n�o encontrado!";
            }
        } catch (SQLException erro) {
            return "Falha: " + erro;
        } finally {
            bd.close();
        }

    }

    public String excluir() {
        sql = "delete from Usuario where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, id);
            int n = bd.st.executeUpdate();
            if (n == 1) {
                return "Usu�rio exclu�do com sucesso!";
            } else {
                return "Usu�rio n�o encontrado!";
            }
        } catch (SQLException erro) {
            return "Falha: " + erro;
        } finally {
            bd.close();
        }
    }

    public boolean verificarCadastro() {
        sql = "select * from Usuario where id_telegram = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, idTelegram);
            bd.rs = bd.st.executeQuery();
            return bd.rs.next();
        } catch (SQLException erro) {
            return false;
        } finally {
            bd.close();
        }
    }

    public Aluno encontrarAluno() {
        Aluno a = new Aluno();
        sql = "select * from Usuario where id_telegram = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, idTelegram);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            while (bd.rs.next()) {
                a.id = bd.rs.getInt("id");
                a.idTelegram = bd.rs.getInt("id_telegram");
                a.idCursoUniversidade = bd.rs.getInt("id_curso_universidade");
                a.nomeUsuario = bd.rs.getString("nomeUsuario");
            }
            return a;
        } catch (SQLException erro) {
            return null;
        } finally {
            bd.close();
        }
    }

}
