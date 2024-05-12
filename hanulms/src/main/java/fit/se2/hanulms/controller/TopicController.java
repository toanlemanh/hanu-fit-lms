package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.AnnouncementRepository;
import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.Repository.TopicRepository;
import fit.se2.hanulms.model.Announcement;
import fit.se2.hanulms.model.AnnouncementDTO;
import fit.se2.hanulms.model.Course;
import fit.se2.hanulms.model.Topic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController {
   @Autowired
    TopicRepository topicRepository;
   @Autowired
    CourseRepository courseRepository;
   @Autowired
    AnnouncementRepository announcementRepository;

    @RequestMapping(value = "/myCourses/course-details/{code}")
    public String getCourseDetailById (@PathVariable (value="code") String code, @RequestParam(value="error", required = false, defaultValue = "default") String error, Model model){
        Course course = courseRepository.findById(code).get();
        List<Announcement> announcements = course.getAnnouncements();
        List<Topic> topics = course.getTopics();
        Topic topic = new Topic();
        AnnouncementDTO announcementDTO = new AnnouncementDTO();
        model.addAttribute("announcements", announcements);
        model.addAttribute("announcementDTO", announcementDTO);
        model.addAttribute("course", course);
        model.addAttribute("topics", topics);
        model.addAttribute("topic", topic);
        model.addAttribute("error", error);
        return "/lecturer/course-resource/course-details";
    }

   @PostMapping(value="/myCourses/course-details/{code}/create-topic")
    public String insertTopic(@PathVariable(value="code") String code,@Valid Topic topic, BindingResult result  ,Model model, @RequestBody String topicDescription){
       System.out.print("DCourse code"+code);
       System.out.println("Topic obj"+ topic.getTopicName());
       System.out.println(topicDescription);
       Course course = courseRepository.getReferenceById(code);
       if (result.hasErrors()){
           String errors = "Bad creating a topic";
           System.out.println("Errors :"+ errors);
          return "redirect:/myCourses/course-details/"+ code+"?error="+ errors;
       }
       else {
           topic.setCourse(course);
           topicRepository.save(topic);
           return "redirect:/myCourses/course-details/"+ code;
       }
   }

    @PostMapping(value="/myCourses/course-details/{code}/create-announcement")
    public String insertAnnouncement(@PathVariable(value="code") String code, @Valid AnnouncementDTO announcementDTO, BindingResult result  , Model model){
        Course course = courseRepository.getReferenceById(code);


        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            String errors = "Bad creating an announcement";
            System.out.println("Errors :"+ errors);
            return "redirect:/myCourses/course-details/"+ code+"?error="+ errors;
        }
        else {
            // save image file
            MultipartFile attachment = announcementDTO.getAttachment();
            Date createdAt = new Date();
            String storageFileName = createdAt.getTime()+"_"+ attachment.getOriginalFilename();

            try {
                String uploadDir = "src/main/resources/static/images/";
                Path uploadPath = Paths.get(uploadDir);

                if (!Files.exists(uploadPath)){
                    Files.createDirectories(uploadPath);
                }
                try (InputStream inputStream = attachment.getInputStream()){
                    Files.copy(inputStream, Paths.get(uploadDir+storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
            }
            catch (Exception e){
                System.out.println("Exception: "+ e.getMessage());
            }

            Announcement announcement = new Announcement();
            announcement.setAnnouncementTitle(announcementDTO.getAnnouncementTitle());
            announcement.setAnnouncementDescription(announcementDTO.getAnnouncementDescription());
            announcement.setAttachment(storageFileName);
            announcement.setCourse(course);
            announcementRepository.save(announcement);
            return "redirect:/myCourses/course-details/"+ code;
        }
    }








}
