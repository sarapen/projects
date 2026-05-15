package team.project_2026.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import team.project_2026.service.CommentService;
import team.project_2026.service.UserService;

@Controller
public class commentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/projects/{projectId}/comments/add")
    public String addComment(@PathVariable int projectId,
                             @RequestParam String comment) {

        commentService.addComment(projectId, comment);

        return "redirect:/projects/edit/" + projectId;
    }
}
