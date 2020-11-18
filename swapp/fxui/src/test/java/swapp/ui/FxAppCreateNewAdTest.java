package swapp.ui;

import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

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
import org.testfx.matcher.base.WindowMatchers;
import swapp.core.Swapp;
import swapp.core.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class FxAppCreateNewAdTest extends AbstractTestFxController {
  private CreateAdController controller;
  private ComboBox<String> categoryComboBox;
  TextField titleField;
  TextArea textBodyField;
  Label display;
  private Button createAdButton;
  private Button cancel;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("createNewAd.fxml"));
    final Parent parent = loader.load();
    this.controller = loader.getController();
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
