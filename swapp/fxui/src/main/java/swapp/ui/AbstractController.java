package swapp.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

/**
 * Contains shared methods for all controllers.
 */
public abstract class AbstractController {

  protected static final Alert.AlertType ERROR = Alert.AlertType.ERROR;
  protected static final String ERROR_DIALOG = "Error!";
  protected static final String EMAIL_IN_USE = "That email is already in use.";
  protected static final String NAME_FIELD_EMPTY = "Please enter a name.";
  protected static final String EMAIL_FIELD_EMPTY = "Please enter an email.";
  protected static final String PWD_FIELD_EMPTY = "Please enter a password.";
  protected static final String NAME_IN_USE = "That username is already in use.";
  protected static final String INVALID_USERNAME = "Invalid username, username must contain at least 3 characters and must end and start with an alphanumerical character.";
  protected static final String INVALID_EMAIL = "Invalid email, must be of format: name-part@domain, e.g. example@example.com.";
  protected static final String INVALID_PWD = "Invalid password, must be at least 8 characters and contain at least 1 digit and 1 lower and uppercase letter.";
  protected static final String INVALID_EMAIL_AND_OR_PWD = "Invalid email address or password.";

  public enum CONTROLLERS {
    LOGIN("Login.fxml", new LoginController()),
    REGISTER("Register.fxml", new RegisterController()),
    LIST("ListOfAds.fxml", new ListController()),
    NEWAD("createNewAd.fxml", new CreateAdController()),
    ADDETAIL("adDetail.fxml", new AdDetailController()),
    PROFILE("ProfilePage.fxml", new ProfilePageController());

    private final String fxml;
    private final AbstractController controller;

    CONTROLLERS(String fxml, AbstractController controller) {
      this.fxml = fxml;
      this.controller = controller;
    }

    public AbstractController getControllerInstance() {
      return this.controller;
    }

    public String getFXMLString() {
      return this.fxml;
    }
  }

  protected SwappAccess swappAccess;

  /**
   *
   * @param event
   */
  @FXML public void onMouseHoverEnter(MouseEvent event) {
    ((Node) event.getTarget()).getScene().setCursor(Cursor.HAND);
  }

  /**
   *
   * @param event
   */
  @FXML public void onMouseHoverExit(MouseEvent event) {
    ((Node) event.getTarget()).getScene().setCursor(Cursor.DEFAULT);
  }

  /**
   *
   * @param event
   */
  @FXML public void setOnActionLink(ActionEvent event) {
    String hyperlinkID = ((Hyperlink) event.getSource()).getId();
    switch (hyperlinkID) {
      case "registerLink":
        setScene(CONTROLLERS.LOGIN, event, swappAccess);
        break;
      case "loginLink":
        setScene(CONTROLLERS.REGISTER, event, swappAccess);
        break;
      default:
        break;
    }
  }

  /**
   * This method sets a new scene.
   * @param type
   * @param event
   * @param access
   */
  public void setScene(CONTROLLERS type, Event event, SwappAccess access) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    try {
      AbstractController controller = type.getControllerInstance();
      controller.setSwappAccess(swappAccess);
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(AbstractController.class.getResource(type.getFXMLString()));
      Parent parent = loader.load();
      Scene newScene = new Scene(parent);
      stage.setScene(newScene);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   *
   * @param alertType
   * @param parent
   * @param title
   * @param message
   */
  public void createAlertBox(Alert.AlertType alertType, Window parent, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.initOwner(parent);
    alert.show();
  }

  /**
   *
   * @param swappAccess
   */
  public void setSwappAccess(SwappAccess swappAccess) {
    this.swappAccess = swappAccess;
    System.out.println("swappAccess " + swappAccess);
  }
}
