package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import team.project_2026.model.CRC;
import org.springframework.ui.Model;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.service.CrcService;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class crcController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private CrcService crcService;

    @GetMapping("/projects/{projectId}/crc/create")
    public String crcPage(@PathVariable int projectId,  Model model) {
        model.addAttribute("crc", new CRC());
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

    @GetMapping("/projects/{projectId}/crc/edit/{id}")
    public String editCrc(@PathVariable int projectId, @PathVariable int id, Model model) {
        CRC crc = crcService.findById(id);

        model.addAttribute("crc", crc);
        model.addAttribute("projectId", projectId);

        return "/project/editCrc";
    }

    @PostMapping("/projects/{projectId}/crc/update/{id}")
    public String updateCrc(@PathVariable int projectId, @PathVariable int id, @ModelAttribute CRC updatedCRC) {
        crcService.updateCrc(id, updatedCRC);
        return "redirect:/projects/edit/" + projectId;
    }

    @GetMapping("/projects/{projectId}/crc/{crcId}/link-usecases")
    public String linkCrcToUseCases(@PathVariable int projectId, @PathVariable int crcId, Model model) {
        List<UseCase> availableUseCases = crcService.getUnlinkedUseCases(projectId, crcId);
        model.addAttribute("availableUseCases", availableUseCases);
        model.addAttribute("projectId", projectId);
        model.addAttribute("crcId", crcId);
        return "/project/linkUsecases";
    }


    @PostMapping("/projects/{projectId}/crc/{crcId}/useCase/{useCaseId}/link")
    public String linkCrcToUseCases(@PathVariable int crcId, @PathVariable int useCaseId, @PathVariable int projectId) {
        crcService.linkCrcToUseCase(useCaseId, crcId);
        return "redirect:/projects/edit/" + projectId;
    }

    @GetMapping("/projects/{projectId}/crc/{crcId}/links")
    public String showCrcLinks(@PathVariable int crcId, @PathVariable int projectId, Model model) {
        List<UseCase> linkedUseCases = crcService.linkedUseCases(crcId);
        CRC crc = crcService.findById(crcId);
        model.addAttribute("crc", crc);
        model.addAttribute("linkedUseCases", linkedUseCases);
        return "project/crcLinks";
    }
}


