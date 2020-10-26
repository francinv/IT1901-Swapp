package swapp.json;

import java.io.IOException;
import java.util.Iterator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import swapp.core.Ad;
import swapp.core.User;

public class UserDeserializer extends JsonDeserializer<User> {

	ObjectMapper mapper = new ObjectMapper();

	@Override
	public User deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
		JsonNode node = parser.getCodec().readTree(parser);
		return deserialize(node);
	}
	
	public User deserialize(JsonNode node) {
		User user = new User(node.get("name").asText(), node.get("email").asText(), node.get("password").asText());
		Iterator<JsonNode> adNode = node.get("ads").elements();
		adNode.forEachRemaining(jsonNode -> {
			try {
				String title = jsonNode.get("title").asText();
				String textBody = jsonNode.get("textbody").asText();
				Ad.Category category = mapper.treeToValue(jsonNode.get("category"), Ad.Category.class);
				Ad.Status status = mapper.treeToValue(jsonNode.get("status"), Ad.Status.class);
				long time = jsonNode.get("date").asLong();
				Ad ad = new Ad(title, user, textBody, category, status, time);
				user.getUserAds().add(ad);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});
		return user;
	}
}
