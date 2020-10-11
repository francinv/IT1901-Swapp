package swapp.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import swapp.core.Swapp;
import swapp.core.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwappJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new SwappModule());
    }

    private User getTestUser1() {
        return new User("testname", "test@test.com", "Testpassword123");
    }

    private Swapp setUpSwapp() {
        Swapp swapp = new Swapp();
        User user = new User("testname", "test@test.com", "Testpassword123");
        swapp.add(user);
        return swapp;
    }

    @Test
    public void testSwappSerialization() throws JsonProcessingException {
        final String actualJson = objectMapper.writeValueAsString(setUpSwapp());
        final String expectedJson = "[{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\"}]";
        assertEquals(expectedJson, actualJson);
    }

    // har ikke implementert funksjoner som Object.equals(Object o) og Object.hashCode() dermed vil ikke brukere med samme grunnlag
    // være like derfor er dette egentlig det samme som å test userdeserialization
    @Test
    public void testSwappDeserialization() throws JsonProcessingException {
        final String json = "[{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\"}]";
        final Swapp swapp = objectMapper.readValue(json, Swapp.class);
        assertEquals(1, swapp.getUserAmount());
        assertEquals(getTestUser1().getName(), swapp.getUser("testname").getName());
        assertEquals(getTestUser1().getEmail(), swapp.getUser("testname").getEmail());
        assertEquals(getTestUser1().getPassword(), swapp.getUser("testname").getPassword());
    }

    @Test
    public void testUserSerialization() throws JsonProcessingException {
        final String actualJson = objectMapper.writeValueAsString(getTestUser1());
        final String expectedJson = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\"}";
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testUserDeserialization() throws JsonProcessingException {
        final String json = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\"}";
        final User user = objectMapper.readValue(json, User.class);
        assertEquals(getTestUser1().getName(), user.getName());
        assertEquals(getTestUser1().getEmail(), user.getEmail());
        assertEquals(getTestUser1().getPassword(), user.getPassword());
    }

}
