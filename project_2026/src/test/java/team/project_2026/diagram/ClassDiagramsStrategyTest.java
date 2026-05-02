package team.project_2026.diagram;

import org.junit.jupiter.api.Test;
import team.project_2026.model.CRC;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClassDiagramsStrategyTest {

    @Test
    void testPlantUmlClassDiagram() {
        CRC crc = new CRC();
        crc.setClassName("User");
        crc.setResponsibilities("login");
        crc.setCollaborations("Project");

        ClassDiagramStrategy strategy =
                new PlantUmlClassDiagramStrategy();

        String result = strategy.generate(List.of(crc));

        assertTrue(result.contains("@startuml"));
        assertTrue(result.contains("class User"));
    }

    @Test
    void testNomnomlClassDiagram() {
        CRC crc = new CRC();
        crc.setClassName("User");
        crc.setResponsibilities("login");
        crc.setCollaborations("Project");

        ClassDiagramStrategy strategy =
                new NomnomlClassDiagramStrategy();

        String result = strategy.generate(List.of(crc));

        assertTrue(result.contains("[User|login]"));
        assertTrue(result.contains("->"));
    }
}
