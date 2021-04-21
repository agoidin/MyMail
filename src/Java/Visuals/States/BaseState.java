package Java.Visuals.States;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class BaseState extends Application {

    @FXML private BorderPane mainPane;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("MyMail");

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Resources/JavaFX/FXFiles/BaseState.fxml"));
        Parent root = fxmlLoader.load();
        Scene baseState = new Scene(root);

        primaryStage.setScene(baseState);
        primaryStage.show();
    }
}