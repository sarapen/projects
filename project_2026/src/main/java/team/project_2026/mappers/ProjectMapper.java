package team.project_2026.mappers;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.domain.Project;

public interface ProjectMapper extends JpaRepository<Project, Long> {
}