package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import team.project_2026.model.User;

import java.util.List;
import java.util.Optional;

public interface userRepo extends JpaRepository<User, Long> {
    User findById(int id);
    Optional<User> findByUsername(String username);
    @Query("""
SELECT u FROM User u
WHERE u.id NOT IN (
    SELECT u2.id FROM Project p
    JOIN p.collaborators u2
    WHERE p.id = :projectId
)
""")
    List<User> findUsersNotInProject(@Param("projectId") int projectId);

}
