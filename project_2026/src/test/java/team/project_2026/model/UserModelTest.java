package team.project_2026.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserModelTest {

    @Test
    void testUserFields() {
        User user = new User();

        user.setId(1);
        user.setUsername("nikos");
        user.setEmail("nikos@email.com");
        user.setPassword("1234");

        assertEquals(1, user.getId());
        assertEquals("nikos", user.getUsername());
        assertEquals("nikos@email.com", user.getEmail());
        assertEquals("1234", user.getPassword());
    }
}
