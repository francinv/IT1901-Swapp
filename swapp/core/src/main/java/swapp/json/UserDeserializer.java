package swapp.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import swapp.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

	@Override
	public User deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		User user = new User(node.get("name").asText(), node.get("email").asText(), node.get("password").asText());
		return user;
	}
	
	public User deserialize(JsonNode node) {
		return new User(node.get("name").asText(), node.get("email").asText(), node.get("password").asText());
	}

}
