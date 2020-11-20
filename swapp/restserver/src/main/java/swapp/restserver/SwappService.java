package swapp.restserver;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import swapp.core.Ad;
import swapp.core.Swapp;
import swapp.json.SwappStorage;

@Service
public class SwappService {

  private static final String swappString =
          "[{\"name\":\"Testname\",\"email\":\"test@test.com\","
          + "\"password\":\"Testpass123\",\"ads\":[{\"title\":\"Test ad\",\"textbody\":\"This is a test\","
          + "\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":917543700}]}]";

  private Swapp swapp;

  public SwappService(Swapp swapp) {
    this.swapp = swapp;
  }

  public SwappService() {
    this(getSwappFromStorage());
  }

  public Swapp getSwapp() {
    return swapp;
  }

  private static Swapp getSwappFromStorage() {
    SwappStorage storage = new SwappStorage();
    Swapp swapp = null;
    URL url = SwappService.class.getResource("swapp.json");
    Reader swappReader = null;
    if (url != null) {
      try {
        swappReader = new FileReader(url.getFile(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        e.printStackTrace();
      }

      if (swappReader == null) { // Read a single user with a single ad
        swappReader = new StringReader(swappString);
      }

      try {
        swapp = storage.read(swappReader);
      } catch (IOException e) {
        System.err.println("Unable to read the file from reader");
      } finally {
        try {
          swappReader.close();
        } catch (IOException e) {
          System.err.println("Unable to close reader");
        }
      }

    }

    if (swapp == null) {
      swapp = new Swapp();
      swapp.createUser("Testname", "test@test.com", "Testpass123");
      swapp.getUser("Testname").createAd("Hoppeslott gis bort", "Gratis hoppeslott", Ad.Category.GIFT);
    }

    return swapp;
  }

}
