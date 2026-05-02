package team.project_2026.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import team.project_2026.model.CRC;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import team.project_2026.service.CrcService;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    @Mock
    private UseCaseService useCaseService;

    @Mock
    private CrcService crcService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @InjectMocks
    private projectController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        Project project = new Project();
        User user = new User();

        when(principal.getName()).thenReturn("nikos");
        when(userService.findByUsername("nikos")).thenReturn(user);

        String view = controller.createProject(project, principal);

        assertEquals("redirect:/dashboard", view);
        verify(userService).findByUsername("nikos");
        verify(projectService).createProject(project, user);
    }

    @Test
    void testDeleteProject() {
        String view = controller.deleteProject(1);

        assertEquals("redirect:/dashboard", view);
        verify(projectService).deleteById(1);
    }

    @Test
    void testEditProject() {
        Project project = new Project();
        project.setName("Test Project");

        UseCase useCase = new UseCase();
        useCase.setName("Login");

        CRC crc = new CRC();
        crc.setClassName("User");

        when(useCaseService.getUseCaseByProjectId(1))
                .thenReturn(List.of(useCase));

        when(crcService.getCrcByProjectId(1))
                .thenReturn(List.of(crc));

        when(projectService.getById(1))
                .thenReturn(project);

        String view = controller.editProject(1, model);

        assertEquals("/project/project", view);

        verify(useCaseService).getUseCaseByProjectId(1);
        verify(crcService).getCrcByProjectId(1);
        verify(projectService).getById(1);

        verify(model).addAttribute("useCases", List.of(useCase));
        verify(model).addAttribute("crcCards", List.of(crc));
        verify(model).addAttribute("project", project);
    }
}
