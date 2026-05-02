package team.project_2026.diagram;
import team.project_2026.model.CRC;
import java.util.List;

public interface ClassDiagramStrategy {
    String generate(List<CRC> crcCards);
}