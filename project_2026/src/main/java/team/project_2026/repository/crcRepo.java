package team.project_2026.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.project_2026.model.Project;
import team.project_2026.model.CRC;

import java.util.List;

public interface crcRepo extends JpaRepository<CRC, Long> {
    public List<CRC> getCrcByProjectId(Integer projectId);
    public void deleteById(Integer crcId);
}


