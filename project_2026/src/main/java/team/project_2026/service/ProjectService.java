package team.project_2026.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.repository.projectRepo;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    private projectRepo projectRepo;

    public void createProject(Project project, User user) {
        project.setUserId(user);
        projectRepo.save(project);
    }

    public List<Project> getProjectsByUser(User user) {
        return projectRepo.findProjectsByUserId(user.getId());
    }

    public void deleteById(Integer id){
        projectRepo.deleteById(id);
    }

    public Project getById(Integer id){
        return projectRepo.getById(id);
    }

    public void addCollaborator(Project project, User user) {

        if (!project.getCollaborators().contains(user)) {
            project.getCollaborators().add(user);
            projectRepo.save(project);
        }
    }
}
