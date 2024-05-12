package fit.se2.hanulms.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class FileDTO {

    @NotEmpty(message = "You have to insert a title to this topic item")
    private String fileTitle;
    @NotEmpty(message="You have to insert a link to this topic item")
    private MultipartFile fileData;

    @NotEmpty(message="You have to choose a file type")
    private String type;

    public MultipartFile getFileData() {
        return fileData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFileData(MultipartFile fileData) {
        this.fileData = fileData;
    }

    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }
}
