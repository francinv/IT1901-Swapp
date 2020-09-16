package swapp.core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserTest {
    User user;

    @BeforeAll
    public void setUp() throws Exception{
        this.user = new User("name", "email", "password");
    }

    @Test
    public void testCreateUser() {
        try {
            this.user =  new User("name2", "email", "password");
        } 
        catch (Exception e) {
        }
    }

    @Test
    public void testSetName() {
       try {
           user.setName("name675");
       } catch (Exception e) {
       }
    }
}