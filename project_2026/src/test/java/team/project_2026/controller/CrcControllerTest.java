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
import team.project_2026.service.CrcService;
import team.project_2026.service.ProjectService;
import team.project_2026.service.UserService;

import java.security.Principal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CrcControllerTest {

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    @Mock
    private CrcService crcService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @InjectMocks
    private crcController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrcPage() {
        String view = controller.crcPage(1, model);

        assertEquals("project/crc", view);
        verify(model).addAttribute(eq("crc"), any(CRC.class));
    }

    @Test
    void testCreateCrc() {
        CRC crc = new CRC();
        User user = new User();

        when(principal.getName()).thenReturn("nikos");
        when(userService.findByUsername("nikos")).thenReturn(user);

        String view = controller.createUseCase(crc, 1, principal);

        assertEquals("redirect:/projects/edit/1", view);
        verify(userService).findByUsername("nikos");
        verify(crcService).create(crc, 1, user);
    }

    @Test
    void testDeleteCrc() {
        String view = controller.deleteCrc(1, 2, principal);

        assertEquals("redirect:/projects/edit/1", view);
        verify(crcService).deleteById(2);
    }

    @Test
    void testEditCrc() {
        CRC crc = new CRC();
        crc.setClassName("User");

        when(crcService.findById(2)).thenReturn(crc);

        String view = controller.editCrc(1, 2, model);

        assertEquals("/project/editCrc", view);
        verify(model).addAttribute("crc", crc);
        verify(model).addAttribute("projectId", 1);
    }

    @Test
    void testUpdateCrc() {
        CRC updated = new CRC();
        updated.setClassName("Updated");

        String view = controller.updateCrc(1, 2, updated);

        assertEquals("redirect:/projects/edit/1", view);
        verify(crcService).updateCrc(2, updated);
    }

    @Test
    void testLinkCrcPage() {
        UseCase uc = new UseCase();
        uc.setName("Login");

        when(crcService.getUnlinkedUseCases(1, 2))
                .thenReturn(List.of(uc));

        String view = controller.linkCrcToUseCases(1, 2, model);

        assertEquals("/project/linkUsecases", view);

        verify(model).addAttribute("availableUseCases", List.of(uc));
        verify(model).addAttribute("projectId", 1);
        verify(model).addAttribute("crcId", 2);
    }

    @Test
    void testLinkCrcToUseCase() {
        String view = controller.linkCrcToUseCases(2, 3, 1);

        assertEquals("redirect:/projects/edit/1", view);
        verify(crcService).linkCrcToUseCase(3, 2);
    }

    @Test
    void testShowCrcLinks() {
        CRC crc = new CRC();
        UseCase uc = new UseCase();
        uc.setName("Login");

        when(crcService.findById(2)).thenReturn(crc);
        when(crcService.linkedUseCases(2)).thenReturn(List.of(uc));

        String view = controller.showCrcLinks(2, 1, model);

        assertEquals("project/crcLinks", view);

        verify(model).addAttribute("crc", crc);
        verify(model).addAttribute("linkedUseCases", List.of(uc));
    }
}
