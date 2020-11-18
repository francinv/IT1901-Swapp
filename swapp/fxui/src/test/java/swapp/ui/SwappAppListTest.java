package swapp.ui;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import swapp.core.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwappAppListTest extends AbstractTestFxController {

  @FXML
  private ListView listView;
  @FXML
  private ComboBox<String> sortByComboBox;
  @FXML
  private ComboBox<String> filterByCombobox;
  @FXML
  private Button logOut;
  @FXML
  private Button makeAd;
  @FXML
  private Button refresh;
  @FXML
  private Button myProfile;


  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.LIST);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }
  @BeforeEach
  public void initLoginFields() {
    listView = lookup("#listView").query();
    sortByComboBox = lookup("#sortByComboBox").query();
    filterByCombobox = lookup("#filterByCombobox").query();
    logOut= lookup("#logOut").query();
    makeAd= lookup("#makeAd").query();
    refresh= lookup("#refresh").query();
    myProfile= lookup("#myProfile").query();
  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
    assert this.filterByCombobox.getItems().size() == 4;
    assert this.sortByComboBox.getItems().size() == 4;
    System.out.println(this.filterByCombobox.getItems().size());
  }

  @Test
  public void testSelectedSwappAccess() {
    assertNotNull(this.controller.swappAccess);
  }

  @Test
  public void testLogOutButton(){
    clickOn(logOut);
  }

  @Test
  public void testClickMyProfileButton(){
    clickOn(myProfile);
  }

  @Test
  public void testClickMakeAd(){
    clickOn(makeAd);
  }

  @Test
  public void testClickRefresh(){
    clickOn(refresh);
  }

  @Test
  public void hasListCell() {
    System.out.println(listView.getItems().size());
    //assert listView.getItems().size() == 0;
    System.out.println(listView.getItems().get(0));
    Object firstListViewElement=listView.getItems().get(0);// gets first listview element
    listView.getSelectionModel().selectFirst(); // click on first listView Item
    assert firstListViewElement.equals(listView.getSelectionModel().getSelectedItem()); //assert same object is selected
    System.out.println(listView.getSelectionModel().getSelectedItem());

  }
  @Test
  public void filterTest(){
    int size = listView.getItems().size();
    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("All");
    assert listView.getItems().size() == size;
    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("Borrow");


    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("Trade");


    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("Gift");

  }

  @Test
  public void setSortByTest(){
    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("new");
    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("old");


    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("title");
    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("author");
  }


}
