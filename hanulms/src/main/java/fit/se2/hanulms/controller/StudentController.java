package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.*;
import fit.se2.hanulms.model.Assignment;
import fit.se2.hanulms.model.Course;
import fit.se2.hanulms.model.Topic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class StudentController {
    @Autowired
    CourseRepository enrolledCourseRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    AssignmentRepository assignmentRepository;
    @Autowired
    SubmissionRepository submissionRepository;


    @GetMapping(value = "/course-detail/{code}/submit-assignment/{assId}")
    public String showAssignment(@PathVariable(value="code") String code,
                                 @PathVariable(value = "assId") Long assId,
                                 Model model) {
        Course course = enrolledCourseRepository.getReferenceById(code);
        Assignment assignment = assignmentRepository.getReferenceById(assId);
        model.addAttribute("course", course);
        model.addAttribute("assignment", assignment);

        return "/student/submit-assignment";
    }

    @GetMapping(value = "/submit-assignment")
    public String testShowAssignment() {
        return "/student/submit-assignment";
    }
}
