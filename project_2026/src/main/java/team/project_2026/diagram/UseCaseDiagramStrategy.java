package team.project_2026.diagram;
import team.project_2026.model.UseCase;
import java.util.List;

public interface UseCaseDiagramStrategy {
    String generate(List<UseCase> useCases);
}
