package swapp.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class UserValidationTest {

    private UserValidation userValidation;

    @BeforeEach
    public void setUpBeforeEach() {
        List<User> users = Arrays.asList(new User("testname","test@test.com","Password123"));
        this.userValidation = new UserValidation(users);
    }

    @Test
    public void testValidateUser() {
        Assertions.assertTrue(userValidation.validateUser("test", "testmail@test.com", "Test1234"));
        Assertions.assertFalse(userValidation.validateUser("testname", "test@test.com", "Password123"));
    }

    @Test
    public void testNameInUse() {
        Assertions.assertFalse(userValidation.nameInUse("test"));
        Assertions.assertTrue(userValidation.nameInUse("testname"));
        Assertions.assertTrue(userValidation.nameInUse("TestName"));
    }

    @Test
    public void testEmailInUse() {
        Assertions.assertFalse(userValidation.emailInUse("test1@test.com"));
        Assertions.assertTrue(userValidation.emailInUse("test@test.com"));
        Assertions.assertTrue(userValidation.emailInUse("TEST@TEST.com"));
    }

    @Test
    public void testValidEmail() {
        Assertions.assertFalse(userValidation.validEmail("TEST@TEST.COM"));
        Assertions.assertFalse(userValidation.validEmail("testtest.com0"));
        Assertions.assertTrue(userValidation.validEmail("abc@dfgh.ij"));
    }

    @Test
    public void testValidUsername() {
        Assertions.assertFalse(userValidation.validUsername("aa"));
        Assertions.assertTrue(userValidation.validUsername("testname"));
        Assertions.assertTrue(userValidation.validUsername("TESTNAME"));
        Assertions.assertTrue(userValidation.validUsername("test"));
    }

    @Test
    public void testValidPassword() {
        Assertions.assertFalse(userValidation.validPassword("PASSWORD123"));
        Assertions.assertFalse(userValidation.validPassword("password123"));
        Assertions.assertFalse(userValidation.validPassword("Pass123"));
        Assertions.assertTrue(userValidation.validPassword("Password123"));
    }

}
