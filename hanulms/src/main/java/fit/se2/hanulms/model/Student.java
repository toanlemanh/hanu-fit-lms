package fit.se2.hanulms.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Student {
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
}
