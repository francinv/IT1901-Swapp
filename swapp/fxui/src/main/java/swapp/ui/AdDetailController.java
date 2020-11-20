package swapp.ui;

import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import swapp.core.Ad;

/**
 * AdDetailController shows details about one specific Ad and is triggered by clicking on an Ad in the listview.
 * The method setAd should then be called which will update Labels to match the specific Ad.
 *
 */
public class AdDetailController extends AbstractController {

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

  /**
   * When clicking "All ads" button, this methods is called and switches back to previous view.
   *
   * @param event
   */
  @FXML
  void backToAllAds(ActionEvent event) {

    setScene(CONTROLLERS.LIST, event, swappAccess);
  }

  /**
   * When Back to Profile-button is cliecked, this method is triggered.
   * @param event
   */
  @FXML
  void backToProfile(ActionEvent event) {
    setScene(CONTROLLERS.PROFILE, event, swappAccess);
  }

  /**
   * request is triggered by clicking the "requst this ad"-button. It creates a transaction that the adOwner will
   * be able to accept.
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

  /**
   * When setting the controller to transition to, setAd should be called and give the specific Ad that has been
   * clicked in a listView. setAd updates the field and then updates labels.
   * @param ad
   */
  public void setAd(Ad ad) {
    this.ad = ad;
    setLabels();
  }

  /**
   * customizes the text that shows up so it corresponds to the correct Ad
   */
  private void setLabels() {
    titleLabel.setText(ad.getTitle());
    textBody.setText(ad.getTextBody());
    userLabel.setText("advertised by: " + ad.getAuthor().getName()
            + " with email " + ad.getAuthor().getEmail());
    categoryText.setText("Category: " + ad.getCategory().toString().toLowerCase()
            + "     (" + convertTime(ad.getTime()) + ")");
  }

  /**
   * Helper to make unix time to a more readable format.
   *
   * @param time
   * @return
   */
  private String convertTime(long time) {
    Date date = new Date(time);
    return date.toString();

  }

  public Ad getAd() {
    return this.ad;
  }
}
