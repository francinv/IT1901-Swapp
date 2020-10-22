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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FxAppLoginTest extends ApplicationTest {

    private AbstractController controller;
    private static final String EMPTY_STRING = "";
    private TextField loginEmailField;
    private PasswordField loginPasswordField;
    private Button loginButton;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        final Parent parent = loader.load();
        this.controller = loader.getController();
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
        controller.getSwapp().add(new User("foobar", "foo@bar.com", "Foobar123"));
        writeInLoginFields("foo@bar.com", "Foobar123");
        clickOn(loginButton);
        Window currentWindow = window(getTopModalStage().getScene()); // gets the current window on top which at this point will be the ad view
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOfAds.fxml")); // load same anchorpane that currentWindow contains
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