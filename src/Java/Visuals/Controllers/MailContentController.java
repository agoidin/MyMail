package Java.Visuals.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class MailContentController implements Initializable {

    @FXML private Label mailSubjectLabel;
    @FXML private Label mailSenderAddressLabel;
    @FXML private Label mailDateLabel;
    @FXML private TextArea messageBodyArea;

    @FXML private HBox buttonBox;

    @FXML private Button closeMailContentButton;
    @FXML private Button replyToMailButton;
    @FXML private Button deleteMailButton;
    @FXML private Button forwardMailButton;

    private static MailContentController instance;

    private String messageId;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
    }

    /**
     * MailContentController contructor
     */
    public MailContentController() {
        instance = this;
    }

    /**
     * @return the instance of MailContentController
     */
    public static MailContentController getInstance() {
        return instance;
    }

    @FXML
    public void closeMailContentPane(MouseEvent event) {
        BaseStateController.getInstance().removeCenterFromMainPane();
    }
    
    @FXML
    public void replyToMail(MouseEvent event) {
        System.out.println(messageId);
    }

    @FXML
    public void forwardMail(MouseEvent event) {

    }

    @FXML
    public void deleteMail(MouseEvent event) {
        BaseStateController.getInstance().removeCenterFromMainPane();
    }

    public void setSubject(String subject) {
        mailSubjectLabel.setText(subject);
    }

    public void setSenderAddress(String senderAddress) {
        mailSenderAddressLabel.setText(senderAddress);
    }

    public void setDate(String date) {
        mailDateLabel.setText(date);
    }

    public void setMessageBody(String messageBody) {
        // mailMessageBodyLabel.setText(messageBody);
        messageBodyArea.setText(messageBody);
    }

    public void setMessageId(String id) {
        // mailMessageBodyLabel.setText(messageBody);
        messageId = id;
    }
    // public void setBody(String messageBody) {
    //     this.messageBody.setText(messageBody);
    // }
}