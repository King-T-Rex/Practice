module com.example.graphic {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.graphic to javafx.fxml;
    exports com.example.graphic;
    exports controller;
    opens controller to javafx.fxml;
}