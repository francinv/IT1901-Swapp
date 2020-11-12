package swapp.restapi;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swapp.core.AbstractSwapp;
import swapp.core.Swapp;
import swapp.core.SwappModel;

/**
 * Used for all requests referring to Ad by name.
 */
public class SwappResource {

    private static final Logger LOG = LoggerFactory.getLogger(SwappResource.class);

    private final SwappModel swappModel;
    private final String name;
    private final AbstractSwapp swapp;

    /**
     * Initializes this SwappResource with appropriate context information.
     * Each method will check and use what it needs.
     *
     * @param swappModel the SwappModel, needed for DELETE and rename
     * @param name the ad name, needed for most requests
     * @param swapp the Swapp, or null, needed for PUT
     */
    public SwappResource(SwappModel swappModel, String name, AbstractSwapp swapp) {
        this.swappModel = swappModel;
        this.name = name;
        this.swapp = swapp;
    }

    private void checkSwapp() {
        if (this.swapp == null) {
            throw new IllegalArgumentException("No Swapp named \"" + name + "\"");
        }
    }

    /**
     * Gets the corresponding Swapp.
     *
     * @return the corresponding Swapp
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public AbstractSwapp getSwapp() {
        checkSwapp();
        LOG.debug("getSwapp({})", name);
        return this.swapp;
    }

    /**
     * Replaces or adds a Ad.
     *
     * @param swappArg the swapp to add
     * @return true if it was added, false if it replaced
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean putSwapp(AbstractSwapp swappArg) {
        LOG.debug("putSwapp({})", swappArg);
        return this.swappModel.putSwapp(swappArg) == null;
    }

    /**
     * Adds a Ad with the given name, if it doesn't exist already.
     *
     * @return true if it was added, false if it replaced
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public boolean putSwapp() {
        return putSwapp(new Swapp(name));
    }

    /**
     * Renames the Ad.
     *
     * @param newName the new name
     */
    @POST
    @Path("/rename")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean renameSwapp(@QueryParam("newName") String newName) {
        checkSwapp();
        if (this.swappModel.getSwapp(newName) != null) {
            throw new IllegalArgumentException("A ad named \"" + newName + "\" already exists");
        }
        this.swapp.setName(newName);
        return true;
    }

    /**
     * Removes the TodoList.
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public boolean removeSwapp() {
        checkSwapp();
        this.swappModel.removeswapp(this.swapp);
        return true;
    }
}

