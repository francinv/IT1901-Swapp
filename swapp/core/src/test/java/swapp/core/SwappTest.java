package swapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class SwappTest {

    Swapp swapp;
    User user = new User("name", "ggg@gmail.com", "Password123");

    public void printInfo() {
        System.out.println("Test coverage report can be found in target/site/jacoco/index.html");
        System.out.println("These tests check that Swapp objects and their methods work as intented");
    }

    @BeforeEach
    public void setUpSwapp() {
        this.swapp = new Swapp();
    }

    @Test
    public void testAddandRemove() {
        assertTrue(this.swapp.add(user));
        assertFalse(this.swapp.add(user));
        assertTrue(this.swapp.remove(user));
        assertFalse(this.swapp.remove(user));
    }
    @Test
    public void testCreateUser() {
        assertFalse(this.swapp.createUser("name", "XD", "Password123"));
        assertTrue(this.swapp.createUser("name", "ggg@gmail.com", "Password123"));
    }
    @Test
    public void testGetUserLogin() {
        this.swapp.add(user);
        assertEquals(user, this.swapp.getUserLogin(user.getEmail(), user.getPassword()));
    }
}