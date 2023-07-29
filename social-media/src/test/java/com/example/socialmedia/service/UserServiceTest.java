package com.example.socialmedia.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.socialmedia.model.User;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void emailMatchesTestWithValidEmails() {
        String email1 = "username@domain.com";
        String email2 = "user.name@domain.com";
        String email3 = "user-name@domain.com";
        String email4 = "username@domain.co.in";
        String email5 = "user_name@domain.com";

        assertTrue(service.emailMatches(email1));
        assertTrue(service.emailMatches(email2));
        assertTrue(service.emailMatches(email3));
        assertTrue(service.emailMatches(email4));
        assertTrue(service.emailMatches(email5));
    }

    @Test
    public void emailMatchesTestWithInvalidEmails() {
        String email1 = "";
        String email2 = "username.@domain.com";
        String email3 = ".user.name@domain.com";
        String email4 = "user-name@domain.com.";
        String email5 = "username@.com";

        assertFalse(service.emailMatches(email1));
        assertFalse(service.emailMatches(email2));
        assertFalse(service.emailMatches(email3));
        assertFalse(service.emailMatches(email4));
        assertFalse(service.emailMatches(email5));
    }

    @Test
    public void savingValidUser() {
        String name = "Masha";
        String email = "masha@gmail.com";
        User user = new User(name, email);

        service.saveUser(user);
    }

    @Test
    public void savingUserWithNullName() {
        String name = null;
        String email = "mitko@gmail.com";
        User user = new User(name, email);

        try {
            service.saveUser(user);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void savingUserWithNullEmail() {
        String name = "Mitko";
        String email = null;
        User user = new User(name, email);

        try {
            service.saveUser(user);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void savingUserWithEmptyName() {
        String name = "";
        String email = "mitko@gmail.com";
        User user = new User(name, email);

        try {
            service.saveUser(user);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void savingUserWithEmptyEmail() {
        String name = "Mitko";
        String email = "";
        User user = new User(name, email);

        try {
            service.saveUser(user);

            assertTrue(false);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void deleteUserTest() {
        Long userID = 1l;

        service.deleteUser(userID);
    }
}
