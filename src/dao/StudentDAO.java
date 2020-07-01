package dao;

import java.sql.SQLException;
import model.Student;

public class StudentDAO extends DAO {

    private final Student student;

    public StudentDAO(Student student) {
        this.student = student;
    }

    public boolean add() {
        String sql = "insert into Usuario (nomeUsuario, id_telegram,criado_em, editado_em) values (?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, student.name);
            bd.st.setLong(2, student.telegramId);
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setTimestamp(4, getCurrentTimeStamp());
            bd.st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean updateCourse() {
        String sql = "update Usuario set id_curso_universidade = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, student.universityCourseId);
            bd.st.setInt(2, student.id);
            bd.st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean updateUniversity() {
        String sql = "update Usuario set id_Universidade = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, student.universityId);
            bd.st.setInt(2, student.id);
            bd.st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean acceptTerms() {
        String sql = "update Usuario set termoAceite = ? where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setBoolean(1, student.termAccepted);
            bd.st.setInt(2, student.id);
            bd.st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean delete() {
        String sql = "UPDATE Usuario SET termoAceite = false, id_Universidade = NULL, id_curso_universidade = NULL where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, student.id);
            int n = bd.st.executeUpdate();
            return n == 1;
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean exists() {
        String sql = "select * from Usuario where id_telegram = ? AND termoAceite = 1";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, student.telegramId);
            bd.rs = bd.st.executeQuery();
            return bd.rs.next();
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public Student find() {
        String sql = "select * from Usuario where id_telegram = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, student.telegramId);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            return new Student(bd.rs.getInt("id"),
                    bd.rs.getInt("id_curso_universidade"),
                    bd.rs.getInt("id_telegram"),
                    bd.rs.getBoolean("termoAceite"),
                    bd.rs.getString("nomeUsuario"),
                    bd.rs.getInt("id_Universidade"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }

    public void deleteUniversity() {
        String sql = "update Usuario set id_Universidade = NULL where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, student.id);
            bd.st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            bd.close();
        }
    }
    
    public void deleteCourse() {
        String sql = "update Usuario set id_curso_universidade = NULL where id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, student.id);
            bd.st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.toString());
        } finally {
            bd.close();
        }
    }
}
