package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team.project_2026.model.User;
import team.project_2026.service.UserService;

@Controller
public class authController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(){
        return "auth/signin";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "auth/signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        return "auth/signin";
    }
}