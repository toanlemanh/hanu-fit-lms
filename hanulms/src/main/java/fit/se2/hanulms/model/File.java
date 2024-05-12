package fit.se2.hanulms.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fileId;

    @NotEmpty(message = "You should type the file name")
    private String fileName;

    @NotEmpty(message = "You should type the file link")
    private String fileLink;

    @NotEmpty(message = "You should choose a type of file")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    @ManyToOne
    private Topic topic;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
