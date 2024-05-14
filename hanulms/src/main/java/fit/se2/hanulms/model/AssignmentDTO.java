package fit.se2.hanulms.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

public class AssignmentDTO {
    @NotEmpty(message="You have to enter the title")
    private String assTitle;

    private String assDescription;

    private MultipartFile attachment;

    private Date deadline;

    public String getAssTitle() {
        return assTitle;
    }

    public void setAssTitle(String assTitle) {
        this.assTitle = assTitle;
    }

    public String getAssDescription() {
        return assDescription;
    }

    public void setAssDescription(String assDescription) {
        this.assDescription = assDescription;
    }

    public MultipartFile getAttachment() {
        return attachment;
    }

    public void setAttachment(MultipartFile attachment) {
        this.attachment = attachment;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
