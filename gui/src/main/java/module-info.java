module org.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires Framework;

    opens gui to javafx.fxml;
    exports gui;
}