package team.project_2026.diagram;

import team.project_2026.model.UseCase;
import java.util.List;

public abstract class AbstractUseCaseDiagramTemplate implements UseCaseDiagramStrategy {

    @Override
    public String generate(List<UseCase> useCases) {
        StringBuilder script = new StringBuilder();

        generateHeader(script);
        generateBody(script, useCases);
        generateFooter(script);

        return script.toString();
    }

    protected abstract void generateHeader(StringBuilder script);

    protected abstract void generateBody(StringBuilder script, List<UseCase> useCases);

    protected abstract void generateFooter(StringBuilder script);
}
