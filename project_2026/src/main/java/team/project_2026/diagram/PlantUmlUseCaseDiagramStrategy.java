package team.project_2026.diagram;

import team.project_2026.model.UseCase;
import java.util.List;

public class PlantUmlUseCaseDiagramStrategy extends AbstractUseCaseDiagramTemplate {

    @Override
    protected void generateHeader(StringBuilder script) {
        script.append("@startuml\n");
        script.append("left to right direction\n\n");
    }

    @Override
    protected void generateBody(StringBuilder script, List<UseCase> useCases) {
        for (UseCase uc : useCases) {
            String actor = uc.getActors().trim();

            script.append("actor \"").append(actor).append("\"\n");

            script.append("usecase \"").append(uc.getName().trim()).append("\" as UC")
                    .append(uc.getId()).append("\n");

            script.append("\"").append(actor).append("\" --> UC")
                    .append(uc.getId()).append("\n\n");
        }
    }

    @Override
    protected void generateFooter(StringBuilder script) {
        script.append("@enduml");
    }
}
