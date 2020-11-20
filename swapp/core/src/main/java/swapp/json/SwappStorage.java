package swapp.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import swapp.core.Swapp;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class SwappStorage {

  private ObjectMapper objectMapper;
  private final String pathToSwapp;

  public SwappStorage() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new SwappModule());
    this.pathToSwapp = "swapp.json";
  }

  public Swapp read(Reader reader) throws IOException {
    return objectMapper.readValue(reader, Swapp.class);
  }

  public void write(Swapp swapp, Writer writer) throws IOException {
    objectMapper.writerWithDefaultPrettyPrinter().writeValue(writer, swapp);
  }

  public String getPathToSwapp() {
    return pathToSwapp;
  }

}
