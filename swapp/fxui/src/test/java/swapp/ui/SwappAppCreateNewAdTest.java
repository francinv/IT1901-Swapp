package swapp.ui;

import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.WindowMatchers;

public class SwappAppCreateNewAdTest extends AbstractTestFxController {

  private ComboBox<String> categoryComboBox;
  TextField titleField;
  TextArea textBodyField;
  Label display;
  private Button createAdButton;
  private Button cancel;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = loadFXML(AbstractController.CONTROLLERS.NEWAD);
    final Parent parent = loader.load();
    final Scene scene = new Scene(parent);
    stage.setScene(scene);
    stage.show();
  }

  @BeforeEach
  public void findFields() {
    createAdButton= lookup("#createAdButton").query();
    cancel= lookup("#cancel").query();
    titleField= lookup("#titleField").query();
    textBodyField= lookup("#textBodyField").query();
    categoryComboBox= lookup("#categoryComboBox").query();
    display = lookup("#display").query();
  }

  @AfterEach
  public void clearFields(){
    titleField.clear();
    textBodyField.clear();
  }

  @Test
  public void testClickCreateAdUnsuccessful(){
    assert display.getText().equals("Fill in all fields");
    clickOn(createAdButton);
    assert display.getText().equals("You must fill inn all fields");
  }

  @Test
  public void testClickCreateAdSuccessful(){
    assert display.getText().equals("Fill in all fields");
    writeInFields("Fisk", "Fersk",3);
    clickOn(createAdButton);
    assert display.getText().equals("Success!");
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }

  @Test
  public void CreateAdWithEmptyString(){
    assert display.getText().equals("Fill in all fields");
    writeInFields("Fisk", "",2);
    clickOn(createAdButton);
    assert display.getText().equals("You must fill inn all fields");
  }
  @Test
  public void testTooLongTitle(){
    assert display.getText().equals("Fill in all fields");
    writeInFields("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "body",2);
    clickOn(createAdButton);
    assert display.getText().equals("Max 35 characters in title");
  }

  @Test
  public void testClickCancel(){
    clickOn(cancel);
    FxAssert.verifyThat(window(getTopModalStage().getScene()), WindowMatchers.isShowing());
  }


  /**
   *
   * @param title
   * @param textBody
   * @param i Which combobox element to pick, 1->borrow, 2->trade, 3->gift. i outside this domain throws exception
   */
  private void writeInFields(String title, String textBody, int i) {
    clickOn(titleField).write(title);
    clickOn(textBodyField).write(textBody);
    comboboxSelect(i, categoryComboBox);
  }




}
