package team.project_2026.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.model.UseCase;
import team.project_2026.repository.useCaseRepo;
import team.project_2026.repository.projectRepo;
import java.util.List;

@Service
@Transactional
public class UseCaseService {

    @Autowired
    private useCaseRepo useCaseRepo;

    @Autowired
    private projectRepo projectRepo;

    public void create(UseCase useCase, Integer projectId, User user){
        Project project = projectRepo.findProjectById(projectId);
        useCase.setProject(project);
        useCaseRepo.save(useCase);
    }

    public List<UseCase> getUseCaseByProjectId(Integer projectId){
        return useCaseRepo.findUseCaseByProjectId(projectId);
    }

    public void deleteById(Integer useCaseId){
        useCaseRepo.deleteById(useCaseId);
    }

    public UseCase findById(Integer useCaseId){
        return useCaseRepo.findById(useCaseId);
    }
}
