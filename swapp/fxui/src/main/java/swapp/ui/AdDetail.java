package swapp.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import swapp.core.Ad;
import swapp.core.User;
// Perhaps change this class to TransitionHandler? Cant use it as controller because the Constructor
public class AdDetail {
  Ad ad;


  AdDetail(Ad ad, Stage primaryStage){
    this.ad = ad;
    try {
      /*
      FXMLLoader loader = FXMLLoader.load(getClass().getResource("AdDetail.fxml"));
      loader.setController(this);
      System.out.println("hulk");
      Parent parent = (Parent) loader.load();

      primaryStage.setScene(new Scene(parent));

      */
      Parent parent = FXMLLoader.load(getClass().getResource("AdDetail.fxml"));
      primaryStage.setScene(new Scene(parent));
      //AdDetailController controller = loader.getController();
      //controller.initData(ad);
      //controller.customInitialize();

    }
    catch (Exception e){
      System.out.println("Something wrong with loading fxml file from AdDetail");
    }

  }


}
