package swapp.ui;

public class SwappApp extends AbstractSwappApp {

  public SwappAccess getSwappAccess() {
    LocalSwappAccess localSwappAccess = new LocalSwappAccess(loadSwapp());
    localSwappAccess.setPath(pathToSwapp);
    localSwappAccess.populateAdList();
    return localSwappAccess;
  }

  public static void main(final String[] args) {
    launch(args);
  }
}