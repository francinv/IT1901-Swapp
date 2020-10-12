package swapp.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import swapp.core.Swapp;
import swapp.core.User;

public class SwappSerializer extends JsonSerializer<Swapp> {

	@Override
	public void serialize(Swapp swapp, JsonGenerator gen, SerializerProvider ser) throws IOException {
		gen.writeStartArray(swapp.getUserAmount());
		for (final User user : swapp.getAccounts()) {
			gen.writeObject(user);
		}
		gen.writeEndArray();
	}

}
