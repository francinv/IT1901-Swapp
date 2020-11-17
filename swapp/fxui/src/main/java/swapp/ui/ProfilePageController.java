package swapp.ui;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import swapp.core.AdList;
import swapp.core.*;

public class ProfilePageController extends AbstractController {
  @FXML
  Label profileNameText;
  @FXML
  Label emailLabel;
  @FXML
  Label adListLabel;
  @FXML
  Label emailText;
  @FXML
  ListView<Ad> listView;
  @FXML
  Button backButton;

  private final List<Ad> ads = new ArrayList<>();

  @FXML
  public void initialize() {
    populateAdList();
    refreshList();
    profileNameText.setText(swappAccess.getCurrentUser().getName());
    emailText.setText(swappAccess.getCurrentUser().getEmail());
  }

  @FXML
  public void handleBackButton(ActionEvent event) {
    setScene(CONTROLLERS.LIST, event, swappAccess);
  }

  @FXML
  public void handleListClick(ActionEvent event) {
    setScene(CONTROLLERS.ADDETAIL, event, swappAccess);
    // må laste inn et spesifikt ad objekt-dette er ikke implementert enda
  }

  @FXML
  private void populateAdList() { // gets all ads from the logged in user from swapp and ads to Adlist
    // TODO ny versjon av populateadlist, som er i swapp, som legger til alle ads tilhørende denne brukeren.
    // TODO kan være en filtrert adlist?. må undersøkes. best å ta det når serveren er klar, siden loadswapp ikke blir relevant
      ads.clear();
      ads.addAll(swappAccess.getCurrentUser().getUserAds());
  }

  /**
   * refreshList updates the GUI so it shows the ads
   * //TODO duplicate method
   */
  @FXML
  private void refreshList() {
    listView.getItems().clear();
    listView.getItems().addAll(ads);
  }
}