package Java.Visuals.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import Java.Visuals.States.BaseState;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * FxmlLoader handles the routine of finding and opening the FXML file.
 */
public class FxmlLoader {
    private Pane view;

    /**
     * FxmlLoader class constructor
     */
    public FxmlLoader() {
    }

    /**
     * Reads the FXML file and returns the Pane its containing
     * @param fileName name of FXML file (without .fxml)
     * @return Pane got from FXML file
     */
    public Pane getPage(String fileName) {
        try {
            URL fileURL = BaseState.class.getResource("/Resources/JavaFX/FXFiles/" + fileName + ".fxml");
            if (fileURL == null)
                throw new FileNotFoundException("FXML file can't be found");

            new FXMLLoader();
            view = FXMLLoader.load(fileURL);
        } catch (IOException e) {
            // System.out.println("No page " + fileName);
            e.printStackTrace();
        }

        return view;
    }
}
