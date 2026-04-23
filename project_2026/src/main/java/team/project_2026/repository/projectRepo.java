package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import java.util.Optional;
import java.util.List;


public interface projectRepo extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
    void deleteById(Integer id);
    Project getById(Integer id);
    Project findProjectById(Integer projectId);
}
