package fit.se2.hanulms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Submission {
    @Id
    private Long id;
    private boolean submissionStatus;
    private boolean deadlineStatus;
    private Date lastModifiedDate;
    @ManyToOne
    private Assignment assignment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isSubmissionStatus() {
        return submissionStatus;
    }

    public void setSubmissionStatus(boolean submissionStatus) {
        this.submissionStatus = submissionStatus;
    }

    public boolean isDeadlineStatus() {
        return deadlineStatus;
    }

    public void setDeadlineStatus(boolean deadlineStatus) {
        this.deadlineStatus = deadlineStatus;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }
}
