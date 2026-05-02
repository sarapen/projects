package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.repository.projectRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private projectRepo projectRepo;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProject() {
        User user = new User();

        Project project = new Project();
        project.setName("Test Project");

        projectService.createProject(project, user);

        assertEquals(user, project.getUserId());
        verify(projectRepo).save(project);
    }

    @Test
    void testGetProjectsByUser() {
        User user = new User();

        Project project = new Project();
        project.setName("Test Project");

        when(projectRepo.findByUser(user))
                .thenReturn(List.of(project));

        List<Project> result = projectService.getProjectsByUser(user);

        assertEquals(1, result.size());
        assertEquals("Test Project", result.get(0).getName());
        verify(projectRepo).findByUser(user);
    }

    @Test
    void testDeleteById() {
        projectService.deleteById(1);

        verify(projectRepo).deleteById(1);
    }

    @Test
    void testGetById() {
        Project project = new Project();
        project.setName("Test Project");

        when(projectRepo.getById(1))
                .thenReturn(project);

        Project result = projectService.getById(1);

        assertEquals("Test Project", result.getName());
        verify(projectRepo).getById(1);
    }
}
