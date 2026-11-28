package gui;

import gui.dashboard.DashboardModel;
import gui.dashboard.DashboardPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
     /* test voor loginwindow
    @Override
        public void start(Stage primaryStage) {
            var loginWindow = new LoginWindow(primaryStage);
            primaryStage.setTitle("Login window");
            primaryStage.setScene(loginWindow);
            primaryStage.show();
        }
        */
        public static void main(String[] args) {
            launch(args);
        }

    @Override
    public void start(Stage PrimaryStage){
        DashboardPanel dashboardPanel = new DashboardPanel();
        dashboardPanel.createDashboardPanel();
        VBox vBox = new VBox(dashboardPanel.getContent());
        PrimaryStage.setScene(new Scene(vBox));
        PrimaryStage.show();
    }

    }
