package swapp.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FxApp extends Application {

    @Override
    public void start(final Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        final Parent parent = loader.load();
        primaryStage.setTitle("SwApp!");
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static void main(final String[] args) {
        launch(args);
    }
}