package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import java.util.Optional;
import java.util.List;


public interface projectRepo extends JpaRepository<Project, Long> {
    List<Project> findByUser(User user);
    void deleteById(Integer id);
    Project getById(Integer id);
    Project findProjectById(Integer projectId);

    @Query("""
SELECT DISTINCT p
FROM Project p
LEFT JOIN p.collaborators c
WHERE p.user.id = :userId
   OR c.id = :userId
""")
    List<Project> findProjectsByUserId(@Param("userId") int userId);
}
