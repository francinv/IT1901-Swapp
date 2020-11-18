package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import swapp.core.Ad;
import swapp.core.AdList;

import javafx.event.ActionEvent;

import java.util.Date;

public class AdDetailController extends AbstractController {
  /**
   * For now only loads a static FXML file and button to get back to all ads
   * TODO: Get access to Ad-object here
   */
  @FXML
  public Label userLabel;
  @FXML
  public Label categoryText;
  @FXML
  public Label titleLabel;
  @FXML
  public Label textBodyLabel;
  @FXML
  public Label textBody;
  @FXML
  public Button backToAllAds;
  @FXML
  public Button request;
  @FXML
  public Label display;



  Ad ad;


  public void initialize() {
    System.out.println("HEI på deg! AdDETAIL");
    //ad = null;
    // denne har ikke tilgang til Ad enda
  }
  public void initData(Ad ad){
    this.ad = ad;
  }
  public void customInitialize(){
    userLabel.setText(ad.getAuthor().toString());
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

  /**
   * request is triggered by clicking the "requst this ad"-button.
   *
   */
  @FXML
  void request(){
    assert this.ad != null;
    swappAccess.createTransaction(this.ad, swappAccess.getCurrentUser());
    /*
    åpner en transaction, med status ongoing. mangler et steg hvor mottaker får en beskjed og kan trykke accept?
    funksjon i user som er accept/deny, og funksjon i transaction som er notifyReceiver()?
     Should also check that a request with same Ad and same currentUser exists.
     Temporarily this just sets Ad.status to completed, so should not show up on main page.
    */

    swappAccess.changeAdStatus(ad, Ad.Status.COMPLETED);
    display.setText("You have sucessfully requested this ad!");
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    setLabels();
  }


  private void setLabels(){
    titleLabel.setText(ad.getTitle());
    textBody.setText(ad.getTextBody());
    userLabel.setText("advertised by: "+ad.getAuthor().getName()+" with email "+ad.getAuthor().getEmail());
    categoryText.setText("Category: "+ad.getCategory().toString().toLowerCase()+"     ("+
            convertTime(ad.getTime())+")");
  }

  private String convertTime(long time){
    Date date = new Date(time);
    return date.toString();

  }
}
