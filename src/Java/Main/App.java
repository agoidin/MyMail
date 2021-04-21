package Java.Main;

import Java.Visuals.States.BaseState;
import javafx.application.Application;

public class App {
    public static void main(String[] args){
        Application.launch(BaseState.class, args);
    }
}
