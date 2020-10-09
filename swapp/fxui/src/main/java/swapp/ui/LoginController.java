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
    @FXML TextField emailField;
    @FXML TextField passwordField;
    @FXML Label emailLabel;
    @FXML Label passwordLabel;
    @FXML Label display;
    @FXML Hyperlink loginLink;

    /**
     * Method that's run when the controller starts
     */
    @FXML
    public void initialize() {
        loadSwapp();
    }

    /**
     * Method that is run when the "Log in"-button is clicked.
     * checks that the user is already registered
     * logs the user in by setting the Swapp object's currentUser to the user being logged in through the app
     * switches the scene to the main app
     */
    @FXML
    public void loginUser(ActionEvent event) {
        Window parent = ((Button) event.getTarget()).getScene().getWindow();
        String emailFieldText = emailField.getText();
        String passwordFieldText = passwordField.getText();

        UserValidation userValidation = this.swapp.getUserValidation();
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
        User loggedIn = this.swapp.getUserLogin(emailFieldText, passwordFieldText);
        if (loggedIn == null) {
            createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_EMAIL_AND_OR_PWD);
            return;
        } else {
            this.swapp.setCurrentUser(loggedIn);
        }
        display.setText("LOGIN SUCCESSFUL!");
        setScene(new FXMLLoader(AbstractController.class.getResource("ListOfAds.fxml")), event);
        // skift til annonse side
        //setScene(new FXMLLoader(getClass().getResource(AD FXML FILE HERE)), event);
    }
}