package dao;

import java.sql.SQLException;
import model.StudentWeb;
import model.Student;

public class StudentWebDAO extends DAO {

    private final StudentWeb studentWeb;

    public StudentWebDAO(StudentWeb studentWeb) {
		this.studentWeb = studentWeb;
    }

    public boolean addUsuarioWeb(String nomeUsuario,String email) {
    	
    	studentWeb.nomeUsuario = nomeUsuario;
    	studentWeb.email = email;
    	
        String sql = "insert into UsuarioWeb (nomeUsuario, email, webId) values (?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, studentWeb.nomeUsuario);
            bd.st.setString(2, studentWeb.email);
            bd.st.setLong(3, studentWeb.webId);
            bd.st.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            bd.close();
        }
    }
    
    public boolean addRelation() {
    	
    	long id_telegram;
    	int id_usuario;
    	
    	String sql = "SELECT MIN(id_telegram) as id_telegram  FROM Usuario";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, studentWeb.webId);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            id_telegram = bd.rs.getLong("id_telegram") + 1;   
        } catch (SQLException e) {
            System.err.println(e);
            return false;	
        }
                
        sql = "insert into Usuario (nomeUsuario, id_telegram,criado_em, editado_em) values (?,?,?,?)";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, studentWeb.nomeUsuario);
            bd.st.setLong(2, id_telegram);
            bd.st.setTimestamp(3, getCurrentTimeStamp());
            bd.st.setTimestamp(4, getCurrentTimeStamp());
            bd.st.executeUpdate();
           
        } catch (SQLException e) {
            System.err.println(e);
            return false;	
        }
       
        sql = "SELECT id FROM Usuario Where id_telegram = ?";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, id_telegram);
            bd.rs = bd.st.executeQuery();
            bd.rs.next();
            id_usuario = bd.rs.getInt("id");   
        } catch (SQLException e) {
            System.err.println(e);
            return false;	
        }
        
        sql = "insert into Usuario_UsuarioWeb (id_UsuarioWeb, id_usuario) values (?,?)";
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, studentWeb.webId);
            bd.st.setInt(1, id_usuario);
            bd.st.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e);
            return false;	
        } 
        
        bd.close();
        
        return true;
    }
    
    public boolean existsWebId() {
        String sql = "select * from UsuarioWeb where webId = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, studentWeb.webId);
            bd.rs = bd.st.executeQuery();
            return bd.rs.next();
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }

    public boolean existsEmail() {
        String sql = "select * from UsuarioWeb where email = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setString(1, studentWeb.email);
            bd.rs = bd.st.executeQuery();
            return bd.rs.next();
        } catch (SQLException e) {
            System.err.println(e.toString());
            return false;
        } finally {
            bd.close();
        }
    }
    
    public boolean exists() {
        String sql = "SELECT * FROM Usuario_UsuarioWeb AS UU " + 
        				"INNER JOIN UsuarioWeb AS UW ON UW.id = UU.id_UsuarioWeb " + 
        				"WHERE UW.webId = ?";
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, studentWeb.webId);
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
        String sql = "SELECT * FROM Usuario AS U " + 
        		"INNER JOIN Usuario_UsuarioWeb AS UUW ON UUW.id_usuario = U.id " + 
        		"INNER JOIN UsuarioWeb AS UW ON UW.id = UUW.id_UsuarioWeb " + 
        		"WHERE UW.webId = ?";
        
        bd.getConnection();
        try {
            bd.st = bd.con.prepareStatement(sql);
            bd.st.setLong(1, studentWeb.webId);
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
    
/*

    public boolean delete() {
        String sql = "UPDATE Usuario SET termoAceite = 0, id_Universidade = NULL, id_curso_universidade = NULL where id = ?";
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
*/

}
