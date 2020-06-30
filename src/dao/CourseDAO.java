/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import model.Course;

/**
 *
 * @author joao
 */
public class CourseDAO extends DAO {

    private Course course;

    public CourseDAO() {
    }

    public CourseDAO(Course course) {
        this.course = course;
    }

    public Course find(int universityId) {
        String sql = "SELECT c.id as id, siglaCurso, nomeCurso, c_u.id as Id_Curso_Universidade FROM Curso c, Curso_Universidade c_u WHERE c.id = c_u.id_curso AND c_u.id_universidade = ? AND (c.nomeCurso LIKE ? OR c.siglaCurso like ?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, universityId);
            bd.st.setString(2, '%' + course.name + '%');
            bd.st.setString(3, '%' + course.initials + '%');
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            
            return new Course(bd.rs.getInt("id"),
                    bd.rs.getString("siglaCurso"),
                    bd.rs.getString("nomeCurso"),
                    bd.rs.getInt("Id_Curso_Universidade"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }

    public Course findByUniversityCourse() {
        String sql = "SELECT c.id as id, c.siglaCurso, c.nomeCurso ,c_u.id as Id_Curso_Universidade FROM Curso c, Curso_Universidade c_u WHERE c.id = c_u.id_curso AND c.id = c_u.id_curso AND c_u.id = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setInt(1, course.universityCourseId);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            
            return new Course(bd.rs.getInt("id"),
                    bd.rs.getString("siglaCurso"),
                    bd.rs.getString("nomeCurso"),
                    bd.rs.getInt("Id_Curso_Universidade"));
        } catch (SQLException e) {
            System.err.println(e.toString());
            return null;
        } finally {
            bd.close();
        }
    }
}
