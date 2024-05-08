package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LecturerController {
    @Autowired
    CourseRepository courseRepository;

    @RequestMapping(value = "/myCourses")
    public String myCourses(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "/lecturer/course/myCourses";
    }
    @RequestMapping(value = "/searchResult")
    public String searchResult() {
        return "searchResult";
    }
}
