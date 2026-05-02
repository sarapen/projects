package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import team.project_2026.repository.projectRepo;
import team.project_2026.repository.useCaseRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UseCaseServiceTest {

    @Mock
    private useCaseRepo useCaseRepo;

    @Mock
    private projectRepo projectRepo;

    @InjectMocks
    private UseCaseService useCaseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Project project = new Project();
        User user = new User();

        UseCase useCase = new UseCase();
        useCase.setName("Login");

        when(projectRepo.findProjectById(1)).thenReturn(project);

        useCaseService.create(useCase, 1, user);

        assertEquals(project, useCase.getProject());
        verify(projectRepo).findProjectById(1);
        verify(useCaseRepo).save(useCase);
    }

    @Test
    void testGetUseCaseByProjectId() {
        UseCase useCase = new UseCase();
        useCase.setName("Login");

        when(useCaseRepo.findUseCaseByProjectId(1))
                .thenReturn(List.of(useCase));

        List<UseCase> result = useCaseService.getUseCaseByProjectId(1);

        assertEquals(1, result.size());
        assertEquals("Login", result.get(0).getName());

        verify(useCaseRepo).findUseCaseByProjectId(1);
    }

    @Test
    void testDeleteById() {
        useCaseService.deleteById(1);

        verify(useCaseRepo).deleteById(1);
    }

    @Test
    void testFindById() {
        UseCase useCase = new UseCase();
        useCase.setName("Login");

        when(useCaseRepo.findById(1)).thenReturn(useCase);

        UseCase result = useCaseService.findById(1);

        assertEquals("Login", result.getName());
        verify(useCaseRepo).findById(1);
    }

    @Test
    void testUpdateUser() {
        UseCase existing = new UseCase();
        existing.setName("Old");

        UseCase updated = new UseCase();
        updated.setName("New");
        updated.setActors("User");
        updated.setPreconditions("User has account");
        updated.setMainFlow("User logs in");
        updated.setPostconditions("User is logged in");

        when(useCaseRepo.findById(1)).thenReturn(existing);

        useCaseService.updateUser(1, updated);

        assertEquals("New", existing.getName());
        assertEquals("User", existing.getActors());
        assertEquals("User has account", existing.getPreconditions());
        assertEquals("User logs in", existing.getMainFlow());
        assertEquals("User is logged in", existing.getPostconditions());

        verify(useCaseRepo).findById(1);
        verify(useCaseRepo).save(existing);
    }
}