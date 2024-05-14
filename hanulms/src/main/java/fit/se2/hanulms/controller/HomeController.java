package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.CourseRepository;
import fit.se2.hanulms.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

<<<<<<< HEAD
//@Controller
//public class HomeController {
//    @GetMapping("/")
//    public String home() {
//        return "/homepage";
//    }
//}
=======
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "/admin/create-account";
    }
}
>>>>>>> 35abeb925879a926c3a96a5bb4335fbbcaec2bbf
