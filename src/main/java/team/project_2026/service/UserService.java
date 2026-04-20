package team.project_2026.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import team.project_2026.model.UseCase;
import team.project_2026.repository.userRepo;
import team.project_2026.repository.useCaseRepo;
import team.project_2026.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private userRepo userRepo;

    @Autowired
    private useCaseRepo useCaseRepo;

    public void registerUser(User user) {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepo.save(user);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + username)
                );
    }



}
