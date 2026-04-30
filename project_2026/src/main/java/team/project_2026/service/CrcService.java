package team.project_2026.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.project_2026.model.CRC;
import team.project_2026.model.Project;
import team.project_2026.model.UseCase;
import team.project_2026.model.User;
import team.project_2026.repository.crcRepo;
import team.project_2026.repository.projectRepo;
import team.project_2026.repository.useCaseRepo;

import java.util.List;

@Service
@Transactional
public class CrcService {
    @Autowired
    private crcRepo crcRepo;

    @Autowired
    private useCaseRepo useCaseRepo;

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

    public CRC findById(Integer crcId){
        return crcRepo.findById(crcId);
    }

    public void updateCrc(Integer crcId, CRC updatedCRC){
        CRC existingCRC = crcRepo.findById(crcId);
        existingCRC.setClassName(updatedCRC.getClassName());
        existingCRC.setResponsibilities(updatedCRC.getResponsibilities());
        existingCRC.setCollaborations(updatedCRC.getCollaborations());
        crcRepo.save(existingCRC);
    }


    public List<UseCase> getUnlinkedUseCases(int projectId, int crcId) {

        CRC crc = crcRepo.findById(crcId);

        List<UseCase> allUseCases = useCaseRepo.findByProjectId(projectId);

        List<UseCase> linked = crc.getUseCases();

        return allUseCases.stream()
                .filter(uc -> !linked.contains(uc))
                .toList();
    }

    public void linkCrcToUseCase(int useCaseId, int crcId){
        CRC crc = crcRepo.findById(crcId);
        UseCase useCase = useCaseRepo.findById(useCaseId);

        crc.getUseCases().add(useCase);
        crcRepo.save(crc);
    }

    public List<UseCase> linkedUseCases(int crcId){
        CRC crc = crcRepo.findById(crcId);
        return crc.getUseCases();
    }
}