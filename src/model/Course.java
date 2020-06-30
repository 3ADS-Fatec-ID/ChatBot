package model;

public class Course {

    public int id, universityCourseId;
    public String initials, name;

    public Course() {
    }

    public Course(int id, String initials, String name, int universityCourseId) {
        this.id = id;
        this.initials = initials;
        this.name = name;
        this.universityCourseId = universityCourseId;
    }

    @Override
    public String toString() {
        return "Curso{" + "id=" + id + ", siglaCurso=" + initials + ", nomeCurso=" + name + '}';
    }
}
