package swapp.ui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import swapp.core.*;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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

  // helper functions:

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
   * This method is triggered when a user clicks on an element of the ListView. Should take either Ad or null.
   * If the element is an Ad, we should transition to AdDetailView and be able to request the Ad.
   * If an Ad has been clicked, a DetailView of the Ad should open.
   * @param arg0
   */
  @FXML
  void handleListClick(MouseEvent arg0) {
    Object ad = listView.getSelectionModel().getSelectedItem(); // Return the ListView element user clicked on

    if (ad instanceof Ad) {
      setSceneAd(CONTROLLERS.ADDETAIL, arg0, swappAccess, (Ad) ad);  // Try to cast the element clicked to an Ad
    } else {
      System.out.println("Clicked empty list element");
    }
  }
  // scene shift to addetail needs it's own setScene, because it takes an extra paramerer, Ad, and calls
  // adDetail.setAd.
  public void setSceneAd(CONTROLLERS type, Event event, SwappAccess access, Ad ad) {
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    try {
      AbstractController controller = type.getControllerInstance();
      controller.setSwappAccess(swappAccess);
      FXMLLoader loader = new FXMLLoader();
      loader.setController(controller);
      loader.setLocation(AbstractController.class.getResource(type.getFXMLString()));


      Parent parent = loader.load();
      Scene newScene = new Scene(parent);
      stage.setScene(newScene);
      if (controller instanceof AdDetailController){
        ((AdDetailController) controller).setAd(ad);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  // triggered by combobox
  public void sort() {
    String sortByToken;
    if (sortByComboBox.getValue() == null) {
      sortByToken = "new";
    } else {
      sortByToken = sortByComboBox.getValue();
    }
    sortBy(sortByToken);

  }

  public void sortBy(String s) { // s= "author" | "title" | "new"
    adList.sortBy(s);
    refreshListView();
  }
/**
 * filters the ListView to show ads certain categories
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

  public void filterByCategory(Enum s) { // s = "borrow" | "switch" | "gift"
    populateList();
    adList.filterByCategory(s);

  }

  public void reverse() {
    adList.reverse();
    refreshListView();
  }


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
    // TODO: set current user to None
    swappAccess.setCurrentUser(null);
    setScene(CONTROLLERS.LOGIN, event, swappAccess);
  }

  /**
   * Triggered by refresh button
   */
  @FXML
  void populateListView() {
    populateList();
  }
  public void myProfile(ActionEvent event) { // triggered by button click
    setScene(CONTROLLERS.PROFILE, event, swappAccess);
  }
}
