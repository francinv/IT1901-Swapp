package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import swapp.core.Ad;
import swapp.core.User;

import java.util.List;

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
   * Triggered if user clicks create Ad. If all fields are filled out, create an add an go back to main page.
   * @param event
   */
  @FXML
  public void createAdAndBackToMain(ActionEvent event){
    // When clicking "All ads" button, this methods is called and switches back to previous view.
    if (titleField.getText().isEmpty() || textBodyField.getText().isEmpty() || categoryComboBox.getValue().isEmpty()){
      System.out.println("Empty fields");
      display.setText("You must fill inn all fields");
    }
    else if (titleField.getText().length()>35){
      display.setText("Max 35 characters in title");
    }
    else{
//      List<User> accounts = swappAccess.getAccounts();
//      assert accounts.size()>0;
//      // TODO: Should pick current user, not first user
//      accounts.get(0).createAd(titleField.getText(), textBodyField.getText(), Ad.Category.BORROW); // picks first user
      System.out.println(swappAccess.getCurrentUser().getUserAds());
      swappAccess.createAd(titleField.getText(), textBodyField.getText(), Ad.Category.valueOf(categoryComboBox.getValue().toUpperCase()));
      System.out.println(swappAccess.getCurrentUser().getUserAds());
      setScene(CONTROLLERS.LIST, event, swappAccess);
    }

  }

  /**
   * Triggered if user clicks cancel. Goes back to main page without creating an ad
   * @param event
   */
  @FXML
  public void backToMain(ActionEvent event){
      setScene(CONTROLLERS.LIST, event, swappAccess);

  }


}
