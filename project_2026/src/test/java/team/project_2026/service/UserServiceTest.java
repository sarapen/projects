package team.project_2026.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import team.project_2026.model.User;
import team.project_2026.repository.userRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private userRepo userRepo;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser() {
        User user = new User();
        user.setUsername("nikos");
        user.setPassword("1234");

        when(bCryptPasswordEncoder.encode("1234"))
                .thenReturn("encoded1234");

        userService.registerUser(user);

        assertEquals("encoded1234", user.getPassword());
        verify(bCryptPasswordEncoder).encode("1234");
        verify(userRepo).save(user);
    }

    @Test
    void testFindByUsername() {
        User user = new User();
        user.setUsername("nikos");

        when(userRepo.findByUsername("nikos"))
                .thenReturn(Optional.of(user));

        User result = userService.findByUsername("nikos");

        assertEquals("nikos", result.getUsername());
        verify(userRepo).findByUsername("nikos");
    }

    @Test
    void testFindByUsernameNotFound() {
        when(userRepo.findByUsername("missing"))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> userService.findByUsername("missing")
        );

        assertEquals("User not found: missing", exception.getMessage());
        verify(userRepo).findByUsername("missing");
    }

    @Test
    void testUpdateUser() {
        User existingUser = new User();
        existingUser.setUsername("old");
        existingUser.setEmail("old@email.com");
        existingUser.setPassword("oldpass");

        User updatedUser = new User();
        updatedUser.setUsername("new");
        updatedUser.setEmail("new@email.com");
        updatedUser.setPassword("1234");

        when(userRepo.findByUsername("old"))
                .thenReturn(Optional.of(existingUser));

        when(bCryptPasswordEncoder.encode("1234"))
                .thenReturn("encoded1234");

        userService.updateUser(updatedUser, "old");

        assertEquals("new", existingUser.getUsername());
        assertEquals("new@email.com", existingUser.getEmail());
        assertEquals("encoded1234", existingUser.getPassword());

        verify(userRepo).findByUsername("old");
        verify(bCryptPasswordEncoder).encode("1234");
        verify(userRepo).save(existingUser);
    }
}
