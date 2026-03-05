package team.project_2026.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import team.project_2026.domain.Developer;
import team.project_2026.serivce.DeveloperService;

@Controller
public class AuthController {
    @Autowired
    DeveloperService developerService;

    @RequestMapping("/signin")
    public String signin(){
        return "auth/signin";
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new Developer());
        return "auth/signup";
    }

    @RequestMapping("/save")
    public String signupDeveloper(@ModelAttribute("user") Developer developer, Model model){

        if(developerService.isDeveloperPresent(developer)){
            model.addAttribute("successMessage", "Developer already registered!");
            return "auth/signin";
        }

        developerService.saveDeveloper(developer);

        model.addAttribute("successMessage", "Developer registered successfully!");

        return "auth/signin";
    }
}
