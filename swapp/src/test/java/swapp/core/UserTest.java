package swapp.core;

import org.junit.jupiter.api.Test;

public class UserTest {
    User user;

    @Test
    public void setUp() throws Exception{
        System.out.println("These tests checs that creating users work");
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