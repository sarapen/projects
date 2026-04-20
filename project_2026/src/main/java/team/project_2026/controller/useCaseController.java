package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;

import java.security.Principal;

@Controller
public class useCaseController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private UseCaseService useCaseService;

    @GetMapping("/projects/{projectId}/usecase/create")
    public String useCasePage(@PathVariable int projectId) {
        return "project/useCase";
    }

    @PostMapping("/projects/{projectId}/usecase/create")
    public String createUseCase(@ModelAttribute UseCase useCase, @PathVariable int projectId, Principal principal) {
        useCaseService.create(useCase, projectId, userService.findByUsername(principal.getName()));
        return "redirect:/dashboard";
    }

    @PostMapping("/project/{projectId}/usecases/delete/{useCaseId}")
    public String deleteUseCase(@PathVariable int useCaseId, @PathVariable int projectId) {
        useCaseService.deleteById(useCaseId);
        System.out.println("delete");
        return "redirect:/projects/edit/" + projectId;
    }

}

