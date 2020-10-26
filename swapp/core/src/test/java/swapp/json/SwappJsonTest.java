package swapp.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import swapp.core.Ad;
import swapp.core.Swapp;
import swapp.core.User;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SwappJsonTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.registerModule(new SwappModule());
    }

    private User getTestUser() {
        return new User("testname", "test@test.com", "Testpassword123");
    }

    private User getTestUserWithAds() {
        User user = getTestUser();
        user.getUserAds().addAll(List.of(new Ad("test ad", user, "this is a test", Ad.Category.BORROW, Ad.Status.ACTIVE, 0L),
                                         new Ad("another ad", user, "yet another test", Ad.Category.GIFT, Ad.Status.ACTIVE, 0L),
                                         new Ad("last ad", user, "last one", Ad.Category.TRADE, Ad.Status.ACTIVE, 0L)));
        return user;
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
        final String expectedJson = "[{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\",\"ads\":[]}]";
        assertEquals(expectedJson, actualJson);
    }
    
    @Test
    public void testSwappDeserialization() throws JsonProcessingException {
        final String json = "[{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\",\"ads\":[]}]";
        final Swapp swapp = objectMapper.readValue(json, Swapp.class);
        assertEquals(1, swapp.getUserAmount());
        assertEquals(getTestUser(), swapp.getUser("testname"));
    }

    @Test
    public void testUserSerialization() throws JsonProcessingException {
        final String actualJson = objectMapper.writeValueAsString(getTestUser());
        final String expectedJson = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\",\"ads\":[]}";
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testUserSerializationWithAds() throws JsonProcessingException {
        final String actualJson = objectMapper.writeValueAsString(getTestUserWithAds());
        final String expectedJson = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\",\"ads\":[{\"title\":\"test ad\",\"textbody\":\"this is a test\",\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":0},{\"title\":\"another ad\",\"textbody\":\"yet another test\",\"category\":\"GIFT\",\"status\":\"ACTIVE\",\"date\":0},{\"title\":\"last ad\",\"textbody\":\"last one\",\"category\":\"TRADE\",\"status\":\"ACTIVE\",\"date\":0}]}";
        assertEquals(expectedJson, actualJson);
    }

    @Test
    public void testUserDeserialization() throws JsonProcessingException {
        final String json = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\", \"ads\":[]}}";
        final User user = objectMapper.readValue(json, User.class);
        assertEquals(getTestUser().getName(), user.getName());
        assertEquals(getTestUser().getEmail(), user.getEmail());
        assertEquals(getTestUser().getPassword(), user.getPassword());
    }

    @Test
    public void testUserDeserializationWithAds() throws JsonProcessingException {
        final String json = "{\"name\":\"testname\",\"email\":\"test@test.com\",\"password\":\"Testpassword123\",\"ads\":[{\"title\":\"test ad\",\"textbody\":\"this is a test\",\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":0},{\"title\":\"another ad\",\"textbody\":\"test textbody\",\"category\":\"GIFT\",\"status\":\"ACTIVE\",\"date\":0}]}";
        final User user = objectMapper.readValue(json, User.class);
        final List<Ad> adCollection = new ArrayList<>(List.of(new Ad("test ad", getTestUser(), "this is a test", Ad.Category.BORROW, Ad.Status.ACTIVE, 0L), new Ad("another ad", getTestUser(), "test textbody", Ad.Category.GIFT, Ad.Status.ACTIVE, 0L)));
        assertEquals(getTestUser().getName(), user.getName());
        assertEquals(getTestUser().getEmail(), user.getEmail());
        assertEquals(getTestUser().getPassword(), user.getPassword());
        assertTrue(adCollection.containsAll(user.getUserAds()));
    }
}
