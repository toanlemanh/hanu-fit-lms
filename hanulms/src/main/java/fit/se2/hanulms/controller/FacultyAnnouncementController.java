package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.FacultyAnnouncementRepository;
import fit.se2.hanulms.Repository.FacultyRepository;
import fit.se2.hanulms.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class FacultyAnnouncementController {
    @Autowired
    FacultyAnnouncementRepository facultyAnnouncementRepository;

    @Autowired
    FacultyRepository facultyRepository;

    @GetMapping("/facultyAnnouncement")
    public String facultyAnnouncement(Model model) {
        List<FacultyAnnouncement> facultyAnnouncements = facultyAnnouncementRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("facultyAnnouncements", facultyAnnouncements);
        return "/lecturer/faculty-announcement/facultyAnnouncement";
    }

    @GetMapping("/create-faculty-announcement")
    public String showCreateFacultyAnnouncement(Model model) {
        FacultyAnnouncement facultyAnnouncement = new FacultyAnnouncement();
        model.addAttribute("facultyAnnouncement", facultyAnnouncement);
        // Retrieve the list of faculties from the database
        List<Faculty> faculties = facultyRepository.findAll();
        model.addAttribute("faculties", faculties);
        return "/lecturer/faculty-announcement/create-faculty-announcement";
    }

    @PostMapping("/create-faculty-announcement")
    public String createFacultyAnnouncement(@Valid FacultyAnnouncement facultyAnnouncement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "/lecturer/faculty-announcement/create-faculty-announcement";
        }
        facultyAnnouncementRepository.save(facultyAnnouncement);
        return "redirect:/facultyAnnouncement";
    }

    @RequestMapping(value = "/lecturer/faculty-announcement/facultyAnnouncement/delete/{id}")
    public String deleteFacultyAnnouncement(@PathVariable(value = "id") Long id) {
        if (facultyAnnouncementRepository.findById(id).isPresent()) {
            FacultyAnnouncement facultyAnnouncement = facultyAnnouncementRepository.findById(id).get();
            facultyAnnouncementRepository.delete(facultyAnnouncement);
        }
        return "redirect:/facultyAnnouncement";
    }

}
