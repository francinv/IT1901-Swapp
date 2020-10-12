package swapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    User user;
    public void informationPrint() throws Exception{
        System.out.println("Test coverage report can be found in target/site/jacoco/index.html");
        System.out.println("These tests check that User objects and their methods work as intented");
    }


/**
 * Creates a User object before every test
 */
@BeforeEach    
public void setUpUser() throws Exception {
    this.user = new User("name", "ggg@gmail.com", "Password123");
}
    /**
     * tests that User objects are created correctly
     */
    @Test
    public void testCreateUser() {
        assertEquals("name", user.getName());
        assertEquals("ggg@gmail.com", user.getEmail());
        assertEquals("Password123", user.getPassword());
    }

    /**
     * tests that the User class' setters work as intended
     */
    @Test
    public void testSetters() {
        this.user.setName("nam");
        this.user.setEmail("gab@gmail.com");
        this.user.setPassword("Password1234");
       assertEquals("nam", this.user.getName());
       assertEquals("gab@gmail.com", this.user.getEmail());
       assertEquals("Password1234", this.user.getPassword());  
    }

    /**
     * tests that the User class' toString() method works correctly
     */
    @Test
    public void testToString() {
        assertEquals("[NAME: name, EMAIL: ggg@gmail.com]", this.user.toString());
    }
}