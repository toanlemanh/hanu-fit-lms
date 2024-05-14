package fit.se2.hanulms.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
<<<<<<<< HEAD:hanulms/src/main/java/fit/se2/hanulms/model/Lecturer.java
public class Lecturer {
========
public class Student {
>>>>>>>> 35abeb925879a926c3a96a5bb4335fbbcaec2bbf:hanulms/src/main/java/fit/se2/hanulms/model/Student.java
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @ManyToOne
    private Faculty faculty;
    @ManyToMany
    private List<Course> courses;
    private String username;

    private String password;
<<<<<<<< HEAD:hanulms/src/main/java/fit/se2/hanulms/model/Lecturer.java

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
========
>>>>>>>> 35abeb925879a926c3a96a5bb4335fbbcaec2bbf:hanulms/src/main/java/fit/se2/hanulms/model/Student.java
}
