package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import swapp.core.Ad;

import java.util.Date;

/**
 * AdDetailController shows details about one specific Ad and is triggered by clicking on an Ad in the listview.
 * The method setAd should then be called which will update Labels to match the specific Ad.
 *
 */
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

  @FXML
  void backToAllAds(ActionEvent event) {
    // When clicking "All ads" button, this methods is called and switches back to previous view.
    setScene(CONTROLLERS.LIST, event, swappAccess);
  }

  /**
   * request is triggered by clicking the "requst this ad"-button. It creates a transaction that the adOwner will
   * be able to accept
   *
   */
  @FXML
  void request() {
    assert this.ad != null;
    if (swappAccess.getCurrentUser().equals(this.ad.getAuthor())) {
      display.setText("You own this Ad and therefore cant request it.");
    }
    else {
      swappAccess.createTransaction(this.ad, swappAccess.getCurrentUser());
      display.setText("You have sucessfully requested this ad!");
    }
  }

  public void setAd(Ad ad) {
    this.ad = ad;
    setLabels();
  }


  private void setLabels() {
    titleLabel.setText(ad.getTitle());
    textBody.setText(ad.getTextBody());
    userLabel.setText("advertised by: " + ad.getAuthor().getName() + " with email " + ad.getAuthor().getEmail());
    categoryText.setText("Category: " + ad.getCategory().toString().toLowerCase() + "     (" +
            convertTime(ad.getTime()) + ")");
  }

  private String convertTime(long time) {
    Date date = new Date(time);
    return date.toString();

  }

  public Ad getAd() {
    return this.ad;
  }
}
