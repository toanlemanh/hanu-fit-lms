package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.Repository.TopicRepository;
import fit.se2.hanulms.model.Course;
import fit.se2.hanulms.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TopicController {
   @Autowired
    TopicRepository topicRepository;
   @Autowired
    CourseRepository courseRepository;

   @PostMapping(value="/myCourses/course-details/{code}/create-topic")
    public String createTopic(@PathVariable(value="code") String code, @RequestBody String title, @RequestBody String content) {
       Course course = courseRepository.findById(code).get();
       Topic topic = new Topic();
       topic.setTopicName(title);
       topic.setTopicDescription(content);
       topic.setCourse(course);


       return "redirect:/myCourses/course-details/{code}";
   }


}
