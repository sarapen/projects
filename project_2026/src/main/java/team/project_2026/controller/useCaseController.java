package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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
        return "redirect:/projects/edit/" + projectId;
    }

    @PostMapping("/project/{projectId}/usecases/delete/{useCaseId}")
    public String deleteUseCase(@PathVariable int useCaseId, @PathVariable int projectId) {
        useCaseService.deleteById(useCaseId);
        return "redirect:/projects/edit/" + projectId;
    }

    @GetMapping("/project/{projectId}/usecases/edit/{useCaseId}")
    public String editCasePage(Model model, @PathVariable int projectId, @PathVariable int useCaseId) {
        UseCase useCase = useCaseService.findById(useCaseId);
        model.addAttribute("useCase", useCase);
        return "project/editUseCase";
    }

    @PostMapping("/projects/{projectId}/usecases/edit/{useCaseId}")
    public String updateCasePage(@PathVariable int useCaseId, @PathVariable int projectId) {
        //update attributes to be done
        return "redirect:/projects/edit/" + projectId;
    }

}

