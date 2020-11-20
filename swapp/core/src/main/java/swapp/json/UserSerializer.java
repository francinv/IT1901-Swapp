package swapp.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import swapp.core.Ad;
import swapp.core.User;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

  @Override
  public void serialize(User user, JsonGenerator gen, SerializerProvider ser) throws IOException {
    gen.writeStartObject();
    gen.writeStringField("name", user.getName());
    gen.writeStringField("email", user.getEmail());
    gen.writeStringField("password", user.getPassword());
    gen.writeFieldName("ads");
    gen.writeStartArray();
    for (Ad ad : user.getUserAds()) {
      gen.writeStartObject();
      gen.writeStringField("title", ad.getTitle());
      gen.writeStringField("textbody", ad.getTextBody());
      gen.writeObjectField("category", ad.getCategory());
      gen.writeObjectField("status", ad.getStatus());
      gen.writeFieldName("date");
      gen.writeNumber(ad.getTime());
      gen.writeEndObject();
    }
    gen.writeEndArray();
    gen.writeEndObject();
  }

}
