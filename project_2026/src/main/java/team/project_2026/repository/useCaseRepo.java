package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import java.util.Optional;
import java.util.List;


public interface useCaseRepo extends JpaRepository<UseCase, Long> {
    public List<UseCase> findUseCaseByProjectId(Integer projectId);
    public void deleteById(Integer useCaseId);
    public UseCase findById(Integer useCaseId);
}
