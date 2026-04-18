package team.project_2026.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team.project_2026.model.User;

@Controller
public class userController {

    @RequestMapping("/dashboard")
    public String dashboard( ) {
        return "auth/dashboard";
    }
}
