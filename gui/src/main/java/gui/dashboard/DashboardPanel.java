package gui.dashboard;

import gui.MainWindow;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import widgets.WidgetPanel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class DashboardPanel extends WidgetPanel implements PropertyChangeListener {
    private DashboardControllerSimple controller;
    private DashboardModel model;
    private AnchorPane content;

    public void createDashboardPanel() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindow.class.getResource("Dashboard.fxml"));
        try {
            super.setContent(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("CHANGE VIEW!!!!");
    }
}
