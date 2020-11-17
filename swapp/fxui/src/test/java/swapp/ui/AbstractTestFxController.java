package swapp.ui;

import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractTestFxController extends ApplicationTest {




    public void comboboxSelect(int i, ComboBox comboBox){
      if (i < 1 || i>3) {
        throw new IllegalArgumentException("i must be in [1, 3]");
      }
      clickOn(comboBox);
      for (int j=0;j<i;j++){

        type(KeyCode.DOWN);
      }
      type(KeyCode.ENTER);
      System.out.println(comboBox.getValue());
    }

    protected Stage getTopModalStage() {
      // Get a list of windows but ordered from top[0] to bottom[n] ones.
      // It is needed to get the first found modal window.
      final List<Window> allWindows = new ArrayList<>(new FxRobot().robotContext().getWindowFinder().listWindows());
      Collections.reverse(allWindows);

      return (Stage) allWindows
              .stream()
              .filter(window -> window instanceof Stage)
              .findFirst()
              .orElse(null);
    }


}
