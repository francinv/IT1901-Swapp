package swapp.ui;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import swapp.core.Ad;
import swapp.core.Swapp;
import swapp.json.SwappStorage;

public class SwappApp extends Application {

  private final static String pathToSwapp = "swapp.json";
  private static final String swappString = "[{\"name\":\"Testname\",\"email\":\"test@test.com\"," +
          "\"password\":\"Testpass123\",\"ads\":[{\"title\":\"Test ad\",\"textbody\":\"This is a test\"," +
          "\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":917543700}]}]";

  @Override
  public void start(final Stage primaryStage) throws Exception {
    FXMLLoader loader = new FXMLLoader();
    LoginController controller =  new LoginController();
    controller.setSwappAccess(getSwappAccess());
    loader.setController(controller);
    loader.setLocation(getClass().getResource("Login.fxml"));
    final Parent parent = loader.load();
    primaryStage.setTitle("SwApp!");
    primaryStage.setScene(new Scene(parent));
    primaryStage.show();
  }

  private Swapp loadSwapp() {
    SwappStorage storage = new SwappStorage();
    Swapp swapp = null;
    Reader swappReader = null;
    if (pathToSwapp != null) { // We'll try to read from the local version of swapp
      try {
        swappReader = new FileReader(Paths.get(pathToSwapp).toFile(), StandardCharsets.UTF_8);
      } catch (IOException e) {
        System.err.println("Unable to get SwApp instance from: " + System.getProperty("user.dir") + "\\" + pathToSwapp + ", creating sample instance");
      }
    }
    if (swappReader == null) { // Read a single user with a single ad
      swappReader = new StringReader(swappString);
    }

    // Now we try reading, add try catch clause for reading
    try {
      swapp = storage.read(swappReader);
    } catch (IOException e) {
      System.err.println("Unable to read the file from reader");
    } finally {
      try {
        swappReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // If swapp is still null at this point we just make a new one
    if (swapp == null) {
      swapp = new Swapp();
      swapp.createUser("Testname", "test@test.com", "Testpass123");
      swapp.getUser("testname").createAd("Test ad", "This is a test", Ad.Category.BORROW);
      System.out.println("Sample SwApp instance created");
    }

    return swapp;
  }

  private SwappAccess getSwappAccess() {
    SwappAccess swappAccess = null;
    if (swappAccess == null) {
      LocalSwappAccess localSwappAccess = new LocalSwappAccess(loadSwapp());
      localSwappAccess.setPath(pathToSwapp);
      localSwappAccess.populateAdList();
      swappAccess = localSwappAccess;
    }
    return swappAccess;
  }

  public static void main(final String[] args) {
    launch(args);
  }
}