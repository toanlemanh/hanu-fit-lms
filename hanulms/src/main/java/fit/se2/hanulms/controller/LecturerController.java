package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.Repository.FacultyRepository;
import fit.se2.hanulms.Repository.LecturerRepository;
import fit.se2.hanulms.model.Course;
import fit.se2.hanulms.model.Faculty;
import fit.se2.hanulms.model.Lecturer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
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
    public ResponseEntity<String> confirmEditCourse(@RequestBody String requestBody) {
        String[] keyValuePairs = requestBody.split("&");
        String courseCode = null;
        String courseName = null;
        String enrolmentKey = null;
        String description = null;
        String facultyCode = null;
        Long[] lecturerIds = null;

        // Iterate over each key-value pair
        for (String pair : keyValuePairs) {
            // Split the key-value pair by "=" to extract the key and value
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = null;
            if (keyValue.length > 1) {
                // Decode URL-encoded value
                value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                if (!key.equals("_csrf") && !key.equals("lecturers")) {
                    System.out.println(key);
                    switch (key) {
                        case "code":
                            courseCode = value;
                        case "name":
                            courseName = value;
                        case "enrolmentKey":
                            enrolmentKey = value;
                        case "description":
                            description = value;
                        case "faculty":
                            facultyCode = value;
                        default:
                    }
                } else if (key.equals("lecturers")) {
                    // Step 1: Split the string into an array of substrings
                    String[] lecturerIdsStr = value.split(",\\s*"); // Split on comma and optional whitespace

                    // Step 2: Convert each substring to a Long and add it to a new array
                    lecturerIds = new Long[lecturerIdsStr.length];
                    for (int i = 0; i < lecturerIdsStr.length; i++) {
                        lecturerIds[i] = Long.parseLong(lecturerIdsStr[i].trim());
                    }
                }
            }
        }
        System.out.println("courseCode: "+courseCode);
        System.out.println("courseName: "+courseName);
        System.out.println("enrolmentKey: "+enrolmentKey);
        System.out.println("description: "+description);
        System.out.println("facultyCode: "+facultyCode);
        System.out.println("lecturerIds: "+Arrays.toString(lecturerIds));
        if (lecturerIds != null && facultyCode != null && courseCode != null && lecturerIds.length > 0) {
            // Retrieve existing course entity or create a new one
            Course course = courseRepository.findById(courseCode).orElse(new Course());
            List<Lecturer> allLecturers = lecturerRepository.findAll();
            // DELETE CURRENT COURSE AND PREPARE FOR ADDING A NEW COURSE WITH UPDATED INFORMATION
            // Clear existing associations for each lecturer
            for (Lecturer lecturer : allLecturers) {
                lecturer.getCourses().remove(course);
                System.out.println("Courses: " + lecturer.getCourses());
                lecturerRepository.save(lecturer); // Save to ensure changes are persisted
            }
            // Clear existing associations for current course
            course.getLecturers().clear();
            System.out.println("Lecturers: " + course.getLecturers());
            courseRepository.save(course);

            course.setCode(courseCode);
            course.setName(courseName);
            course.setEnrolmentKey(enrolmentKey);
            course.setDescription(description);
            course.setFaculty(facultyRepository.findById(facultyCode).orElse(null)); // Retrieve faculty entity
            // Retrieve lecturer entities by their IDs and update the course's lecturers
            List<Lecturer> selectedLecturers = lecturerRepository.findAllById(Arrays.asList(lecturerIds));


            // ADD A NEW COURSE WITH ATTRIBUTES FROM POST REQUEST
            // Set the course to each lecturer's list of associated courses
            for (Lecturer lecturer : selectedLecturers) {
                lecturer.getCourses().add(course);
            }
            course.setLecturers(selectedLecturers);
            courseRepository.save(course);
        }
//        return "redirect:/searchResult";
        return ResponseEntity.ok("Course information updated successfully.");
    }

    @GetMapping(value = "/createCourse")
    public String createCourse(Model model) {
        Course course = new Course();
        model.addAttribute("course", course);
        List<Faculty> allFaculties = facultyRepository.findAll();
        model.addAttribute("allFaculties", allFaculties);
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        model.addAttribute("allLecturers", allLecturers);
        return "/lecturer/course/createCourse";
    }

    @PostMapping(value = "/createCourse")
    public ResponseEntity<String> createCourse(@RequestBody String requestBody) {
        System.out.println(requestBody);
        String[] keyValuePairs = requestBody.split("&");
        String courseCode = null;
        String courseName = null;
        String enrolmentKey = null;
        String description = null;
        String facultyCode = null;
        Long[] lecturerIds = null;

        // Iterate over each key-value pair
        for (String pair : keyValuePairs) {
            // Split the key-value pair by "=" to extract the key and value
            String[] keyValue = pair.split("=");
            String key = keyValue[0];
            String value = null;
            if (keyValue.length > 1) {
                // Decode URL-encoded value
                value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                if (!key.equals("_csrf") && !key.equals("lecturers")) {
                    System.out.println(key);
                    switch (key) {
                        case "code":
                            courseCode = value;
                        case "name":
                            courseName = value;
                        case "enrolmentKey":
                            enrolmentKey = value;
                        case "description":
                            description = value;
                        case "faculty":
                            facultyCode = value;
                        default:
                    }
                } else if (key.equals("lecturers")) {
                    // Step 1: Split the string into an array of substrings
                    String[] lecturerIdsStr = value.split(",\\s*"); // Split on comma and optional whitespace

                    // Step 2: Convert each substring to a Long and add it to a new array
                    lecturerIds = new Long[lecturerIdsStr.length];
                    for (int i = 0; i < lecturerIdsStr.length; i++) {
                        lecturerIds[i] = Long.parseLong(lecturerIdsStr[i].trim());
                    }
                }
            }
        }
        System.out.println("courseCode: "+courseCode);
        System.out.println("courseName: "+courseName);
        System.out.println("enrolmentKey: "+enrolmentKey);
        System.out.println("description: "+description);
        System.out.println("facultyCode: "+facultyCode);
        System.out.println("lecturerIds: "+Arrays.toString(lecturerIds));
        if (lecturerIds != null && facultyCode != null && courseCode != null && lecturerIds.length > 0) {
            // Retrieve existing course entity or create a new one
            Course course = courseRepository.findById(courseCode).orElse(new Course());

            course.setCode(courseCode);
            course.setName(courseName);
            course.setEnrolmentKey(enrolmentKey);
            course.setDescription(description);
            course.setFaculty(facultyRepository.findById(facultyCode).orElse(null)); // Retrieve faculty entity
            // Retrieve lecturer entities by their IDs
            List<Lecturer> selectedLecturers = lecturerRepository.findAllById(Arrays.asList(lecturerIds));


            // ADD A NEW COURSE WITH ATTRIBUTES FROM POST REQUEST
            // Set the course to each lecturer's list of associated courses
            for (Lecturer lecturer : selectedLecturers) {
                lecturer.getCourses().add(course);
            }
            course.setLecturers(selectedLecturers);
            courseRepository.save(course);
        }
//        return "redirect:/searchResult";
        return ResponseEntity.ok("Course information updated successfully.");
    }

    @GetMapping(value = "/deleteCourse/{code}")
    public String deleteCourse(@PathVariable(value = "code") String courseCode) {
        Course course = courseRepository.findById(courseCode).orElse(new Course());
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        // Clear existing associations for each lecturer
        for (Lecturer lecturer : allLecturers) {
            lecturer.getCourses().remove(course);
            System.out.println("Courses: " + lecturer.getCourses());
            lecturerRepository.save(lecturer); // Save to ensure changes are persisted
        }
        // Clear existing associations for current course
        course.getLecturers().clear();
        System.out.println("Lecturers: " + course.getLecturers());
        courseRepository.save(course);

        courseRepository.delete(course);
        return "redirect:/myCourses";
    }

}
