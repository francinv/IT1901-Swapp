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

  private enum CurrentSorting{
    NEW,
    OLD,
    TITLE,
    AUTHOR
  }

  @FXML
  private ListView listView;

  @FXML
  private ComboBox<String> sortByComboBox;
  @FXML
  private ComboBox<String> filterByCombobox;

  private AdList adList;
  private CurrentSorting currentSorting;



  /**
   * When ListOfAds.FXML is loaded, initialize() constructs an adList and fills it with some AdOjbects.
   * TODO: Add persistence so adList is filled with all Ad objects previously created.
   */
  public void initialize() {

    loadSwapp();
    adList = swapp.getAdList();
    populateAdList();  // gets all ads from User Lists (json) to adList

    //this.currentSorting= CurrentSorting.NEW;


    //testy(); // adds some test Ads, remove at some point
    refreshList();  // Clears ListView and adds all ads from adList

  }
  private void populateAdList(){ // gets all ads from all users from swapp and ads to Adlist
    List<User> accounts = this.swapp.getAccounts();
    for (User user: accounts){
      for (Ad ad: user.getUserAds()){
        if (ad.getStatus().equals(Ad.Status.ACTIVE)){
          adList.add(ad);
        }
        else{
          System.out.println(ad.getStatus());
        }

      }
    }
  }
  /**
   * refreshList updates the GUI to display all Ads in the Adlist.
   */
  private void refreshList(){
    listView.getItems().clear();
    addAllAdsToListView();
  }
  private void addAllAdsToListView(){
    for (int i=0;i<adList.getNumberOfAds();i++){
      Ad ad = adList.getAd(i);
      listView.getItems().add(ad);
    }
  }
  public void testy(){
    List<User> accounts = this.swapp.getAccounts();
    /*
    System.out.println(this.swapp.getCurrentUser()); // null
    System.out.println(this.swapp.getUser("henrik81"));// [NAME: henrik81, EMAIL: test@test81.no]
    System.out.println(this.swapp.getUserAmount()); // 3
    this.swapp.getUser("henrik81").createAd("nepe", "Godt brukt");
    */

    for (User user: accounts){
      System.out.println("kek " +user);
      user.createAd("nepe", "Godt brukt", Ad.Category.BORROW );
      user.createAd("kål", "Pen", Ad.Category.BORROW);
      user.createAd("ergonomisk stol", "brukt", Ad.Category.GIFT);
      user.createAd("ubåt til låns", "brukt", Ad.Category.BORROW);
      user.createAd("gir ski", "brukt", Ad.Category.GIFT);
      System.out.println("kek2 " +user.getUserAds());
      for (Ad ad: user.getUserAds()){
        adList.add(ad);

      }
      saveUser();
    }
  }



  // temporary test method
  @FXML
  void populateListView(){
    //testy();
    System.out.println(adList);
    System.out.println(adList.getAd(0).getStatus());
    System.out.println(adList.getAd(0).getCategory());
    //refreshList();
    initialize();
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


      //AdDetail detail = new AdDetail((Ad) ad, stageTheEventSourceNodeBelongs);
            /*
            stageTheEventSourceNodeBelongs.setScene(new Scene(new Pane(new Label("git good"))));
            //Parent parent = FXMLLoader.load(getClass().getResource("Register.fxml"));
            //stageTheEventSourceNodeBelongs.setScene(new Scene(parent));
            stageTheEventSourceNodeBelongs.setMaximized(true);
            stageTheEventSourceNodeBelongs.show();
            stageTheEventSourceNodeBelongs.setMaximized(true);*/
    }
    else{
      System.out.println("Clicked empty list element");
    }
  }
  public void myAds(){ // triggered by button click
    adList.sortBy("time");
    System.out.println("kek "+adList.getAd(0).getTime());
    refreshList();
    System.out.println("sss");

    // TODO: Should transition to a list of currently logged in user's Ads
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
    refreshList();
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
      filterByCategory(category); //Wrong, combobox values doesnt correspond to filter values
      System.out.println(filterByToken);
    }


  }
  public void filterByCategory(Enum s){ // s = "borrow" | "switch" | "gift"
    initialize();
    adList.filterByCategory(s);
    refreshList();
  }
  public void reverse(){
    adList.reverse();
    refreshList();
  }
  private void reset(){ // removes filters and sorting
    initialize();
  }

  public void makeAd(ActionEvent event){
    setScene(new FXMLLoader(AbstractController.class.getResource("createNewAd.fxml")), event);
  }
  /*
    adList.reverse();
    adList.sortBy("author" | "title");
    adlist.filterByCategory("borrow" | "switch" | "gift")
   */

  /**
   * Triggered when
   * @param event
   */
  @FXML
  public void logOut(ActionEvent event){
    // set current user to None
    setScene(new FXMLLoader(AbstractController.class.getResource("Login.fxml")), event);
  }

}
