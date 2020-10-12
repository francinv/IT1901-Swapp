package swapp.ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
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
import swapp.core.Swapp;
import swapp.json.SwappModule;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    private static final String REGISTER_FXML = "Register.fxml";
    private static final String LOGIN_FXML = "Login.fxml";

    private final Path path = Paths.get("users.json");
    protected Swapp swapp;
    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.registerModule(new SwappModule());
        }
        return objectMapper;
    }

    public void loadSwapp() {
        if (!path.toFile().exists()) {
            this.swapp = new Swapp();
        } else {
            openUsers();
        }
    }

    public void openUsers() {
        try (InputStream inputStream = new FileInputStream(path.toFile())) {
            this.swapp = getObjectMapper().readValue(inputStream, Swapp.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveUser() {
        try (OutputStream outputStream = new FileOutputStream(path.toFile())) {
            getObjectMapper().writeValue(outputStream, this.swapp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML public void onMouseHoverEnter(MouseEvent event) {
        ((Node) event.getTarget()).getScene().setCursor(Cursor.HAND);
    }

    @FXML public void onMouseHoverExit(MouseEvent event) {
        ((Node) event.getTarget()).getScene().setCursor(Cursor.DEFAULT);
    }

    @FXML public void setOnActionLink(ActionEvent event) {
        String hyperlinkID = ((Hyperlink) event.getSource()).getId();
        switch (hyperlinkID) {
            case "registerLink":
                setScene(new FXMLLoader(AbstractController.class.getResource(LOGIN_FXML)), event);
                break;
            case "loginLink":
                setScene(new FXMLLoader(AbstractController.class.getResource(REGISTER_FXML)), event);
                break;
            default:
                break;
        }
    }

    public void setScene(FXMLLoader loader, ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            Parent parent = loader.load();
            Scene newScene = new Scene(parent);
            stage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createAlertBox(Alert.AlertType alertType, Window parent, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.initOwner(parent);
        alert.show();
    }

    public Swapp getSwapp() {
        return this.swapp;
    }
}
