package team.project_2026.diagram;

import team.project_2026.model.CRC;
import java.util.List;

public abstract class AbstractClassDiagramTemplate implements ClassDiagramStrategy {

    @Override
    public String generate(List<CRC> crcCards) {
        StringBuilder script = new StringBuilder();

        generateHeader(script);
        generateClasses(script, crcCards);
        generateRelations(script, crcCards);
        generateFooter(script);

        return script.toString();
    }

    protected abstract void generateHeader(StringBuilder script);

    protected abstract void generateClasses(StringBuilder script, List<CRC> crcCards);

    protected abstract void generateRelations(StringBuilder script, List<CRC> crcCards);

    protected abstract void generateFooter(StringBuilder script);
}
