package team.project_2026.diagram;

public class DiagramFactory {

    public static UseCaseDiagramStrategy createUseCaseStrategy(String type) {
        if ("NOMNOML".equalsIgnoreCase(type)) {
            return new NomnomlUseCaseDiagramStrategy();
        }

        return new PlantUmlUseCaseDiagramStrategy();
    }

    public static ClassDiagramStrategy createClassStrategy(String type) {
        if ("NOMNOML".equalsIgnoreCase(type)) {
            return new NomnomlClassDiagramStrategy();
        }

        return new PlantUmlClassDiagramStrategy();
    }
}