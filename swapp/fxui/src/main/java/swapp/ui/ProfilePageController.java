package swapp.ui;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import swapp.core.AdList;
import swapp.core.*;

public class ProfilePageController extends AbstractController{
    @FXML 
    Label profileNameText;
    @FXML
    Label emailLabel;
    @FXML
    Label adListLabel;
    @FXML
    Label emailText;
    @FXML
    ListView listView;
    @FXML
    Button backButton;

    private AdList adList;
    private User loggedInUser;
    
    /**
     * when the app launches, initialize() is called
     * the method customizes the profile page so it shows the logged in user's page
     */
    @FXML
    public void initialize() {
        loadSwapp();
        this.loggedInUser = this.swapp.getAccounts().get(0); // placeholder. henter den første registrerte brukeren istedenfor implementasjon for å hente innlogget bruker
        this.adList = new AdList();
    	profileNameText.setText(this.loggedInUser.getName());// må hente brukernavnet til den innloggede brukeren
        emailText.setText(this.loggedInUser.getEmail()); //hente eposten til den innloggede brukeren
    	// TODO hente adlisten fra persistens...
    }
    @FXML
    public void handleBackButton(ActionEvent event) {
    	setScene(new FXMLLoader(AbstractController.class.getResource("ListOfAds.fxml")), event);
    }
    @FXML
    public void handleListClick(ActionEvent event) {
    	setScene(new FXMLLoader(AbstractController.class.getResource("adDetail.fxml")), event);
    	// må laste inn et spesifikt ad objekt-dette er ikke implementert enda
    }
    @FXML
    private void populateAdList(){ // gets all ads from the logged in user from swapp and ads to Adlist
        // TODO ny versjon av populateadlist, som er i swapp, som legger til alle ads tilhørende denne brukeren.
        // TODO kan være en filtrert adlist?. må undersøkes. best å ta det når serveren er klar, siden loadswapp ikke blir relevant
        for (Ad ad : this.loggedInUser.getUserAds()) {
            adList.add(ad);
        }
  }

  /**
   * refreshList updates the GUI so it shows the ads
   */
  @FXML
  private void refreshList(){
    listView.getItems().clear();
    for (int i=0;i<adList.getNumberOfAds();i++){
      Ad ad = adList.getAd(i);
      listView.getItems().add(ad);
    }
  }
}