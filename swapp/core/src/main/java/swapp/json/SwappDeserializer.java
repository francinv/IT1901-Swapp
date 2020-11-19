package swapp.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import swapp.core.Swapp;
import swapp.core.User;

import java.io.IOException;

public class SwappDeserializer extends JsonDeserializer<Swapp> {

  private final UserDeserializer userDeserializer = new UserDeserializer();

  @Override
  public Swapp deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    JsonNode nodes = parser.getCodec().readTree(parser);
    Swapp swapp = new Swapp();
    for (JsonNode userNode : nodes) {
      User user = userDeserializer.deserialize(userNode);
      swapp.add(user);
    }
    return swapp;
  }

}
