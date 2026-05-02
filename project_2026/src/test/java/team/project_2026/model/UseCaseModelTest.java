package team.project_2026.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UseCaseModelTest {

    @Test
    void testUseCaseFields() {
        UseCase useCase = new UseCase();
        Project project = new Project();

        useCase.setId(1);
        useCase.setProject(project);
        useCase.setName("Login");
        useCase.setActors("User");
        useCase.setPreconditions("User exists");
        useCase.setMainFlow("User logs in");
        useCase.setPostconditions("User authenticated");

        assertEquals(1, useCase.getId());
        assertEquals(project, useCase.getProject());
        assertEquals("Login", useCase.getName());
        assertEquals("User", useCase.getActors());
        assertEquals("User exists", useCase.getPreconditions());
        assertEquals("User logs in", useCase.getMainFlow());
        assertEquals("User authenticated", useCase.getPostconditions());
    }

    @Test
    void testCrcRelation() {
        UseCase useCase = new UseCase();
        CRC crc = new CRC();

        useCase.setCrcCards(new ArrayList<>());
        crc.setUseCases(new ArrayList<>());

        useCase.addCrc(crc);

        assertEquals(1, useCase.getCrcCards().size());
        assertEquals(1, crc.getUseCases().size());
    }
}
