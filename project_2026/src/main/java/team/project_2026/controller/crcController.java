package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team.project_2026.model.CRC;
import team.project_2026.model.Project;
import team.project_2026.service.CrcService;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;

import java.security.Principal;

@Controller
public class crcController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private CrcService crcService;

    @GetMapping("/projects/{projectId}/crc/create")
    public String crcPage(@PathVariable int projectId) {
        return "project/crc";
    }

    @PostMapping("/projects/{projectId}/crc/create")
    public String createUseCase(@ModelAttribute CRC crc, @PathVariable int projectId, Principal principal) {
        crcService.create(crc, projectId, userService.findByUsername(principal.getName()));
        return "redirect:/projects/edit/" + projectId;
    }

    @PostMapping("/projects/{projectId}/crc/delete/{crcId}")
    public String deleteCrc(@PathVariable int projectId, @PathVariable int crcId, Principal principal) {
        crcService.deleteById(crcId);
        return "redirect:/projects/edit/" + projectId;
    }
}


