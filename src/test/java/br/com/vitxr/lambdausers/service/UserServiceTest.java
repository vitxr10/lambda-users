package br.com.vitxr.lambdausers.service;

import br.com.vitxr.lambdausers.dto.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testCreate() {
        User user = new User(1L, "Vitor", "vitor@example.com", "password", 25, 70.5, 1.75);

        Long userId = userService.create(user);

        assertEquals(1L, userId);
        assertFalse(userService.getAll().isEmpty());
        assertEquals("Vitor", userService.getAll().get(0).getName());
    }

    @Test
    void testGetAll() {
        User user1 = new User(1L, "Vitor", "vitor@example.com", "password", 25, 70.5, 1.75);
        User user2 = new User(2L, "Ana", "ana@example.com", "password", 30, 65.0, 1.68);

        userService.create(user1);
        userService.create(user2);

        List<User> users = userService.getAll();

        assertEquals(2, users.size());
        assertEquals("Vitor", users.get(0).getName());
        assertEquals("Ana", users.get(1).getName());
    }

    @Test
    void testGetById_UserExists() {
        User user = new User(1L, "Vitor", "vitor@example.com", "password", 25, 70.5, 1.75);
        userService.create(user);

        Optional<User> foundUser = userService.getById(1L);

        assertTrue(foundUser.isPresent());
        assertEquals("Vitor", foundUser.get().getName());
    }

    @Test
    void testGetById_UserDoesNotExist() {
        Optional<User> foundUser = userService.getById(99L);

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testUpdate_UserExists() {
        User user = new User(1L, "Vitor", "vitor@example.com", "password", 25, 70.5, 1.75);
        userService.create(user);

        User updatedUser = new User(1L, "Updated Vitor", "updated@example.com", "newpassword", 26, 75.0, 1.80);
        Optional<User> result = userService.update(1L, updatedUser);

        assertTrue(result.isPresent());
        assertEquals("Updated Vitor", result.get().getName());
        assertEquals("updated@example.com", result.get().getEmail());
        assertEquals(26, result.get().getAge());
    }

    @Test
    void testUpdate_UserDoesNotExist() {
        User updatedUser = new User(1L, "Updated Vitor", "updated@example.com", "newpassword", 26, 75.0, 1.80);
        Optional<User> result = userService.update(99L, updatedUser);

        assertFalse(result.isPresent());
    }

    @Test
    void testDelete_UserExists() {
        User user = new User(1L, "Vitor", "vitor@example.com", "password", 25, 70.5, 1.75);
        userService.create(user);

        boolean deleted = userService.delete(1L);

        assertTrue(deleted);
        assertTrue(userService.getAll().isEmpty());
    }

    @Test
    void testDelete_UserDoesNotExist() {
        boolean deleted = userService.delete(99L);

        assertFalse(deleted);
    }

}