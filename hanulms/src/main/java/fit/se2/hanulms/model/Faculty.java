package fit.se2.hanulms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Faculty {
    @Id
    private String code;
    private String name;
    @OneToMany(mappedBy = "faculty")
    private List<Lecturer> lecturers;
    @OneToMany(mappedBy = "faculty")
<<<<<<< HEAD
    private List<Course> courses;
=======
    private List<Student> students;
    @OneToMany(mappedBy = "faculty")
    private List<Course> courses;
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
>>>>>>> 35abeb925879a926c3a96a5bb4335fbbcaec2bbf

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Lecturer> getLecturers() {
        return lecturers;
    }

    public void setLecturers(List<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
