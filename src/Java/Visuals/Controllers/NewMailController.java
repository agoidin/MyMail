package Java.Visuals.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Java.Connections.Account;
import Java.Events.SendEmail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

/**
 * NewMailController
 */
public class NewMailController implements Initializable {

    @FXML
    private TextField newMailSenderAddress;
    @FXML
    private TextField newMailRecipientAddress;
    @FXML
    private TextField newMailSubject;
    @FXML
    private TextArea newMailBody;

    @FXML
    private HBox buttonBox;

    @FXML
    private Button sendNewMailButton;
    @FXML
    private Button deleteNewMailButton;
    @FXML
    private Button attachButton;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        newMailSenderAddress.setText(new Account().EMAIL_ADDRESS);
    }

    /**
     * Calls the contructor of SendEmail class and sets input from TextFields as a
     * parameters.
     * 
     * @param event
     * @throws IOException
     */
    @FXML
    public void sendNewMail(MouseEvent event) throws IOException {
        new SendEmail(  newMailSenderAddress.getText(),
                        newMailRecipientAddress.getText(),
                        newMailSubject.getText(),
                        newMailBody.getText() );

        BaseStateController.getInstance().removeCenterFromMainPane();
    }

    /**
     * Cleanes and quits the the center of mainPane. 
     * @param event
     */
    @FXML
    public void deleteNewMail(MouseEvent event) {
        BaseStateController.getInstance().removeCenterFromMainPane();
    }

    @FXML
    public void attachFile(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(attachButton.getScene().getWindow());
        String filePath = selectedFile.getAbsolutePath();
        String filename = selectedFile.getName();
        System.out.println(filePath);
        System.out.println(filename);
    }
    
    
}
