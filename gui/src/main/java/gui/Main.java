package gui;

import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
        @Override
        public void start(Stage primaryStage) {
            // Hier komt de code voor het loginvenster
            var loginWindow = new LoginWindow(primaryStage);
            primaryStage.setScene(loginWindow);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
