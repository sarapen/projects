package team.project_2026.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import team.project_2026.service.DiagramService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiagramControllerTest {

    @Mock
    private DiagramService diagramService;

    @Mock
    private Model model;

    @InjectMocks
    private diagramController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDiagramsPage() {
        String view = controller.diagramsPage(1, model);

        assertEquals("project/diagram", view);
        verify(model).addAttribute("projectId", 1);
    }

    @Test
    void testGenerateUseCaseDiagram() {
        when(diagramService.generateUseCaseDiagram(1, "PLANTUML"))
                .thenReturn("test-script");

        String view = controller.generateUseCaseDiagram(1, "PLANTUML", model);

        assertEquals("project/diagram", view);
        verify(model).addAttribute("script", "test-script");
        verify(model).addAttribute("diagramKind", "Use Case Diagram");
    }

    @Test
    void testGenerateClassDiagram() {
        when(diagramService.generateClassDiagram(1, "PLANTUML"))
                .thenReturn("class-script");

        String view = controller.generateClassDiagram(1, "PLANTUML", model);

        assertEquals("project/diagram", view);
        verify(model).addAttribute("script", "class-script");
        verify(model).addAttribute("diagramKind", "Class Diagram");
    }
}

