//package fit.se2.hanulms.controller;
//
//import fit.se2.blogWebApp.model.User;
//import fit.se2.blogWebApp.model.UserTemplate;
//import fit.se2.blogWebApp.repository.UserRepository;
//import jakarta.validation.Valid;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AuthController {
//    private UserRepository userRepository;
//
//    public AuthController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/register")
//    public String register() {
//        return "register";
//    }
//    @PostMapping("/register")
//    public String registerHandle(PasswordEncoder pEncoder, Model model, @Valid UserTemplate ut, BindingResult result) {
//        if (!result.hasErrors()) {
//            User u = new User(ut, pEncoder);
//            userRepository.save(u);
//        }
//        return "register";
//    }
//}
