package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.Repository.FacultyRepository;
import fit.se2.hanulms.Repository.LecturerRepository;
import fit.se2.hanulms.model.Course;
import fit.se2.hanulms.model.Faculty;
import fit.se2.hanulms.model.Lecturer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LecturerController {
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    LecturerRepository lecturerRepository;

    @GetMapping(value = "/myCourses")
    public String myCourses(Model model) {
        List<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "/lecturer/course/myCourses";
    }
    @GetMapping(value = "/searchResult")
    public String searchResult() {
        return "searchResult";
    }
    @GetMapping(value = "/courseDetail")
    public String courseDetail() {
        return "/lecturer/course/course-detail";
    }
    @GetMapping(value = "/editCourse/{courseCode}")
    public String editCourse(@PathVariable(value = "courseCode") String courseCode, Model model) {
        Course currentCourse = courseRepository.getReferenceById(courseCode);
        model.addAttribute("course", currentCourse);
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        List<Lecturer> lecturers = currentCourse.getLecturers();
        model.addAttribute("lecturers", lecturers);
        List<Long> lecturerIds = lecturers.stream().map(Lecturer::getId).collect(Collectors.toList());
        model.addAttribute("lecturerIds", lecturerIds);
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        model.addAttribute("allLecturers", allLecturers);
        return "/lecturer/course/editCourse";
    }
    @PostMapping(value = "/confirmEditCourse")
    public String confirmEditCourse(@Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            return "/lecturer/course/editCourse";
        }
        courseRepository.save(course);
        return "redirect:/myCourses";
    }

//    public String confirmEditCourse(@Valid Course course, BindingResult result,
//                                    @RequestParam(value = "lecturers", required = false, defaultValue = "0") String lecturerIds) {
//        if (result.hasErrors()) {
//            return "/lecturer/course/editCourse";
//        }
//        courseRepository.save(course);
//        return "redirect:/myCourses";
//    }
//
//    public String confirmEditCourse(@PathVariable String courseCode,
//                                    @PathVariable String courseName) {
//        if (result.hasErrors()) {
//            return "/lecturer/course/editCourse";
//        }
//        courseRepository.save(course);
//        return "redirect:/myCourses";
//    }
    @GetMapping(value = "/setLecturers")
    public String setLecturers(@RequestParam(value = "lecturers", required = false, defaultValue = "0") String lecturerIds) {

        return "redirect:/myCourses";
    }

}
