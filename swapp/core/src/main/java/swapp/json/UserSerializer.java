package swapp.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import swapp.core.User;

public class UserSerializer extends JsonSerializer<User> {
	
	@Override
	public void serialize(User user, JsonGenerator gen, SerializerProvider ser) throws IOException {
		gen.writeStartObject();
		gen.writeStringField("name", user.getName());
		gen.writeStringField("email", user.getEmail());
		gen.writeStringField("password", user.getPassword());
		gen.writeEndObject();
	}

}
