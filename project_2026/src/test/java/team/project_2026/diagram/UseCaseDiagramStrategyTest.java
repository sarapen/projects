package team.project_2026.diagram;

import org.junit.jupiter.api.Test;
import team.project_2026.model.UseCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UseCaseDiagramStrategyTest {

    @Test
    void testPlantUmlGeneration() {
        UseCase uc = new UseCase();
        uc.setId(1);
        uc.setName("Login");
        uc.setActors("User");

        UseCaseDiagramStrategy strategy =
                new PlantUmlUseCaseDiagramStrategy();

        String result = strategy.generate(List.of(uc));

        assertTrue(result.contains("@startuml"));
        assertTrue(result.contains("Login"));
        assertTrue(result.contains("User"));
    }

    @Test
    void testNomnomlGeneration() {
        UseCase uc = new UseCase();
        uc.setId(1);
        uc.setName("Login");
        uc.setActors("User");

        UseCaseDiagramStrategy strategy =
                new NomnomlUseCaseDiagramStrategy();

        String result = strategy.generate(List.of(uc));

        assertTrue(result.contains("[("));
        assertTrue(result.contains("Login"));
    }
}
