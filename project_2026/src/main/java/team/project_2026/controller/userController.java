package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import team.project_2026.model.User;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UserService;

import java.security.Principal;
import java.util.Optional;

@Controller
public class userController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {

        String username = principal.getName();
        User user = userService.findByUsername(username);

        model.addAttribute("projects", projectService.getProjectsByUser(user));

        return "auth/dashboard";
    }
}
