package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Window;
import swapp.core.*;

public class RegisterController extends AbstractController {

	@FXML Button registerButton;
	@FXML TextField nameField;
	@FXML TextField emailField;
	@FXML PasswordField passwordField;
	@FXML Label nameLabel;
	@FXML Label emailLabel;
	@FXML Label passwordLabel;
	@FXML Label display;
	@FXML Hyperlink registerLink;

	// OPPRETT ALLE SJEKKER HER INNAD I CONTROLLER FØR DET BLIR PASSERT VIDERE! ??? BEDRE EN USERVALIDATION?

	/**
	 * Method that's run when the app starts
	 */
	@FXML
	public void initialize() {
		loadSwapp();
	}

	/**
     * Method that is run when the Register button is clicked.
	 * adds the User to Swapp's list of users, and calls saveUser() to save it. (!!!!!!)
     * switches the scene to the main app
     * @param event the event with which the method is called
     */
    @FXML
	public void registerUser(ActionEvent event) {
		Window parent = ((Button) event.getTarget()).getScene().getWindow();
		String nameFieldText = nameField.getText();
		String emailFieldText = emailField.getText();
		String pwdFieldText = passwordField.getText();

		UserValidation userValidation = this.swapp.getUserValidation();

		if (nameFieldText.isEmpty()) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, NAME_FIELD_EMPTY);
			return;
		} else if (!userValidation.validUsername(nameFieldText)) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_USERNAME);
			return;
		} else if (userValidation.nameInUse(nameFieldText)) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, NAME_IN_USE);
			return;
		}
		if (emailFieldText.isEmpty()) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, EMAIL_FIELD_EMPTY);
			return;
		} else if (!userValidation.validEmail(emailFieldText)) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_EMAIL);
			return;
		} else if (userValidation.emailInUse(emailFieldText)) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, EMAIL_IN_USE);
			return;
		}
		if (pwdFieldText.isEmpty()) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, PWD_FIELD_EMPTY);
			return;
		} else if (!userValidation.validPassword(pwdFieldText)) {
			createAlertBox(ERROR, parent, ERROR_DIALOG, INVALID_PWD);
			return;
		}
		getSwapp().createUser(nameFieldText, emailFieldText, pwdFieldText);
		saveUser();
		setScene(new FXMLLoader(AbstractController.class.getResource("ListOfAds.fxml")), event);
		//setScene(new FXMLLoader(getClass().getResource(TIL AD SIDE)), event);
	}
}