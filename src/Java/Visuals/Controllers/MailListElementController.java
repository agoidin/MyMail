package Java.Visuals.Controllers;

import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Java.Connections.IMAPController;
import Java.Constants.IMAPCommands;
import Java.Display.EmailContent;
import Java.Display.Flags;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class MailListElementController implements Initializable {

    @FXML
    private AnchorPane listElementPane;

    @FXML
    private Label mailSenderAddressLabel;
    @FXML
    private Label mailSubjectLabel;

    @FXML
    private Button openContentButton;
    @FXML
    private Button flagMailButton;
    @FXML
    private Button deleteMailButton;
    @FXML
    private ImageView flagMailButtonImage;
    @FXML
    private ImageView deleteMailButtonImage;

    private static MailListElementController instance;

    private ArrayList<Flags> mailflags;

    private String messageNumber;

    private boolean flagged = false;

    private String FLAG_IDLE_STYLE;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub
        // changeElementColor();
    }

    /**
     * MailListElementController contructor
     */
    public MailListElementController() {
        instance = this;
    }

    /**
     * @return the instance of MailListElementController
     */
    public static MailListElementController getInstance() {
        return instance;
    }

    @FXML
    public void openMailContent(MouseEvent event) throws IOException {

        FxmlLoader loader = new FxmlLoader();
        Pane mailContentPane = loader.getPage("MailContent");

        EmailContent content = new EmailContent(messageNumber);

        HashMap<String, String> messageContent = content.getMessageFields();

        MailContentController.getInstance().setSubject(messageContent.get("subject"));
        MailContentController.getInstance().setSenderAddress(messageContent.get("sender"));
        MailContentController.getInstance().setDate(messageContent.get("date"));
        MailContentController.getInstance().setMessageBody(messageContent.get("message"));

        BaseStateController.getInstance().setCenterToMainPane(mailContentPane);

        System.out.println(mailflags);
    }

    @FXML
    public void changeFlag(MouseEvent e) throws UnknownHostException, IOException {
        IMAPController controller = new IMAPController();
        controller.openFolder("INBOX");
        if(flagged) {
            controller.removeFlag(messageNumber, IMAPCommands.FLAGGED_FLAG);
            mailflags.remove(Flags.FLAGGED);
            flagged = false;
            // flagMailButtonImage.setStyle("-fx-image: url(\"/Resources/Icons/flagButton.png\")");
        } else {
            controller.addFlag(messageNumber, IMAPCommands.FLAGGED_FLAG);
            mailflags.add(Flags.FLAGGED);
            flagged = true;
            // flagMailButtonImage.setStyle("-fx-image: url(\"/Resources/Icons/flagButtonHover.png\")");
        }

        setElementColors();
    }
    
    public void setMailSenderAddressLabel(String senderAddress) {
        mailSenderAddressLabel.setText(senderAddress);
    }
    
    public void setMailSubjectLabel(String subject) {
        mailSubjectLabel.setText(subject);
    }
    
    public void setMessageNumber(String messageNumber) {
        this.messageNumber = messageNumber;
    }
    
    public void setElementColors() {
        String IDLE_ELEMENT_COLOR = (mailflags.contains(Flags.UNSEEN)) ? "white" : "transparent";
        String HOVER_ELEMENT_COLOR = "rgba(149,234,169, 0.1)";

        listElementPane.setStyle("-fx-background-color: " + IDLE_ELEMENT_COLOR);

        FLAG_IDLE_STYLE = "-fx-image: url(\"/Resources/Icons/flagButton.png\")";

        if(mailflags.contains(Flags.FLAGGED)) {
            flagged = true;
            FLAG_IDLE_STYLE = "-fx-image: url(\"/Resources/Icons/flagButtonHover.png\")";
            flagMailButtonImage.setStyle(FLAG_IDLE_STYLE);
        }

        //Change colors of AnchorPane once hovered
        listElementPane.setOnMouseEntered(e -> listElementPane.setStyle("-fx-background-color: " + HOVER_ELEMENT_COLOR));
        listElementPane.setOnMouseExited(e -> listElementPane.setStyle("-fx-background-color: " + IDLE_ELEMENT_COLOR));

        //Change colors of Delete and Flag images once openContentButton hovered
        openContentButton.setOnMouseEntered(e -> {
            flagMailButtonImage.setStyle(FLAG_IDLE_STYLE);
            deleteMailButtonImage.setStyle("-fx-image: url(\"/Resources/Icons/deleteMailButtonGrey.png\")");
        });
        openContentButton.setOnMouseExited(e -> {
            if(!mailflags.contains(Flags.FLAGGED))
                flagMailButtonImage.setStyle(null);
            deleteMailButtonImage.setStyle(null);
        });

        //Change colors of Delete image once Flag button hovered
        flagMailButton.setOnMouseEntered(e -> deleteMailButtonImage.setStyle("-fx-image: url(\"/Resources/Icons/deleteMailButtonGrey.png\")"));
        flagMailButton.setOnMouseExited(e -> {
            if(!mailflags.contains(Flags.FLAGGED))
                flagMailButtonImage.setStyle(null);
            deleteMailButtonImage.setStyle(null);
        });

        //Change colors of Flag image once Delete button hovered
        deleteMailButton.setOnMouseEntered(e -> flagMailButtonImage.setStyle(FLAG_IDLE_STYLE));
        deleteMailButton.setOnMouseExited(e -> {
            if(!mailflags.contains(Flags.FLAGGED))
                flagMailButtonImage.setStyle(null);
            deleteMailButtonImage.setStyle(null);
        });
    }
    
    public void setMailFlags(ArrayList<Flags> flags) {
        mailflags = flags;
    } 

}
