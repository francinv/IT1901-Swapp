package swapp.ui;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import org.testfx.robot.MouseRobot;
import swapp.core.Ad;

import java.util.Set;

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
  }

  @Test
  public void testSelectedSwappAccess() {
    assertNotNull(this.controller.swappAccess);
  }

  @Test
  public void testLogOutButton(){
    clickOn(logOut);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void testClickMyProfileButton(){
    clickOn(myProfile);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void testClickMakeAd(){
    clickOn(makeAd);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void testClickRefresh(){
    clickOn(refresh);
  }

  @Test
  public void hasListCell() {

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
    assert listView.getItems().size() == 2;

    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("Trade");
    assert listView.getItems().size() == 1;
    clickOn(filterByCombobox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert filterByCombobox.getValue().equals("Gift");
    assert listView.getItems().size() == 1;

  }

  @Test
  public void setSortByTest(){
    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("new");
    Object newestAd = listView.getItems().get(0);

    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("old");
    assert listView.getItems().get(3).equals(newestAd);


    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("title");
    assert listView.getItems().get(0).toString().equals("Another ad (annonsert av testname)");

    clickOn(sortByComboBox);
    type(KeyCode.DOWN);
    type(KeyCode.ENTER);
    assert sortByComboBox.getValue().equals("author");

  }
  /**
   * Credits to StackOverflow: https://stackoverflow.com/questions/50046928/test-javafx-listview-item-selection
   */
  @Test
  public void testListClickEmpty(){
    Node listElement = listView.getChildrenUnmodifiable().get(0);
    MouseEvent mouseEvent =
            new MouseEvent(
                    MouseEvent.MOUSE_CLICKED,
                    0.0,
                    0.0,
                    0.0,
                    0.0, MouseButton.PRIMARY, 1, false, false, false, false, false, false, false, false, false, true,
                    new PickResult(listElement, 0.0, 0.0)
            );

    MouseEvent.fireEvent(listElement, mouseEvent);
  }

  @Test
  public void testL(){
    listView.getSelectionModel().select(0);
  }

}
