package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import swapp.core.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import swapp.core.UserValidation;

public class ListController extends AbstractController {
  /**
   * This controls the main page of all active Ads. adList holds all active Ads that should be displayed. ListView
   * displays the active ads in a list. Clicking on an Ad opens a new windows where you should eventually see the Ad
   * and be able to create new ones.
   * TODO: Create new Ad object
   */
  @FXML
  private ListView listView;
  private AdList adList;


  /**
   * When ListOfAds.FXML is loaded, initialize() constructs an adList and fills it with some AdOjbects.
   * TODO: Add persistence so adList is filled with all Ad objects previously created.
   */
  public void initialize() {
    loadSwapp();
    adList = new AdList();
    populateAdList();  // gets all ads from User Lists (json) to adList
    refreshList();  // Clears ListView and adds all ads from adList

  }
  private void populateAdList(){ // gets all ads from all users from swapp and ads to Adlist
    List<User> accounts = this.swapp.getAccounts();
    for (User user: accounts){
      for (Ad ad: user.getUserAds()){
        adList.add(ad);
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
    System.out.println(this.swapp.getCurrentUser()); // null
    System.out.println(this.swapp.getUser("henrik81"));// [NAME: henrik81, EMAIL: test@test81.no]
    System.out.println(this.swapp.getUserAmount()); // 3
    this.swapp.getUser("henrik81").createAd("nepe", "Godt brukt");


    for (User user: accounts){
      System.out.println("kek " +user);
      user.createAd("nepe", "Godt brukt");
      user.createAd("kål", "Pen");
      user.createAd("koll", "brukt");
      System.out.println("kek2 " +user.getUserAds());
      for (Ad ad: user.getUserAds()){
        System.out.println("kek3 " +ad);
        adList.add(ad);
      }
    }






  }




  // temporary test method
  @FXML
  void populateListView(){
    testy();
    refreshList();
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
    System.out.println("sss");

    // TODO: Should transition to a list of currently logged in user's Ads
  }



  //legacy code, dont look at this.
  private String[] objectHboxToString(Object hbox){
    if(hbox instanceof HBox) {
      // ((HBox) hbox).getChildren().get(0) // Bruk casting for å få fatt i Label
      // System.out.println(((HBox) hbox).getChildren().get(0).getText());
      int i=0;
      String[] labelString = new String[3];
      for (Node label : ((HBox) hbox).getChildren()) {
        System.out.println(label);
        if (label instanceof Label){
          labelString[i] = ((Label) label).getText();
          i++;
        }
      }
      return labelString;
    }
    return null;
  }
  private void tes(){
    //test stuff
  }
}
