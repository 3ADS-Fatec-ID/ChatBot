package model;

public class Aluno {

    //mapeia o banco de dados
    public int id, id_curso_universidade;
    public long id_telegram;
    public boolean termoAceite;
    public String nomeUsuario;

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", id_curso_universidade=" + id_curso_universidade + ", id_telegram=" + id_telegram + ", termoAceite=" + termoAceite + ", nomeUsuario=" + nomeUsuario + '}';
    }

}
