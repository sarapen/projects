package team.project_2026.serivce;

import org.springframework.stereotype.Service;
import team.project_2026.domain.Project;
import team.project_2026.mappers.ProjectMapper;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectMapper mapper;

    public ProjectServiceImpl(ProjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Project> findAll() {
        return mapper.findAll();
    }

    @Override
    public Project save(Project project) {
        return mapper.save(project);
    }

    @Override
    public void delete(Long id) {
        mapper.deleteById(id);
    }
}