package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.List;


public class ListController extends AbstractController {
  /**
   * This controls the main page of all active Ads. adList holds all active Ads that should be displayed. ListView
   * displays the active ads in a list. Clicking on an Ad opens a new windows where you should eventually see the Ad
   * and be able to create new ones.
   * TODO: Create new Ad object
   */



  @FXML
  private ListView listView;

  @FXML
  private ComboBox<String> sortByComboBox;
  @FXML
  private ComboBox<String> filterByCombobox;

  private AdList adList;




  /**
   * When ListOfAds.FXML is loaded, initialize() constructs an adList and fills it with some AdOjbects.
   * TODO: Add persistence so adList is filled with all Ad objects previously created.
   */
  public void initialize() {
    loadSwapp();
    swapp.populateAdList();
    adList = swapp.getAdList();
      // gets all ads from Accounts Lists (json) to adList
    sort();  // sort by newest as default

    refreshListView();  // Clears ListView and adds all ads from adList
  }

  // helper functions:

  /**
   * refreshListView updates the GUI to display all Ads in the Adlist.
   */
  private void refreshListView(){

    listView.getItems().clear();  // Clears listView
    for (int i=0;i<adList.getNumberOfAds();i++){
      Ad ad = adList.getAd(i);
      listView.getItems().add(ad);
    }

  }




  /**
   * This method is triggered when a user clicks on an element of the ListView. Should take either Ad or null.
   * @param arg0 (Ad or null)
   * If an Ad has been clicked, a DetailView of the Ad should open. Currently a new scene with a static fxml is used
   * as placeholder.
   */
  @FXML
  void handleListClick(MouseEvent arg0){
    Object ad = listView.getSelectionModel().getSelectedItem(); // Return the ListView element user clicked on
    if (ad instanceof Ad){
            /*TODO: Should transition to DetailView of the ad and display all info about the ad and allow to send
               message */

      Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)arg0.getSource()).getScene().getWindow();
      Parent parent = null;
      try {
        parent = FXMLLoader.load(getClass().getResource("AdDetail.fxml"));
        stageTheEventSourceNodeBelongs.setScene(new Scene(parent));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else{
      System.out.println("Clicked empty list element");
    }
  }





  // triggered by combobox
  public void sort(){
    String sortByToken;
    if (sortByComboBox.getValue() == null) {
      sortByToken = "new";
    }
    else {
      sortByToken = sortByComboBox.getValue();
    }
    sortBy(sortByToken);

  }
  public void sortBy(String s){ // s= "author" | "title" | "new"
    adList.sortBy(s);
    refreshListView();
  }

  public void filter(){
    String filterByToken;
    Ad.Category category = null;
    if (filterByCombobox.getValue() == null) {
      filterByToken = "All";
    }
    else {
      filterByToken = filterByCombobox.getValue();
    }
    //TODO: Use switch-statement
    if (filterByToken.equals("All")){
      initialize();
    }
    else{
      if (filterByToken.equals("Borrow")){
        category = Ad.Category.BORROW;
      }
      else if (filterByToken.equals("Gift")){
        category = Ad.Category.GIFT;
      }
      else if (filterByToken.equals("Trade")){
        category = Ad.Category.TRADE;
      }
      filterByCategory(category);

      sort();
    }


  }
  public void filterByCategory(Enum s){ // s = "borrow" | "switch" | "gift"
    initialize();
    adList.filterByCategory(s);
    refreshListView();
  }
  public void reverse(){
    adList.reverse();
    //refreshListView();
  }


  public void makeAd(ActionEvent event){
    setScene(new FXMLLoader(AbstractController.class.getResource("createNewAd.fxml")), event);
  }

  /**
   * Triggered when Log Out is clicked
   * @param event
   */
  @FXML
  public void logOut(ActionEvent event){
    // TODO: set current user to None
    setScene(new FXMLLoader(AbstractController.class.getResource("Login.fxml")), event);
  }

  /**
   * Triggered by refresh button
   */
  @FXML
  void populateListView(){
    swapp.populateAdList();
  }
  /**
   * DELETE method body here
   */
  public void myProfile(ActionEvent event){ // triggered by button click
    setScene(new FXMLLoader(AbstractController.class.getResource("ProfilePage.fxml")), event);
  }

}
