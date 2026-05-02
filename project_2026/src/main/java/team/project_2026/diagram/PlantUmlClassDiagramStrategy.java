package team.project_2026.diagram;

import team.project_2026.model.CRC;
import java.util.List;

public class PlantUmlClassDiagramStrategy extends AbstractClassDiagramTemplate {

    @Override
    protected void generateHeader(StringBuilder script) {
        script.append("@startuml\n\n");
    }

    @Override
    protected void generateClasses(StringBuilder script, List<CRC> crcCards) {
        for (CRC crc : crcCards) {
            script.append("class ").append(crc.getClassName().trim()).append(" {\n");
            script.append("  ").append(crc.getResponsibilities().trim()).append("\n");
            script.append("}\n\n");
        }
    }

    @Override
    protected void generateRelations(StringBuilder script, List<CRC> crcCards) {
        for (CRC crc : crcCards) {
            if (crc.getCollaborations() != null && !crc.getCollaborations().isBlank()) {
                String[] collaborations = crc.getCollaborations().split(",");

                for (String collaboration : collaborations) {
                    script.append(crc.getClassName().trim())
                            .append(" --> ")
                            .append(collaboration.trim())
                            .append("\n");
                }
            }
        }
    }

    @Override
    protected void generateFooter(StringBuilder script) {
        script.append("\n@enduml");
    }
}
