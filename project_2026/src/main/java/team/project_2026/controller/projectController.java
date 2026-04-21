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
import team.project_2026.model.CRC;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;
import team.project_2026.service.CrcService;
import java.util.List;
import java.security.Principal;

@Controller
public class projectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private UseCaseService useCaseService;

    @Autowired
    private CrcService crcService;


    @PostMapping("/projects/create")
    public String createProject(@ModelAttribute Project project, Principal principal) {
        projectService.createProject(project, userService.findByUsername(principal.getName()));
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/delete/{projectId}")
    public String deleteProject(@PathVariable int projectId) {
        projectService.deleteById(projectId);
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/edit/{projectId}")
    public String editProject(@PathVariable int projectId, Model model) {
        List<UseCase> useCases = useCaseService.getUseCaseByProjectId(projectId);
        List<CRC> crcCards = crcService.getCrcByProjectId(projectId);
        model.addAttribute("crcCards",crcCards);
        model.addAttribute("useCases",useCases);

        Project project = projectService.getById(projectId);
        model.addAttribute("project", project);
        return "/project/project";
    }


}