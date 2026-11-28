package gui;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Scene {
    Stage primaryStage;
    StringProperty portProperty;
    Label error;

    public LoginWindow(Stage primaryStage) {
        var root = new VBox(16);
        root.setAlignment(Pos.CENTER);
        super(root,500,150);
        this.primaryStage=primaryStage;
        this.setFill(Color.LIGHTGRAY);

        var text = new Label("Set port number:");
        text.setFont(Font.font(16));
        root.getChildren().add(text);

        var textField= new TextField();
        textField.setMaxWidth(64);
        textField.setTooltip(new Tooltip("Port number"));
        textField.setAlignment(Pos.CENTER);
        portProperty=textField.textProperty();
        root.getChildren().add(textField);

        error = new Label();
        error.setFont( Font.font(16));
        error.setTextFill(Color.RED);
        root.getChildren().add(error);

        var button = new Button("Ok");
        button.setFont(Font.font(16));
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {submitHandler();}
        });
        root.getChildren().add(button);
    }

    void submitHandler(){
        try {
            int port = Integer.parseInt(portProperty.getValue());
            if (port<0 || port>65535){
                portProperty.setValue("");
               setError("Please enter a number between 0 and 65535");
            }else{
                primaryStage.setScene(new MainWindow(primaryStage,port));
            }
        } catch (NumberFormatException e) {
            portProperty.setValue("");
            setError("Please enter a number!");
        }
    }

    void setError(String errorMessage){
        System.out.println(errorMessage);
        error.setText(errorMessage);
    }
}
