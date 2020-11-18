package swapp.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    User user1;
    User user2;



    /**
     * Creates a User object before every test
     */
    @BeforeEach
    public void setUpUser() throws Exception {
        this.user1 = new User("name", "ggg@gmail.com", "Password123");
        this.user2 = new User("testuser", "albatross@gmail.com", "Password123");
    }
    /**
     * tests that User objects are created correctly
     */
    @Test
    public void testCreateUser() {
        assertEquals("name", user1.getName());
        assertEquals("ggg@gmail.com", user1.getEmail());
        assertEquals("Password123", user1.getPassword());
        assertEquals(0, user1.getUserAds().size());
    }

    /**
     * tests that the User class' setters work as intended
     */
    @Test
    public void testSetters() {
        this.user1.setName("nam");
        this.user1.setEmail("gab@gmail.com");
        this.user1.setPassword("Password1234");
        assertEquals("nam", this.user1.getName());
        assertEquals("gab@gmail.com", this.user1.getEmail());
        assertEquals("Password1234", this.user1.getPassword());
    }

    /**
     * tests that the User class' toString() method works correctly
     */
    @Test
    public void testToString() {
        assertEquals("[NAME: name, EMAIL: ggg@gmail.com]", this.user1.toString());
    }

    @Test
    public void testEquality() {
        assert this.user1.equals(user1);
        assert ! this.user1.equals(user2);
        assert this.user1.equals(new User("name", "ggg@gmail.com", "Password123"));
        assert ! this.user1.equals(new User("name ", "ggg@gmail.com", "Password123"));
        assert this.user1.equals(new User("NAME", "ggg@gmail.com", "Password123"));

        assert this.user1.hashCode() != user2.hashCode();
        assert this.user1.hashCode() == new User("NAME", "ggg@gmail.com", "Password123").hashCode();

    }
}