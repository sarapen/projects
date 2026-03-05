package team.project_2026.serivce;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import team.project_2026.domain.Developer;
import team.project_2026.mappers.DeveloperMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class DeveloperServiceImpl implements DeveloperService, UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DeveloperMapper developerMapper;

    @Override
    public void saveDeveloper(Developer developer) {
        String encodedPassword = bCryptPasswordEncoder.encode(developer.getPassword());
        developer.setPassword(encodedPassword);
        developerMapper.save(developer);
    }

    @Override
    public boolean isDeveloperPresent(Developer developer) {
        Developer storedDeveloper = developerMapper.findByUsername(developer.getUsername());
        return storedDeveloper != null;
    }

    @Override
    public Developer findByUsername(String username) {
        return developerMapper.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return developerMapper.findByUsername(username);
    }

}
