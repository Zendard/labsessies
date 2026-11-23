package gui;

import javafx.scene.Group;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            // Hier komt de code voor het loginvenster
            new LoginWindow(root);
        }
        public static void main(String[] args) {
            launch(args);
        }
    }
