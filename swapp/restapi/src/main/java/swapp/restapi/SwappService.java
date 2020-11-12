package swapp.restapi;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swapp.core.AbstractSwapp;
import swapp.core.SwappModel;

@Path(SwappService.SWAPP_MODEL_SERVICE_PATH)
public class SwappModelService {

    public static final String SWAPP_MODEL_SERVICE_PATH = "swapp";

    private static final Logger LOG = LoggerFactory.getLogger(SwappModelService.class);

    @Inject
    private SwappModel swappModel;

    /**
     * The root resource, i.e. /swapp
     *
     * @return the SwappModel
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SwappModel getSwappModel() {
        return swappModel;
    }

    /**
     * Returns the Swap with the provided name
     * (as a resource to support chaining path elements).
     * This supports all requests referring to Swapp by name.
     * Note that the Swapp needn't exist, since it can be a PUT.
     *
     * @param name the name of the swapp list
     */
    @Path("/{name}")
    public SwappResource getSwapp(@PathParam("name") String name) {
        AbstractSwapp swapp = getSwappModel().getSwapp(name);
        LOG.debug("Sub-resource for Swapp " + name + ": " + swapp);
        return new SwappResource(swappModel, name, swapp);
    }
}
