package team.project_2026.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CrcModelTest {

    @Test
    void testCrcFields() {
        CRC crc = new CRC();
        Project project = new Project();

        crc.setId(1);
        crc.setProject(project);
        crc.setClassName("User");
        crc.setResponsibilities("login, logout");
        crc.setCollaborations("Project");

        assertEquals(1, crc.getId());
        assertEquals(project, crc.getProject());
        assertEquals("User", crc.getClassName());
        assertEquals("login, logout", crc.getResponsibilities());
        assertEquals("Project", crc.getCollaborations());
    }

    @Test
    void testUseCaseRelation() {
        CRC crc = new CRC();
        UseCase useCase = new UseCase();

        crc.setUseCases(new ArrayList<>());
        useCase.setCrcCards(new ArrayList<>());

        crc.addUseCase(useCase);

        assertEquals(1, crc.getUseCases().size());
        assertEquals(1, useCase.getCrcCards().size());
    }
}
