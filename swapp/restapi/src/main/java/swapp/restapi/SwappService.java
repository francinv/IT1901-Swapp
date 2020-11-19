package swapp.restapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swapp.core.Swapp;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path(SwappService.SWAPP_MODEL_SERVICE_PATH)
public class SwappService {

  public static final String SWAPP_MODEL_SERVICE_PATH = "swapp";

  private static final Logger LOG = LoggerFactory.getLogger(SwappService.class);

  @Inject
  private Swapp swapp;

  /**
   * The root resource, i.e. /swapp
   *
   * @return the SwappModel
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Swapp getSwapp() {
    return swapp;
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
    Swapp swapp = getSwapp().getswapp(name);
    LOG.debug("Sub-resource for Swapp " + name + ": " + swapp);
    return new SwappResource(swapp, name, swapp);
  }
}
