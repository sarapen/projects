package team.project_2026.diagram;

import team.project_2026.model.UseCase;
import java.util.List;

public class NomnomlUseCaseDiagramStrategy extends AbstractUseCaseDiagramTemplate {

    @Override
    protected void generateHeader(StringBuilder script) {
        script.append("#direction: right\n\n");
    }

    @Override
    protected void generateBody(StringBuilder script, List<UseCase> useCases) {
        for (UseCase uc : useCases) {
            String actor = uc.getActors().trim();

            script.append("[<actor> ").append(actor).append("]\n");
            script.append("[(").append(uc.getName().trim()).append(")]\n");
            script.append("[<actor> ").append(actor).append("] -> ")
                    .append("[(").append(uc.getName().trim()).append(")]\n\n");
        }
    }

    @Override
    protected void generateFooter(StringBuilder script) {
        // Nomnoml does not need footer
    }
}