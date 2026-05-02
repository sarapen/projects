package team.project_2026.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project_2026.diagram.ClassDiagramStrategy;
import team.project_2026.diagram.DiagramFactory;
import team.project_2026.diagram.UseCaseDiagramStrategy;
import team.project_2026.model.CRC;
import team.project_2026.model.UseCase;

import java.util.List;

@Service
public class DiagramService {

    @Autowired
    private UseCaseService useCaseService;

    @Autowired
    private CrcService crcService;

    public String generateUseCaseDiagram(Integer projectId, String tool) {
        List<UseCase> useCases = useCaseService.getUseCaseByProjectId(projectId);

        UseCaseDiagramStrategy strategy =
                DiagramFactory.createUseCaseStrategy(tool);

        return strategy.generate(useCases);
    }

    public String generateClassDiagram(Integer projectId, String tool) {
        List<CRC> crcCards = crcService.getCrcByProjectId(projectId);

        ClassDiagramStrategy strategy =
                DiagramFactory.createClassStrategy(tool);

        return strategy.generate(crcCards);
    }
}
