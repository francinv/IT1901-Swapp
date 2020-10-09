package swapp.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

import swapp.core.Swapp;
import swapp.core.User;
import swapp.core.AdList;

public class SwappModule extends Module {

	private final SimpleSerializers serializers = new SimpleSerializers();
	private final SimpleDeserializers deserializers  = new SimpleDeserializers();
	
	public SwappModule() {
		serializers.addSerializer(Swapp.class, new SwappSerializer());
        serializers.addSerializer(User.class, new UserSerializer());
        serializers.addSerializer(AdList.class, new AdListSerializer());
		deserializers.addDeserializer(Swapp.class, new SwappDeserializer());
        deserializers.addDeserializer(User.class, new UserDeserializer());
        deserializers.addDeserializer(AdList.class, new AdListDeserializer());
	}
	
	@Override
	public String getModuleName() {
		return "SwappModule";
	}

	@Override
	public void setupModule(SetupContext context) {
		context.addSerializers(serializers);
		context.addDeserializers(deserializers);
	}

	@Override
	public Version version() {
		return Version.unknownVersion();
	}
	
}
