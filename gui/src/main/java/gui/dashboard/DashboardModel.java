package gui.dashboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import widgets.WidgetPanel;

import java.beans.PropertyChangeListener;

public class DashboardModel {
    private final StringProperty statusMessage =
            new SimpleStringProperty("Dashboard ready");
    private final ObservableList<WidgetPanel> widgets =
            FXCollections.observableArrayList();

    private final static DashboardModel model = new DashboardModel();

    public static DashboardModel getDashboardModel(){return model;}

    public StringProperty statusMessageProperty() {
        return statusMessage;
    }
    public String getStatusMessage() {
        return statusMessage.get();
    }
    public void setStatusMessage(String newMessage) {
        statusMessage.set(newMessage);
    }
    public ObservableList<WidgetPanel> getWidgets() {
        return widgets;
    }

    public void setPropertyChangeListener(WidgetPanel pcl){widgets.add(pcl);}
}
