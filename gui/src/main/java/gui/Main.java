package gui;

import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
        @Override
        public void start(Stage primaryStage) {
            var loginWindow = new LoginWindow(primaryStage);
            primaryStage.setTitle("Login window");
            primaryStage.setScene(loginWindow);
            primaryStage.show();
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
