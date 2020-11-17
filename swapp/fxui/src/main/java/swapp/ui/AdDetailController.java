package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import swapp.core.Ad;
import swapp.core.AdList;

import javafx.event.ActionEvent;

public class AdDetailController extends AbstractController {
  /**
   * For now only loads a static FXML file and button to get back to all ads
   * TODO: Get access to Ad-object here
   */
  @FXML
  Label nameLabel;

  Ad ad;
  public void initialize() {
    System.out.println("HEI på deg! AdDETAIL");
    ad = null;
    // denne har ikke tilgang til Ad enda
  }
  public void initData(Ad ad){
    this.ad = ad;
  }
  public void customInitialize(){
    nameLabel.setText(ad.getAuthor().toString());
  }

  @FXML
  void handleButton() {
    System.out.println("HEI på deg! AdDETAIL");
  }
  @FXML
  void backToAllAds(ActionEvent event){
    // When clicking "All ads" button, this methods is called and switches back to previous view.
    setScene(CONTROLLERS.LIST, event, swappAccess);
  }
}
