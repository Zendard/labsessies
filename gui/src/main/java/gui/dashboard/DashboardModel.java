package gui.dashboard;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import widgets.WidgetPanel;

public class DashboardModel {
    private final StringProperty statusMessage =
            new SimpleStringProperty("Dashboard ready");
    private final ObservableList<WidgetPanel> widgets =
            FXCollections.observableArrayList();

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
}
