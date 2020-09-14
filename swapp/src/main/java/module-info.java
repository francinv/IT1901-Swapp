module swapp {
    
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.controls;

    exports swapp.core;
    exports swapp.ui;

    opens swapp.ui to javafx.fxml;

}