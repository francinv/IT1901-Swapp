package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Window;
import swapp.core.User;
import swapp.core.UserValidation;

public class LoginController extends AbstractController {

  @FXML Button loginButton;
  @FXML TextField loginEmailField;
  @FXML TextField loginPasswordField;
  @FXML Label emailLabel;
  @FXML Label passwordLabel;
  @FXML Label display;
  @FXML Hyperlink loginLink;

  /**
   * Method that is run when the "Log in"-button is clicked.
   * checks that the user is already registered
   * logs the user in by setting the Swapp object's currentUser to the user being logged in through the app
   * switches the scene to the main app
   */
  @FXML
  public void loginUser(ActionEvent event) {
    Window parent = ((Button) event.getTarget()).getScene().getWindow();
    String emailFieldText = loginEmailField.getText();
    String passwordFieldText = loginPasswordField.getText();

    UserValidation userValidation = swappAccess.getUserValidation();
    if (emailFieldText.isEmpty()) {
      createAlertBox(ERROR, parent, ERROR_DIALOG, EMAIL_FIELD_EMPTY);
      return;
    }
    if (!userValidation.validEmail(emailFieldText)) {
      createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_EMAIL);
      return;
    }
    if (passwordFieldText.isEmpty()) {
      createAlertBox(ERROR, parent, ERROR_DIALOG, PWD_FIELD_EMPTY);
      return;
    }
    if (!userValidation.validPassword(passwordFieldText)) {
      createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_PWD);
      return;
    }
    boolean bool = swappAccess.loginUser(emailFieldText, passwordFieldText);
    if (!bool) {
      createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_EMAIL_AND_OR_PWD);
      return;
    }
    display.setText("LOGIN SUCCESSFUL!");
    setScene(CONTROLLERS.LIST, event, swappAccess);
    // skift til annonse side
    //setScene(new FXMLLoader(getClass().getResource(AD FXML FILE HERE)), event);
  }
}