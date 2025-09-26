module com.example.arif_1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.arif_1 to javafx.fxml;
    opens controller to javafx.fxml;

    exports com.example.arif_1;
    exports controller;
    exports model;
}