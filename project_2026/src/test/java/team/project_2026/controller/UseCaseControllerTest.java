package team.project_2026.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import team.project_2026.model.CRC;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UseCaseService;
import team.project_2026.service.UserService;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UseCaseControllerTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    @Mock
    private UseCaseService useCaseService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @InjectMocks
    private useCaseController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUseCasePage() {
        String view = controller.useCasePage(1);

        assertEquals("project/useCase", view);
    }

    @Test
    void testCreateUseCase() {
        UseCase useCase = new UseCase();
        User user = new User();

        when(principal.getName()).thenReturn("nikos");
        when(userService.findByUsername("nikos")).thenReturn(user);

        String view = controller.createUseCase(useCase, 1, principal);

        assertEquals("redirect:/projects/edit/1", view);
        verify(userService).findByUsername("nikos");
        verify(useCaseService).create(useCase, 1, user);
    }

    @Test
    void testDeleteUseCase() {
        String view = controller.deleteUseCase(2, 1);

        assertEquals("redirect:/projects/edit/1", view);
        verify(useCaseService).deleteById(2);
    }

    @Test
    void testEditCasePage() {
        UseCase useCase = new UseCase();
        useCase.setName("Login");

        when(useCaseService.findById(2)).thenReturn(useCase);

        String view = controller.editCasePage(model, 1, 2);

        assertEquals("project/editUseCase", view);
        verify(useCaseService).findById(2);
        verify(model).addAttribute("useCase", useCase);
    }

    @Test
    void testUpdateCasePage() {
        UseCase updated = new UseCase();
        updated.setName("Updated Login");

        String view = controller.updateCasePage(2, 1, updated);

        assertEquals("redirect:/projects/edit/1", view);
        verify(useCaseService).updateUser(2, updated);
    }

    @Test
    void testShowUseCasesLinks() {
        CRC crc = new CRC();
        crc.setClassName("User");

        UseCase useCase = new UseCase();
        useCase.setName("Login");
        useCase.setCrcCards(List.of(crc));

        when(useCaseService.findById(2)).thenReturn(useCase);

        String view = controller.showUseCasesLinks(2, 1, model);

        assertEquals("project/useCaseLinks", view);
        verify(useCaseService).findById(2);
        verify(model).addAttribute("useCase", useCase);
        verify(model).addAttribute("crcCards", List.of(crc));
    }
}
