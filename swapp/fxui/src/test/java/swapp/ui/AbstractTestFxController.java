package swapp.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import swapp.core.Swapp;
import swapp.json.SwappModule;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractTestFxController extends ApplicationTest {

  private static final String swappString = "[{\"name\":\"Testname\",\"email\":\"test@test.com\"," +
          "\"password\":\"Testpass123\",\"ads\":[{\"title\":\"Test ad\",\"textbody\":\"This is a test\"," +
          "\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":917543700},{\"title\":\"Another ad\",\"textbody\":\"Test\"," +
          "\"category\":\"GIFT\",\"status\":\"ACTIVE\",\"date\":920543700},{\"title\":\"TEST\",\"textbody\":\"Lorem Ipsum\"," +
          "\"category\":\"TRADE\",\"status\":\"ACTIVE\",\"date\":910543700},{\"title\":\"FOOBAR\",\"textbody\":\"Foobar\"," +
          "\"category\":\"BORROW\",\"status\":\"ACTIVE\",\"date\":932543700}]}]";
  private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new SwappModule());

  protected AbstractController controller;
  protected Swapp swapp;
  protected LocalSwappAccess swappAccess;

  protected FXMLLoader loadFXML(AbstractController.CONTROLLERS type) {
    loadSwappAccess();
    FXMLLoader loader = new FXMLLoader();
    this.controller = type.getControllerInstance();
    swappAccess.setPath(System.getProperty("user.dir") + File.separator + "swapp-test.json");
    this.controller.setSwappAccess(swappAccess);
    loader.setController(this.controller);
    loader.setLocation(AbstractTestFxController.class.getResource(type.getFXMLString()));
    return loader;
  }

  private void loadSwappAccess() {
    try {
      swapp = objectMapper.readValue(swappString, Swapp.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    swappAccess = new LocalSwappAccess(swapp);
    swappAccess.setCurrentUser(swappAccess.getUser("Testname"));
  }

  public void comboboxSelect(int i, ComboBox comboBox) {
    if (i < 1 || i > 3) {
      throw new IllegalArgumentException("i must be in [1, 3]");
    }
    clickOn(comboBox);
    for (int j = 0; j < i; j++) {

      type(KeyCode.DOWN);
    }
    type(KeyCode.ENTER);
    System.out.println(comboBox.getValue());
  }

  // Disse to er tatt fra stackoverflow ettersom at det var veldig vanskelig å få tak i alert bokser

  /**
   * Checks the current alert dialog displayed (on the top of the window stack) has the expected contents.
   * <p>
   * From https://stackoverflow.com/a/48654878/8355496
   * Licenced under cc by-sa 3.0 with attribution required https://creativecommons.org/licenses/by-sa/3.0/
   *
   * @param expectedHeader  Expected header of the dialog
   * @param expectedContent Expected content of the dialog
   */
  protected void alertDialogPopsUp(final String expectedHeader, final String expectedContent) {
    final Stage actualAlertDialog = getTopModalStage();
    Assertions.assertNotNull(actualAlertDialog);

    final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
    assertEquals(expectedHeader, dialogPane.getHeaderText());
    assertEquals(expectedContent, dialogPane.getContentText());
  }

  /**
   * Get the top modal window.
   * <p>
   * Adapted from https://stackoverflow.com/a/48654878/8355496
   * Licenced under cc by-sa 3.0 with attribution required https://creativecommons.org/licenses/by-sa/3.0/
   *
   * @return the top modal window
   */
  protected Stage getTopModalStage() {
    // Get a list of windows but ordered from top[0] to bottom[n] ones.
    // It is needed to get the first found modal window.
    final List<Window> allWindows = new ArrayList<>(new FxRobot().robotContext().getWindowFinder().listWindows());
    Collections.reverse(allWindows);

    return (Stage) allWindows
            .stream()
            .filter(window -> window instanceof Stage)
            .findFirst()
            .orElse(null);
  }

  @AfterAll
  public static void deleteSwapp() {
    Path swapp = FileSystems.getDefault().getPath(System.getProperty("user.dir") + File.separator + "swapp-test.json");
    try {
      Files.deleteIfExists(swapp);
    } catch (IOException ioex) {
      ioex.printStackTrace();
    }
  }
}
