package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import team.project_2026.model.Comment;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.repository.commentRepo;
import team.project_2026.repository.projectRepo;
import team.project_2026.repository.userRepo;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CommentServiceTest {

    @Mock
    private commentRepo commentRepo;

    @Mock
    private userRepo userRepo;

    @Mock
    private projectRepo projectRepo;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddComment() {

        User user = new User();
        user.setUsername("nikos");

        Project project = new Project();

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        "nikos",
                        null,
                        List.of()
                )
        );

        when(userRepo.findByUsername("nikos"))
                .thenReturn(Optional.of(user));

        when(projectRepo.getById(1))
                .thenReturn(project);

        commentService.addComment(1, "test comment");

        verify(commentRepo).save(any(Comment.class));
    }

    @Test
    void testGetCommentsByProject() {

        Comment comment = new Comment();

        when(commentRepo.getCommentByProjectId(1))
                .thenReturn(List.of(comment));

        List<Comment> result =
                commentService.getCommentByProject(1);

        assertEquals(1, result.size());

        verify(commentRepo).getCommentByProjectId(1);
    }
}
