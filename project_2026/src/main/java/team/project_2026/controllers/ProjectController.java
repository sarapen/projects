package team.project_2026.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project_2026.domain.Project;
import team.project_2026.serivce.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public String projects(Model model) {

        model.addAttribute("projects", service.findAll());
        model.addAttribute("project", new Project());

        return "projects";
    }

    @PostMapping
    public String create(Project project) {

        service.save(project);

        return "redirect:/projects";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "redirect:/projects";
    }
}
