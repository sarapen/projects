package team.project_2026.mappers;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import team.project_2026.domain.Developer;

@Repository
public interface DeveloperMapper extends JpaRepository<Developer, String> {
    Developer findByUsername(String username);
}