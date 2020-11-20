package swapp.ui;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import swapp.core.Ad;
import swapp.core.AdList;
import java.io.IOException;

/**
 * ListController controls the main page of all active Ads. adList holds all active Ads that should be displayed.
 * ListView displays the content of adList in a ListView. Clicking on an Ad opens a new windows where you
 * should see details about the specific ad and be able to request them if you dont own it (AdDetailController).
 * Clicking on create new Ad button opens a new window where you can create an Ad.
 */
public class ListController extends AbstractController {


  @FXML
  private ListView listView;
  @FXML
  private ComboBox<String> sortByComboBox;
  @FXML
  private ComboBox<String> filterByCombobox;
  private AdList adList;
  @FXML
  public void initialize() {
    populateList();
  }

  /**
   * populateList fills the adList in swapp with all relevant (ACTIVE) Ads which should be
   * displayed in the listview. Sort will sort the ads in adList by new, while refrestListView makes
   * the ListView coherent with adList.
   *
   */
  private void populateList() {
    swappAccess.populateAdList();
    adList = swappAccess.getAdList();

    sort();
    refreshListView();
  }

  /**
   * refreshListView updates the GUI to display all Ads in the Adlist.
   */
  private void refreshListView() {

    listView.getItems().clear();  // Clears listView
    for (int i = 0; i < adList.getNumberOfAds(); i++) {
      Ad ad = adList.getAd(i);
      listView.getItems().add(ad);
    }

  }


  /**
   * This method is triggered when a user clicks on an element of the ListView. It should take
   * either an Ad or null.
   * If the element is an Ad, we should transition to AdDetailView and be able to request the Ad.
   * If an Ad has been clicked, a DetailView of the Ad should open.
   * @param arg0
   */
  @FXML
  void handleListClick(MouseEvent arg0) {
    Object ad = listView.getSelectionModel().getSelectedItem(); // Return clicked listView element (Ad or null if empty)

    if (ad instanceof Ad) {
      setSceneAd(CONTROLLERS.ADDETAIL, arg0, swappAccess, (Ad) ad);  // Try to cast the element clicked to an Ad
    } else {
      System.out.println("Clicked empty list element");
    }
  }





  /**
   * Sort gets the value from comboBox, if nothing use "new" as default.
   */
  public void sort() {
    String sortByToken;
    if (sortByComboBox.getValue() == null) {
      sortByToken = "new";
    } else {
      sortByToken = sortByComboBox.getValue();
    }
    sortBy(sortByToken);

  }

  /**
   * Uses adList method to sort by "author" | "title" | "new". Then refresh.
   * Triggered when user picks a new value in combobox.
   *
   * @param s
   */
  public void sortBy(String s) { // s= "author" | "title" | "new"
    adList.sortBy(s);
    refreshListView();
  }

  /**
   * Filter shows only Ads with selected category. This method gets a string from the combobox and
   * calls filterByCategory with the correct Enum. It then calls sort which ensure elements are in the
   * same order as before, and also refreshes listView.
   */
  public void filter() {
    String filterByToken;
    Ad.Category category = null;
    if (filterByCombobox.getValue() == null) {
      filterByToken = "All";
    } else {
      filterByToken = filterByCombobox.getValue();
    }
    if (filterByToken.equals("All")) {
      populateList();
    } else {
      if (filterByToken.equals("Borrow")) {
        category = Ad.Category.BORROW;
      } else if (filterByToken.equals("Gift")) {
        category = Ad.Category.GIFT;
      } else if (filterByToken.equals("Trade")) {
        category = Ad.Category.TRADE;
      }
      filterByCategory(category);

      sort();
    }


  }

  /**
   * First fills up adList with all ads again, then removes all elements with a different category then
   * specified by Enum s.
   * @param s
   */
  public void filterByCategory(Enum s) {
    populateList();
    adList.filterByCategory(s);

  }

  public void reverse() {
    adList.reverse();
    refreshListView();
  }

  /**
   * Triggered by clicking create new ad-button.
   * @param event
   */
  public void makeAd(ActionEvent event) {
    setScene(CONTROLLERS.NEWAD, event, swappAccess);
  }

  /**
   * Triggered when Log Out is clicked
   *
   * @param event
   */
  @FXML
  public void logOut(ActionEvent event) {
    swappAccess.setCurrentUser(null);
    setScene(CONTROLLERS.LOGIN, event, swappAccess);
  }

  /**
   * Triggered by refresh button.
   */
  @FXML
  void populateListView() {
    populateList();
  }

  /**
   * Triggered by to proifle page-button.
   */
  public void myProfile(ActionEvent event) { // triggered by button click
    setScene(CONTROLLERS.PROFILE, event, swappAccess);
  }
}
