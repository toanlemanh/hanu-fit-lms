package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
public class ResourceController {
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/myCourses/course-details/{id}")
    public String getCourseDetailById ( Model model){
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
//        Employee employee = employeeRepository.findById(id).get();
//        model.addAttribute("employee", employee);
        return "/lecturer/course-resource/course-details";
    }

    //create topic

    //create announcement



}
