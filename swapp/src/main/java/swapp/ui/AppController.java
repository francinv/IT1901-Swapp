package swapp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import swapp.core.*;

public class AppController {
	@FXML
	Button knapp;
	@FXML
	TextField nameText;
	@FXML
	TextField emailText;
	@FXML
	TextField passwordText;
	@FXML
	Label nameLabel;
	@FXML
	Label emailLabel;
	@FXML
	Label passwordLabel;
	@FXML
	Label display;
	
	private Swapp swapp;
	
	@FXML
	void initialize() {
		this.swapp = new Swapp();
	}
	
	@FXML
	void handleButton() {
		this.swapp.add(new User(nameText.getText(), emailText.getText(), passwordText.getText()));
		display.setText("bruker registrert");
    }
}