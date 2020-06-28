package model;

public class Curso extends Universidade {

    public int id;
    public String siglaCurso, nomeCurso;

    public Curso() {
    }

    public Curso(int id, String siglaCurso, String nomeCurso) {
        this.id = id;
        this.siglaCurso = siglaCurso;
        this.nomeCurso = nomeCurso;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", siglaCurso=" + siglaCurso + ", nomeCurso=" + nomeCurso + '}';
    }
}
