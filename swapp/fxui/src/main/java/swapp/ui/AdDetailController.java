package swapp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import swapp.core.Ad;
import swapp.core.AdList;

public class AdDetailController {
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
    nameLabel.setText(ad.getAuthor());
  }

  @FXML
  void handleButton() {
    System.out.println("HEI på deg! AdDETAIL");
  }
}
