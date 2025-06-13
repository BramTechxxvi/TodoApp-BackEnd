package org.bram.data.repositories;

import org.bram.data.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void saveAUser__coustIsOneTest() {
        User user = new User();
        user.setEmail("123@email.com");
        user.setUsername("username");
        user.setPassword("password");
        userRepository.save(user);
        assertNotNull(user.getId());
        assertEquals(1, userRepository.count());
    }

}