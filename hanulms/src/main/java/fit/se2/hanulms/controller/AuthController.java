package fit.se2.hanulms.controller;

import fit.se2.hanulms.Repository.AdminRepository;
import fit.se2.hanulms.model.Admin;
import fit.se2.hanulms.model.UserTemplate;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    PasswordEncoder pEncoder;

    @GetMapping("/login")
    public String login() {
        return "/auth/login";
    }
    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("admin", new Admin());
        return "/auth/signup";
    }
    @PostMapping("/signup")
    public String signup(Model model,
                         @Valid UserTemplate ut,
                         BindingResult result,
                         HttpServletRequest request) {
        String reEnteredPassword = request.getParameter("reEnteredPassword");
        if (result.hasErrors() ||
            adminRepository.findByUsername(ut.getUsername()).isPresent() ||
            !reEnteredPassword.equals(ut.getPassword())
        ) {
            model.addAttribute("admin", ut);
            return "/auth/signup";
        }
        Admin admin = new Admin(ut, pEncoder);
        adminRepository.save(admin);
        return "redirect:/login";
    }
}
