package fit.se2.hanulms.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Entity
public class FacultyAnnouncement{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please enter the title of announcement")
    @Length(max = 40, message = "The title should be less than 40 characters")
    private String facultyAnnouncementTitle;

    @NotEmpty(message = "Please enter the description of topic")
    @Length(max = 255, message = "The description should be less than 255 characters")
    private String facultyAnnouncementDescription;
    @ManyToOne
    private Faculty faculty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFacultyAnnouncementTitle() {
        return facultyAnnouncementTitle;
    }

    public void setFacultyAnnouncementTitle(String facultyAnnouncementTitle) {
        this.facultyAnnouncementTitle = facultyAnnouncementTitle;
    }

    public String getFacultyAnnouncementDescription() {
        return facultyAnnouncementDescription;
    }

    public void setFacultyAnnouncementDescription(String facultyAnnouncementDescription) {
        this.facultyAnnouncementDescription = facultyAnnouncementDescription;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

}
