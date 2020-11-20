package swapp.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import swapp.core.Ad;
import swapp.core.Transaction;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The profile page displays all received transaction objects, and all ads owned by logged in user.
 * Clicking on a transaction will accept it the transaction will get Status accepted
 * and not show up. The Ad is also set to completed and wont show up on main page.
 */
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

  /**
   * Triggered by button click
   *
   * @param event
   */
  @FXML
  public void handleBackButton(ActionEvent event) {
    setScene(CONTROLLERS.LIST, event, swappAccess);
  }

  /**
   * Triggered by clicking on the listView with ads. Opens the detailView of that ad, similar to
   * main page.
   *
   * @param event
   */
  @FXML
  public void handleListClick(MouseEvent event) {
    Object ad = adListView.getSelectionModel().getSelectedItem(); // Return the ListView element user clicked on

    if (ad instanceof Ad) {
      setSceneAd(CONTROLLERS.ADDETAIL, event, swappAccess, (Ad) ad);  // Try to cast the element clicked to an Ad
    } else {
      System.out.println("Clicked empty list element");
    }

  }

  /**
   * Triggered by someone clicking on an element on the TransactionListView. Could contain a Transaction
   * object or null if empty element is clicked. When a user clicks on a transaction, that transaction is
   * changed to COMPLETED and will not show up anymore. The Ad will also change to completed and not be shown
   * on the main page.
   * @param event
   */
  @FXML
  public void handleTransactionListClick(MouseEvent event) {
    Object transaction = transactionListView.getSelectionModel().getSelectedItem();
    if (transaction instanceof Transaction) {
      Ad ad = ((Transaction) transaction).getAd();
      swappAccess.changeAdStatus(ad, Ad.Status.COMPLETED);
      swappAccess.setTransactionStatus(((Transaction) transaction));
      refreshTransactionList();
    }
    else{
      System.out.println("clicked empty element");
    }
  }

  /**
   * Gets all ads from the logged in user from swapp and ads to Adlist
   */
  @FXML
  private void populateAdList() {
    ads.clear();
    ads.addAll(swappAccess.getCurrentUser().getUserAds());
  }

  /**
   * refreshList updates the GUI so it shows the ads
   */
  @FXML
  private void refreshList() {
    adListView.getItems().clear();
    adListView.getItems().addAll(ads);
  }

  /**
   * refreshes GUI so it shows the ONGOING transactions
   */
  private void refreshTransactionList() {
    transactionListView.getItems().clear();
    transactionListView.getItems().addAll(transactions.stream()
            .filter(transaction -> transaction.getStatus()
                    .equals(Transaction.Status.ONGOING)).collect(Collectors.toList()));
  }

}