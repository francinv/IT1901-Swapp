package swapp.restserver;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import swapp.core.AdList;
import swapp.core.Swapp;
import swapp.json.SwappStorage;
import swapp.restapi.SwappModelService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class SwappConfig extends ResourceConfig {

  private Swapp swapp;

  /**
   * Initialize this Swapp.
   *
   * @param Swapp swapp instance to serve
   */
  public SwappConfig(Swapp swapp) {
    setSwapp(swapp);
    register(SwappModelService.class);
    register(SwappModuleObjectProvider.class);
    register(JacksonFeature.class);
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bind(SwappConfig.this.swappModel);
      }
    });
  }

  /**
   * Initialize this SwappConfig with a default SwappModel.
   */
  public SwappConfig() {
    this(createDefaultSwapp);
  }

  public Swapp getSwapp() {
    return Swapp;
  }

  public void setSwapp(Swapp swapp) {
    this.swapp = swapp;
  }

  private static Swapp createDefaultSwapp() {
    SwappStorage swappStorage = new SwappStorage();
    URL url = SwappConfig.class.getResource("default-swappmodel.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return swappStorage.readSwapp(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-swappmodel.json, so rigging SwappModel manually ("
                + e + ")");
      }
    }
    Swapp swapp = new Swapp();
    swapp.addAdList(new AdList("ad1"));
    swapp.addAdList(new AdList("ad2"));
    return swapp;
  }
}
