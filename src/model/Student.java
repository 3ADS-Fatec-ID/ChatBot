package model;

public class Student {

    public int id, universityCourseId, universityId;
    public long telegramId;
    public boolean termAccepted;
    public String name, email;

    public Student(int id, int universityCourseId, long telegramId, boolean termAccepted, String name, int universityId) {
        this.id = id;
        this.universityCourseId = universityCourseId;
        this.telegramId = telegramId;
        this.termAccepted = termAccepted;
        this.name = name;
        this.universityId = universityId;
    }
    
    public Student(int id, int universityCourseId, long telegramId, boolean termAccepted, String name, int universityId, String email) {
        this.id = id;
        this.universityCourseId = universityCourseId;
        this.telegramId = telegramId;
        this.termAccepted = termAccepted;
        this.name = name;
        this.universityId = universityId;
        this.email = email;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", id_curso_universidade=" + universityCourseId + ", id_telegram=" + telegramId + ", termoAceite=" + termAccepted + ", nomeUsuario=" + name + ", E-mail=" + email +'}';
    }
}
