package team.project_2026.serivce;

import team.project_2026.domain.Project;
import java.util.List;

public interface ProjectService {

    List<Project> findAll();

    Project save(Project project);

    void delete(Long id);
}