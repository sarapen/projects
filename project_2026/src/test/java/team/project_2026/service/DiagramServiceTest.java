package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.project_2026.model.CRC;
import team.project_2026.model.UseCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DiagramServiceTest {

    @Mock
    private UseCaseService useCaseService;

    @Mock
    private CrcService crcService;

    @InjectMocks
    private DiagramService diagramService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateUseCaseDiagramPlantUml() {
        UseCase useCase = new UseCase();
        useCase.setId(1);
        useCase.setName("Login");
        useCase.setActors("User");

        when(useCaseService.getUseCaseByProjectId(1))
                .thenReturn(List.of(useCase));

        String result = diagramService.generateUseCaseDiagram(1, "PLANTUML");

        assertTrue(result.contains("@startuml"));
        assertTrue(result.contains("Login"));
        assertTrue(result.contains("User"));

        verify(useCaseService).getUseCaseByProjectId(1);
    }

    @Test
    void testGenerateClassDiagramNomnoml() {
        CRC crc = new CRC();
        crc.setClassName("User");
        crc.setResponsibilities("login");
        crc.setCollaborations("Project");

        when(crcService.getCrcByProjectId(1))
                .thenReturn(List.of(crc));

        String result = diagramService.generateClassDiagram(1, "NOMNOML");

        assertTrue(result.contains("[User|login]"));
        assertTrue(result.contains("[User] -> [Project]"));

        verify(crcService).getCrcByProjectId(1);
    }
}
