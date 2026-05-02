package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import team.project_2026.service.DiagramService;

@Controller
public class diagramController {

    @Autowired
    private DiagramService diagramService;

    @GetMapping("/projects/{projectId}/diagrams")
    public String diagramsPage(@PathVariable Integer projectId, Model model) {
        model.addAttribute("projectId", projectId);
        return "project/diagram";
    }

    @PostMapping("/projects/{projectId}/diagrams/usecase")
    public String generateUseCaseDiagram(@PathVariable Integer projectId,
                                         @RequestParam String tool,
                                         Model model) {

        String script = diagramService.generateUseCaseDiagram(projectId, tool);

        model.addAttribute("projectId", projectId);
        model.addAttribute("script", script);
        model.addAttribute("diagramKind", "Use Case Diagram");
        model.addAttribute("tool", tool);

        return "project/diagram";
    }

    @PostMapping("/projects/{projectId}/diagrams/class")
    public String generateClassDiagram(@PathVariable Integer projectId,
                                       @RequestParam String tool,
                                       Model model) {

        String script = diagramService.generateClassDiagram(projectId, tool);

        model.addAttribute("projectId", projectId);
        model.addAttribute("script", script);
        model.addAttribute("diagramKind", "Class Diagram");
        model.addAttribute("tool", tool);

        return "project/diagram";
    }
}
