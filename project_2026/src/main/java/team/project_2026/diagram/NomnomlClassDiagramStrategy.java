package team.project_2026.diagram;

import team.project_2026.model.CRC;
import java.util.List;

public class NomnomlClassDiagramStrategy extends AbstractClassDiagramTemplate {

    @Override
    protected void generateHeader(StringBuilder script) {
        // Nomnoml does not need header
    }

    @Override
    protected void generateClasses(StringBuilder script, List<CRC> crcCards) {
        for (CRC crc : crcCards) {
            script.append("[")
                    .append(crc.getClassName().trim())
                    .append("|")
                    .append(crc.getResponsibilities().trim())
                    .append("]\n");
        }

        script.append("\n");
    }

    @Override
    protected void generateRelations(StringBuilder script, List<CRC> crcCards) {
        for (CRC crc : crcCards) {
            if (crc.getCollaborations() != null && !crc.getCollaborations().isBlank()) {
                String[] collaborations = crc.getCollaborations().split(",");

                for (String collaboration : collaborations) {
                    script.append("[")
                            .append(crc.getClassName().trim())
                            .append("] -> [")
                            .append(collaboration.trim())
                            .append("]\n");
                }
            }
        }
    }

    @Override
    protected void generateFooter(StringBuilder script) {
        // Nomnoml does not need footer
    }
}