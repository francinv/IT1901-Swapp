package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.Ad;
import swapp.core.Transaction;
import swapp.core.User;


import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwappAppProfilePageTest extends AbstractTestFxController{
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





  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.PROFILE);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }
  @BeforeEach
  public void initLoginFields() {
    profileNameText  = lookup("#profileNameText").query();
    emailLabel  = lookup("#emailLabel").query();
    adListLabel  = lookup("#adListLabel").query();
    emailText = lookup("#emailText").query();
    adListView = lookup("#adListView").query();
    backButton = lookup("#backButton").query();
    transactionListView = lookup("#transactionListView").query();

  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
    assert transactionListView.getItems().size()==0;
    assert adListView.getItems().size()==4;
    if (this.controller instanceof ListController){
      System.out.println("hei");
    }
    else{
      System.out.println("else");
    }
  }

  @Test
  public void testBackButton(){
    clickOn(backButton);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  /**
   * Credits to StackOverflow: https://stackoverflow.com/questions/50046928/test-javafx-listview-item-selection
   */
  @Test
  public void testListClickEmpty(){
    Node listElement = transactionListView.getChildrenUnmodifiable().get(0);

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

}
