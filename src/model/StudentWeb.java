package model;

public class StudentWeb {

    public int id;
    public String email, nomeUsuario;
    public long webId;
    
    public StudentWeb(int id,String email, String nomeUsuario, long webId) {
        this.id = id;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.webId = webId;
        
    }

    public StudentWeb() {
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", email=" + email + ", nomeUsuario=" + nomeUsuario + ", webId=" + webId +"}";
    }
}
