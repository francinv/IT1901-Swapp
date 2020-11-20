package swapp.ui;

import java.net.URI;
import java.net.URISyntaxException;

public class RemoteSwappApp extends AbstractSwappApp {

  private static final String endpointURI = "http://localhost:8080/swapp/";

  @Override
  public SwappAccess getSwappAccess() {
    RemoteSwappAccess remoteAccess = null;
    try {
      remoteAccess = new RemoteSwappAccess(new URI(endpointURI));
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return remoteAccess;
  }

  public static void main(final String[] args) {
    launch(args);
  }

}
