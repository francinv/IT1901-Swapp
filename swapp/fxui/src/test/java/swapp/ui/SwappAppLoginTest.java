package swapp.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.*;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwappAppLoginTest extends AbstractTestFxController {

  private static final String EMPTY_STRING = "";
  private TextField loginEmailField;
  private PasswordField loginPasswordField;
  private Button loginButton;

  @Override
  public void start(final Stage stage) throws Exception {
    FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.LOGIN);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  public void initLoginFields() {
    loginEmailField = lookup("#loginEmailField").query();
    loginPasswordField = lookup("#loginPasswordField").query();
    loginButton = lookup("#loginButton").query();
  }

  @Test void testLoginUser() { // fyfaen den her e støgg
    controller.swappAccess.createUser("foobar", "foo@bar.com", "Foobar123");
    writeInLoginFields("foo@bar.com", "Foobar123");
    clickOn(loginButton);
    Window currentWindow = window(getTopModalStage().getScene()); // gets the current window on top which at this point will be the ad view
    try {
      FXMLLoader loader = new FXMLLoader(); // load same anchorpane that currentWindow contains
      ListController listController = new ListController();
      listController.setSwappAccess(swappAccess);
      loader.setController(listController);
      loader.setLocation(getClass().getResource("ListOfAds.fxml"));
      AnchorPane pane = loader.load();
      ObservableList<Node> unmodNodeListCurrentWindow = currentWindow.getScene().getRoot().getChildrenUnmodifiable(); // get the children of both
      ObservableList<Node> unmodNodeListLoadedWindow = pane.getChildrenUnmodifiable();
      for (int i = 0; i < unmodNodeListCurrentWindow.size(); i++) {
        assertEquals(unmodNodeListCurrentWindow.get(i).getId(), unmodNodeListLoadedWindow.get(i).getId()); // verify that they're identical by ID
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testLoginUserEmptyFields() {
    writeInLoginFields(EMPTY_STRING, EMPTY_STRING);
    clickOn(loginButton);
    alertDialogPopsUp(AbstractController.ERROR_DIALOG, AbstractController.EMAIL_FIELD_EMPTY);
  }

  @Test
  public void testLoginUserNotFound() {
    writeInLoginFields("asdf@asdf.com", "Asdfasdf123");
    clickOn(loginButton);
    alertDialogPopsUp(AbstractController.ERROR_DIALOG, AbstractController.INVALID_EMAIL_AND_OR_PWD);
  }

  @Test
  public void testLoginLink() {
    final Hyperlink loginLink = lookup("#loginLink").query();
    clickOn(loginLink);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @AfterEach
  public void clearLoginFields() {
    loginEmailField.clear();
    loginPasswordField.clear();
  }

  private void writeInLoginFields(String email, String pwd) {
    clickOn(loginEmailField).write(email);
    clickOn(loginPasswordField).write(pwd);
  }
}
