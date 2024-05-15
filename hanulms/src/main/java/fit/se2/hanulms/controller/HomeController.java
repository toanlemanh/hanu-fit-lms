package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.FacultyAnnouncementRepository;
import fit.se2.hanulms.model.FacultyAnnouncement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    FacultyAnnouncementRepository facultyAnnouncementRepository;
    @GetMapping("/")
    public String home(Model model) {
        List<FacultyAnnouncement> allFAs = facultyAnnouncementRepository.findAll();
        model.addAttribute("announcements", allFAs);
        return "/homepage";
    }
}
