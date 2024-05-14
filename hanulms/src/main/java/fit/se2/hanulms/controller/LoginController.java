package fit.se2.hanulms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    @GetMapping("/")
    public String home(){
        return "/homepage";
    }


    @GetMapping("/login")
    public String login (){
        return "auth/login";
    }
}

