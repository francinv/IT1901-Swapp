package swapp.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.Ad;
import swapp.core.User;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SwappAppAdDetailTest extends AbstractTestFxController{
  @FXML
  Label display;
  @FXML
  Label textBody;
  @FXML
  Label categoryText;
  @FXML
  Label titleLabel;
  @FXML
  Button backToAllAds;
  @FXML
  Button request;
  @FXML
  Button backToProfile;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.ADDETAIL);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
    if (controller instanceof AdDetailController){
      Ad ad = new Ad("title31",new User("testname","test@test.no","Testpass123"),
              "texbody", Ad.Category.BORROW);
      ((AdDetailController) controller).setAd(ad);
    }
  }
  @BeforeEach
  public void lookUpElements() {

    display = lookup("#display").query();
    textBody =  lookup("#textBody").query();
    categoryText= lookup("#categoryText").query();
    titleLabel= lookup("#titleLabel").query();
    backToAllAds = lookup("#backToAllAds").query();
    request= lookup("#request").query();
    backToProfile = lookup("#backToProfile").query();

  }

  @Test
  public void testController_initial() {
    assertNotNull(this.controller);
    assert textBody.getText().equals("texbody");
    assert titleLabel.getText().equals("title31");
    assert categoryText.getText().substring(0,16).equals("Category: borrow");
    assert display.getText().isEmpty();
  }

  @Test
  public void testClickBackToMain(){
    clickOn(backToAllAds);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void testClickMyProfileButton(){
    clickOn();
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void testClickMyRequest(){
    clickOn(request);
    assert display.getText().equals("You have sucessfully requested this ad!");
  }




}
