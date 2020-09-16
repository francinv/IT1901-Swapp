package swapp.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import swapp.core.*;
import swapp.json.SwappModule;

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
		// ikke særlig synlig enda, men dette laster inn swapp fra users.json hvis den eksisterer
		if (!usersPath.toFile().exists()) {
			this.swapp = new Swapp();
		} else {
			openUsers();
		}
	}

	@FXML
	void handleButton() {
		this.swapp.add(new User(nameText.getText(), emailText.getText(), passwordText.getText()));
		display.setText("bruker registrert");
		saveUser();
    }
	
	private ObjectMapper objectMapper;
    final private Path usersPath = Paths.get("src/main/resources/swapp/json/users.json");
    
    private ObjectMapper getObjectMapper() {
    	if (objectMapper == null) {
    		objectMapper = new ObjectMapper();
    		objectMapper.registerModule(new SwappModule());
    	}
    	return objectMapper;
    }
    
    private void saveUser() {
    	try {
    		File users = usersPath.toFile();
    		users.createNewFile();
    		OutputStream outputStream = new FileOutputStream(users);
			getObjectMapper().writeValue(outputStream, this.swapp);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    private void openUsers() {
    	try {
    		InputStream inputStream = new FileInputStream(usersPath.toFile());
    		this.swapp = getObjectMapper().readValue(inputStream, Swapp.class);
    	} catch (Exception e) {
    		// TODO: handle exception
    	}
    }
    
}