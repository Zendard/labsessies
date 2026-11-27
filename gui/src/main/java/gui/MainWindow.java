package gui;

import eventbroker.EventBroker;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import network.Network;

public class MainWindow extends Scene {
    Stage primaryStage;

    public MainWindow(Stage primaryStage,int port) {
        primaryStage.setMaximized(true);
                var root = new VBox(16);
        root.setAlignment(Pos.CENTER);
        super(root,200,150);
        this.primaryStage=primaryStage;
        this.setFill(Color.LIGHTGRAY);

        var task=new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                var eventBroker=EventBroker.getEventBroker();
                eventBroker.start();
                var network = new Network(port);
                return null;
            }
        };

    }
}
