package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.model.Comment;
import team.project_2026.model.Project;

import java.util.List;

public interface commentRepo extends JpaRepository<Comment, Long> {
    List<Comment> getCommentByProjectId(int projectId);
}
