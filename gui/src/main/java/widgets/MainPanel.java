package widgets;

import gui.MainWindow;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class MainPanel extends  WidgetPanel{

    public MainPanel () {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainWindow.class.getResource("Main.fxml"));
        try {
            super.setContent(loader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
