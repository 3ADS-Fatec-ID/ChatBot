package model;

public class Curso {

    public int id, idCursoUniversidade;
    public String siglaCurso, nomeCurso;

    public Curso() {
    }

    public Curso(int id, String siglaCurso, String nomeCurso, int idCursoUniversidade) {
        this.id = id;
        this.siglaCurso = siglaCurso;
        this.nomeCurso = nomeCurso;
        this.idCursoUniversidade = idCursoUniversidade;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", siglaCurso=" + siglaCurso + ", nomeCurso=" + nomeCurso + '}';
    }
}
