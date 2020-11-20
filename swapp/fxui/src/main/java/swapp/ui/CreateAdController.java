package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import swapp.core.Ad;

/**
 * Controller for creating an Ad. It has textFields the user must fill in.
 * If the input meets the requirements, a new Ad is created through swapp.
 */
public class CreateAdController extends AbstractController {
  @FXML
  private ComboBox<String> categoryComboBox;
  @FXML
  TextField titleField;
  @FXML
  TextArea textBodyField;
  @FXML
  Label display;

  public void initialize() {
    System.out.println("createAdd init");
  }

  /**
   * Triggered if user clicks create Ad. If all fields are filled out, create
   * an add an go back to main page.
   *
   * @param event
   */
  @FXML
  public void createAdAndBackToMain(ActionEvent event) {

    if (titleField.getText().isEmpty() || textBodyField.getText().isEmpty() || categoryComboBox.getValue().isEmpty()) {
      display.setText("You must fill inn all fields");
    }
    else if (titleField.getText().length()>35) { // if too long title
      display.setText("Max 35 characters in title");
    }
    else { //else success
      swappAccess.createAd(titleField.getText(), textBodyField.getText(), Ad.Category.valueOf(categoryComboBox.getValue().toUpperCase()));
      display.setText("Success!"); // for testing purposes
      setScene(CONTROLLERS.LIST, event, swappAccess);
    }
  }

  /**
   * Triggered if user clicks cancel. Goes back to main page without creating an ad
   *
   * @param event
   */
  @FXML
  public void backToMain(ActionEvent event) {
      setScene(CONTROLLERS.LIST, event, swappAccess);
  }


}
