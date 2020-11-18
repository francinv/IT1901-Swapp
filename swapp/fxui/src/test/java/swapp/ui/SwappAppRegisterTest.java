package swapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwappAppRegisterTest extends AbstractTestFxController {

  private static final String EMPTY_STRING = "";
  private TextField registerNameField;
  private TextField registerEmailField;
  private PasswordField registerPasswordField;
  private Button registerButton;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.REGISTER);
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

  @AfterEach
  public void clearRegisterFields() {
    registerNameField.clear();
    registerEmailField.clear();
    registerPasswordField.clear();
  }

  private void writeInRegisterFields(String name, String email, String pwd) {
    clickOn(registerNameField).write(name);
    clickOn(registerEmailField).write(email);
    clickOn(registerPasswordField).write(pwd);
  }
}
