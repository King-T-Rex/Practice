module com.example.cycle {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.cycle to javafx.fxml;
    opens controller to javafx.fxml;

    exports com.example.cycle;
    exports controller;
    exports model;
}