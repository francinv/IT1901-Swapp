package swapp.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import swapp.core.AdList;
import swapp.core.Swapp;
import swapp.json.SwappStorage;
import swapp.restapi.SwappModelService;

public class SwappConfig extends ResourceConfig {

    private Swapp swapp;

    /**
     * Initialize this Swapp.
     *
     * @param Swapp swapp instance to serve
     */
    public SwappConfig(SwappModel swappModel) {
        setSwappModel(swappModel);
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
        this(createDefaultSwappModel);
    }

    public SwappModel getSwappModel() {
        return SwappModel;
    }

    public void setSwappModel(SwappModel swappModel) {
        this.swappModel = swappModel;
    }

    private static SwappModel createDefaultSwappModel() {
        SwappStorage swappStorage = new SwappStorage();
        URL url = SwappConfig.class.getResource("default-swappmodel.json");
        if (url != null) {
            try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                return swappStorage.readSwappModel(reader);
            } catch (IOException e) {
                System.out.println("Couldn't read default-todomodel.json, so rigging TodoModel manually ("
                        + e + ")");
            }
        }
        SwappModel swappModel = new SwappModel();
        swappModel.addAdList(new AdList("ad1"));
        swappModel.addAdList(new AdList("ad2"));
        return swappModel;
    }
}
