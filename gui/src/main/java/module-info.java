module org.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires Framework;
    requires java.desktop;

    opens gui to javafx.fxml;
    exports gui;
}