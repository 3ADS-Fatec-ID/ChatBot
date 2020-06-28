package model;

public class Aluno {

    public int id, idCursoUniversidade;
    public long idTelegram;
    public boolean termoAceite;
    public String nomeUsuario;

    public Aluno(int id, int idCursoUniversidade, long idTelegram, boolean termoAceite, String nomeUsuario) {
        this.id = id;
        this.idCursoUniversidade = idCursoUniversidade;
        this.idTelegram = idTelegram;
        this.termoAceite = termoAceite;
        this.nomeUsuario = nomeUsuario;
    }

    public Aluno() {
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", id_curso_universidade=" + idCursoUniversidade + ", id_telegram=" + idTelegram + ", termoAceite=" + termoAceite + ", nomeUsuario=" + nomeUsuario + '}';
    }

}
