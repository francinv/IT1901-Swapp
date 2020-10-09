package swapp.json;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import swapp.core.Swapp;
import swapp.core.User;
import swapp.core.AdList;

public class AdListSerializer extends JsonSerializer<Swapp>{

    @Override
    public void serialize(Swapp swapp, JsonGenerator gen, SerializerProvider ser) throws IOException {
		gen.writeStartArray(swapp.getNumberOfAds());
		for (final AdList adList : swapp.getAd()) {
			gen.writeObject(AdList);
		}
		gen.writeEndArray();
	}
}