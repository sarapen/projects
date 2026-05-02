package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.project_2026.model.CRC;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import team.project_2026.repository.crcRepo;
import team.project_2026.repository.projectRepo;
import team.project_2026.repository.useCaseRepo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CrcServiceTest {

    @Mock
    private crcRepo crcRepo;

    @Mock
    private useCaseRepo useCaseRepo;

    @Mock
    private projectRepo projectRepo;

    @InjectMocks
    private CrcService crcService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Project project = new Project();
        User user = new User();

        CRC crc = new CRC();
        crc.setClassName("User");

        when(projectRepo.findProjectById(1)).thenReturn(project);

        crcService.create(crc, 1, user);

        assertEquals(project, crc.getProject());
        verify(projectRepo).findProjectById(1);
        verify(crcRepo).save(crc);
    }

    @Test
    void testGetCrcByProjectId() {
        CRC crc = new CRC();
        crc.setClassName("User");

        when(crcRepo.getCrcByProjectId(1))
                .thenReturn(List.of(crc));

        List<CRC> result = crcService.getCrcByProjectId(1);

        assertEquals(1, result.size());
        assertEquals("User", result.get(0).getClassName());

        verify(crcRepo).getCrcByProjectId(1);
    }

    @Test
    void testDeleteById() {
        crcService.deleteById(1);

        verify(crcRepo).deleteById(1);
    }

    @Test
    void testFindById() {
        CRC crc = new CRC();
        crc.setClassName("User");

        when(crcRepo.findById(1)).thenReturn(crc);

        CRC result = crcService.findById(1);

        assertEquals("User", result.getClassName());
        verify(crcRepo).findById(1);
    }

    @Test
    void testUpdateCrc() {
        CRC existing = new CRC();
        existing.setClassName("OldClass");

        CRC updated = new CRC();
        updated.setClassName("NewClass");
        updated.setResponsibilities("login");
        updated.setCollaborations("Project");

        when(crcRepo.findById(1)).thenReturn(existing);

        crcService.updateCrc(1, updated);

        assertEquals("NewClass", existing.getClassName());
        assertEquals("login", existing.getResponsibilities());
        assertEquals("Project", existing.getCollaborations());

        verify(crcRepo).findById(1);
        verify(crcRepo).save(existing);
    }

    @Test
    void testGetUnlinkedUseCases() {
        UseCase linkedUseCase = new UseCase();
        linkedUseCase.setName("Login");

        UseCase unlinkedUseCase = new UseCase();
        unlinkedUseCase.setName("Register");

        CRC crc = new CRC();
        crc.setUseCases(new ArrayList<>(List.of(linkedUseCase)));

        when(crcRepo.findById(1)).thenReturn(crc);
        when(useCaseRepo.findByProjectId(1))
                .thenReturn(List.of(linkedUseCase, unlinkedUseCase));

        List<UseCase> result = crcService.getUnlinkedUseCases(1, 1);

        assertEquals(1, result.size());
        assertEquals("Register", result.get(0).getName());

        verify(crcRepo).findById(1);
        verify(useCaseRepo).findByProjectId(1);
    }

    @Test
    void testLinkCrcToUseCase() {
        CRC crc = new CRC();
        crc.setUseCases(new ArrayList<>());

        UseCase useCase = new UseCase();
        useCase.setName("Login");

        when(crcRepo.findById(1)).thenReturn(crc);
        when(useCaseRepo.findById(2)).thenReturn(useCase);

        crcService.linkCrcToUseCase(2, 1);

        assertEquals(1, crc.getUseCases().size());
        assertEquals("Login", crc.getUseCases().get(0).getName());

        verify(crcRepo).findById(1);
        verify(useCaseRepo).findById(2);
        verify(crcRepo).save(crc);
    }

    @Test
    void testLinkedUseCases() {
        UseCase useCase = new UseCase();
        useCase.setName("Login");

        CRC crc = new CRC();
        crc.setUseCases(new ArrayList<>(List.of(useCase)));

        when(crcRepo.findById(1)).thenReturn(crc);

        List<UseCase> result = crcService.linkedUseCases(1);

        assertEquals(1, result.size());
        assertEquals("Login", result.get(0).getName());

        verify(crcRepo).findById(1);
    }
}
