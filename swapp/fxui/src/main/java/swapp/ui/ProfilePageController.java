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
  ListView<Ad> adListView;
  @FXML
  Button backButton;
  @FXML
  ListView<Transaction> transactionListView;

  private final List<Ad> ads = new ArrayList<>();
  private List<Transaction> transactions = new ArrayList<>();

  @FXML
  public void initialize() {
    transactions = new ArrayList<>();
    transactions.addAll(swappAccess.getCurrentUser().getUserTransactions());
    populateAdList();
    refreshList();
    refreshTransactionList();
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
  }
  @FXML
  public void handleTransactionListClick(ActionEvent event) {
    Object transaction = transactionListView.getSelectionModel().getSelectedItem();
    if (transaction instanceof Transaction){
      System.out.println("TODOStuff");
    }
    else{
      System.out.println("clicked empty element");
    }
  }

  /*
  @FXML
  void handleListClick(MouseEvent arg0) {
    Object ad = listView.getSelectionModel().getSelectedItem(); // Return the ListView element user clicked on

    if (ad instanceof Ad) {


  setSceneAd(CONTROLLERS.ADDETAIL, arg0, swappAccess, (Ad) ad);

} else {
        System.out.println("Clicked empty list element");
        }
        }
   */

  @FXML
  private void populateAdList() { // gets all ads from the logged in user from swapp and ads to Adlist
      ads.clear();
      ads.addAll(swappAccess.getCurrentUser().getUserAds());
  }

  /**
   * refreshList updates the GUI so it shows the ads
   * //TODO duplicate method
   */
  @FXML
  private void refreshList() {
    adListView.getItems().clear();
    adListView.getItems().addAll(ads);

  }
  private void refreshTransactionList(){
    transactionListView.getItems().clear();
    transactionListView.getItems().addAll(transactions);
  }

}