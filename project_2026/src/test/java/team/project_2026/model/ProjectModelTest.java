package team.project_2026.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectModelTest {

    @Test
    void testProjectFields() {
        Project project = new Project();
        User user = new User();

        project.setId(1);
        project.setName("My Project");
        project.setUserId(user);

        assertEquals(1, project.getId());
        assertEquals("My Project", project.getName());
        assertEquals(user, project.getUserId());
    }
}
