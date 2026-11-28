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
        //FXMLLOADER aanmaken
        FXMLLoader loader = new FXMLLoader();
        // FXML-bestand inladen
        loader.setLocation(MainWindow.class.getResource("Dashboard.fxml"));
        try {
            this.setContent(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //controller en model koppelen
        this.setController(DashboardControllerSimple.getController());
        this.setModel(DashboardModel.getDashboardModel());
        //de huidige klasse registreren als PropertyChangeListener bij model
        DashboardModel.getDashboardModel().setPropertyChangeListener(this);
    }
    //getter- en setter-methode voor content
    /*
    public void setContent(AnchorPane content) {
        this.content = content;
    }

    public AnchorPane getContent() {
        return content;
    }
    */
    //getter- en setter controller
    public void setController(DashboardControllerSimple controller){this.controller=controller;}
    public DashboardControllerSimple getController(){return this.controller;}

    //getter en setter model
    public void setModel(DashboardModel model){this.model=model;}
    public DashboardModel getModel(){return this.model;}

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
        System.out.println("CHANGE VIEW!!!!");
    }
}
