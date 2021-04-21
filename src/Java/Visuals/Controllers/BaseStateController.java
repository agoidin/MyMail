package Java.Visuals.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Java.Display.Flags;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * BaseStateController
 */
public class BaseStateController implements Initializable {

    @FXML private BorderPane basePane;
    @FXML private BorderPane mainPane;
    @FXML private BorderPane sidePane;

    @FXML private ButtonBar windowButtonBar;

    @FXML private Button closeButton;
    @FXML private Button maximizeButton;
    @FXML private Button minimizeButton;

    @FXML private Button sideMenuButton;
    @FXML private Button newMailButton;
    @FXML private Button mailListButton;
    @FXML private Button accountListButton;

    private static BaseStateController instance;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

    /**
     * BaseStateController constructor.
     */
    public BaseStateController() {
        instance = this;
    }

    /**
     * @return the instance of BaseStateController.
     */
    public static BaseStateController getInstance() {
        return instance;
    }

    /**
     * Closes window while the closeWindow button is clicked.
     * @param event
     */
    @FXML
    public void closeWindow(MouseEvent event) {
        Stage stage = (Stage) basePane.getScene().getWindow();
        stage.close();
    }

    /**
     * Minimizes window while the closeWindow button is clicked.
     * @param event
     */
    @FXML
    public void minimizeWindow(MouseEvent event) {
        Stage stage = (Stage) basePane.getScene().getWindow();
        stage.setIconified(true);
    }

    /**
     * Maximizes window while the closeWindow button is clicked.
     * @param event
     */
    @FXML
    public void maximizeWindow(MouseEvent event) {
        Stage stage = (Stage) basePane.getScene().getWindow();
        if (!stage.isMaximized())
            stage.setMaximized(true);
        else
            stage.setMaximized(false);
    }

    /**
     * Loads MailList FXML file and set to the center of sidePane.
     * @param event
     * @throws IOException
     */
    @FXML
    public void openMailList(MouseEvent event) throws IOException {
            FxmlLoader loader = new FxmlLoader();
            Pane mailList = loader.getPage("MailList");
            sidePane.setCenter(mailList);
            MailListController.getInstance().updateList(Flags.ALL);
    }

    /**
     * Loads AccountList FXML file and set to the center of sidePane.
     * @param event
     * @throws IOException
     */
    @FXML
    public void openAccountList(MouseEvent event) throws IOException {
            FxmlLoader loader = new FxmlLoader();
            Pane accountList = loader.getPage("AccountList");
            sidePane.setCenter(accountList);
    }

    /**
     * If center of sidePane is used, closes it.
     * If it is empty loads MailList FXML file and set to the center of sidePane.
     * @param event
     * @throws IOException
     */
    @FXML
    public void sideMenuTrigger(MouseEvent event) throws IOException {
        if(sidePane.getCenter() == null)
            openMailList(event);
        else {
            sidePane.setCenter(null);
        }
    }

    /**
     * Loads NewMail FXML file and set to the center of mainPane.
     * @param event
     */
    @FXML
    public void openNewMailPane(MouseEvent event) { 
        FxmlLoader loader = new FxmlLoader();
        Pane newMailPane = loader.getPage("NewMail");
        
        setCenterToMainPane(newMailPane);
    }

    /**
     * Sets the pane to the center of mainPane.
     * @param content pane to set 
     */
    public void setCenterToMainPane(Pane content) {
        mainPane.setCenter(content);
        
        windowButtonBar.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    /**
     * Removes the pane from the center of mainPane
     */
    public void removeCenterFromMainPane() {
        mainPane.setCenter(null);

        windowButtonBar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
    }
}