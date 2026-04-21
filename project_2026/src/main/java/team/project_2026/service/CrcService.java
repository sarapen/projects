package team.project_2026.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project_2026.model.CRC;
import team.project_2026.model.Project;
import team.project_2026.model.User;
import team.project_2026.repository.crcRepo;
import team.project_2026.repository.projectRepo;

import java.util.List;

@Service
@Transactional
public class CrcService {
    @Autowired
    private crcRepo crcRepo;

    @Autowired
    private projectRepo projectRepo;

    public void create(CRC crc, Integer projectId, User user) {
        Project project = projectRepo.findProjectById(projectId);
        crc.setProject(project);
        crcRepo.save(crc);
    }

    public List<CRC> getCrcByProjectId(Integer projectId){
       return crcRepo.getCrcByProjectId(projectId);
    }

    public void deleteById(Integer crcId){
        crcRepo.deleteById(crcId);
    }
}