package swapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.Swapp;
import swapp.core.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwappAppRegisterTest extends ApplicationTest {

  private RegisterController controller;
  private static final String EMPTY_STRING = "";
  private TextField registerNameField;
  private TextField registerEmailField;
  private PasswordField registerPasswordField;
  private Button registerButton;
  private Swapp swapp = new Swapp();
  private LocalSwappAccess swappAccess = new LocalSwappAccess(swapp);

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader();
    final RegisterController controller = new RegisterController();
    this.controller = controller;
    swappAccess.setPath(System.getProperty("user.dir") + File.separator + "swapp.json");
    controller.setSwappAccess(swappAccess);
    loader.setController(controller);
    loader.setLocation(getClass().getResource("Register.fxml"));
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  public void initRegisterFields() {
    registerNameField = lookup("#registerNameField").query();
    registerEmailField = lookup("#registerEmailField").query();
    registerPasswordField = lookup("#registerPasswordField").query();
    registerButton = lookup("#registerButton").queryButton();
  }

  @Test
  public void testRegisterUserSuccessfully() {
    final String name = "username";
    final String email = "user@user.com";
    final String password = "Userpwd123";
    writeInRegisterFields(name, email, password);
    clickOn(registerButton);
    User user = controller.swappAccess.getUser("Username");
    assertEquals(user.getName(), name);
    assertEquals(user.getEmail(), email);
    assertEquals(user.getPassword(), password);
  }

  @Test
  public void testRegisterUserEmptyFields() {
    writeInRegisterFields(EMPTY_STRING, EMPTY_STRING, EMPTY_STRING);
    clickOn(registerButton);
    alertDialogPopsUp(AbstractController.ERROR_DIALOG, AbstractController.NAME_FIELD_EMPTY); // ser med en gang at name field er tom og lager en alert box og returnerer
  }

  @Test
  public void testRegisterUserInvalidName() {
    writeInRegisterFields("aa", "testmail@testmail.com", "Pwdtest123");
    clickOn(registerButton);
    alertDialogPopsUp(AbstractController.ERROR_DIALOG, AbstractController.INVALID_USERNAME);
  }

  @Test
  public void testRegisterUserNameInUse() {
    controller.swappAccess.createUser("foobar", "foo@bar.com", "Foobar123");
    writeInRegisterFields("foobar", "bar@foo.com", "Barfoo123");
    clickOn(registerButton);
    alertDialogPopsUp(AbstractController.ERROR_DIALOG, AbstractController.NAME_IN_USE);
  }

  @Test // Usikker på hvor mye dette sier om korrektheten av programmet ettersom at det lastes inn nye fxml filer med nye kontrollere
  public void testLinks() {
    final Hyperlink registerLink = lookup("#registerLink").query();
    clickOn(registerLink);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  private void writeInRegisterFields(String name, String email, String pwd) {
    clickOn(registerNameField).write(name);
    clickOn(registerEmailField).write(email);
    clickOn(registerPasswordField).write(pwd);
  }

  @AfterEach
  public void clearRegisterFields() {
    registerNameField.clear();
    registerEmailField.clear();
    registerPasswordField.clear();
  }

  // Disse to er tatt fra stackoverflow ettersom at det var veldig vanskelig å få tak i alert bokser

  /**
   * Checks the current alert dialog displayed (on the top of the window stack) has the expected contents.
   *
   * From https://stackoverflow.com/a/48654878/8355496
   * Licenced under cc by-sa 3.0 with attribution required https://creativecommons.org/licenses/by-sa/3.0/
   * @param expectedHeader Expected header of the dialog
   * @param expectedContent Expected content of the dialog
   */
  private void alertDialogPopsUp(final String expectedHeader, final String expectedContent) {
    final Stage actualAlertDialog = getTopModalStage();
    Assertions.assertNotNull(actualAlertDialog);

    final DialogPane dialogPane = (DialogPane) actualAlertDialog.getScene().getRoot();
    assertEquals(expectedHeader, dialogPane.getHeaderText());
    assertEquals(expectedContent, dialogPane.getContentText());
  }

  /**
   * Get the top modal window.
   *
   * Adapted from https://stackoverflow.com/a/48654878/8355496
   * Licenced under cc by-sa 3.0 with attribution required https://creativecommons.org/licenses/by-sa/3.0/
   * @return the top modal window
   */
  private Stage getTopModalStage() {
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
}
