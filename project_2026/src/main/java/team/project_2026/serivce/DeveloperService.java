package team.project_2026.serivce;

import team.project_2026.domain.Developer;

public interface DeveloperService {
    public boolean isDeveloperPresent(Developer developer);
    public void saveDeveloper(Developer developer);
    public Developer findByUsername(String username);
}
