package model;

public class Aluno {

    //mapeia o banco de dados
    public int id, idCursoUniversidade;
    public long idTelegram;
    public boolean termoAceite;
    public String nomeUsuario;

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", id_curso_universidade=" + idCursoUniversidade + ", id_telegram=" + idTelegram + ", termoAceite=" + termoAceite + ", nomeUsuario=" + nomeUsuario + '}';
    }

}
