package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.model.User;
import java.util.Optional;

public interface userRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
