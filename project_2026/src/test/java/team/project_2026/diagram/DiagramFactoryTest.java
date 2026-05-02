package team.project_2026.diagram;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiagramFactoryTest {

    @Test
    void testCreateUseCaseStrategy_PlantUML() {
        UseCaseDiagramStrategy strategy =
                DiagramFactory.createUseCaseStrategy("PLANTUML");

        assertTrue(strategy instanceof PlantUmlUseCaseDiagramStrategy);
    }

    @Test
    void testCreateUseCaseStrategy_Nomnoml() {
        UseCaseDiagramStrategy strategy =
                DiagramFactory.createUseCaseStrategy("NOMNOML");

        assertTrue(strategy instanceof NomnomlUseCaseDiagramStrategy);
    }

    @Test
    void testCreateClassStrategy_PlantUML() {
        ClassDiagramStrategy strategy =
                DiagramFactory.createClassStrategy("PLANTUML");

        assertTrue(strategy instanceof PlantUmlClassDiagramStrategy);
    }

    @Test
    void testCreateUseCaseStrategy_InvalidDefaultsToPlantUml() {
        UseCaseDiagramStrategy strategy =
                DiagramFactory.createUseCaseStrategy("INVALID");

        assertTrue(strategy instanceof PlantUmlUseCaseDiagramStrategy);
    }

    @Test
    void testCreateClassStrategy_InvalidDefaultsToPlantUml() {
        ClassDiagramStrategy strategy =
                DiagramFactory.createClassStrategy("UNKNOWN");

        assertTrue(strategy instanceof PlantUmlClassDiagramStrategy);
    }

}

