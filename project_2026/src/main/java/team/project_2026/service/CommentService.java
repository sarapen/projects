package team.project_2026.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.repository.commentRepo;
import team.project_2026.model.Comment;
import team.project_2026.repository.projectRepo;
import team.project_2026.repository.userRepo;

import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private commentRepo commentRepo;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private projectRepo projectRepo;

    public List<Comment> getCommentByProject(int projectId){
        return commentRepo.getCommentByProjectId(projectId);
    }

    public void addComment(int projectId, String text) {

        String username = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username)
                );

        Project project = projectRepo.getById(projectId);

        Comment comment = new Comment();

        comment.setUser(user);
        comment.setProject(project);
        comment.setComment(text);

        commentRepo.save(comment);
    }
}