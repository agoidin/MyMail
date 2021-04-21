package Java.Visuals.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import Java.Display.Flags;
import Java.Connections.IMAPController;
import Java.Display.EmailsList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MailListController implements Initializable {

    @FXML private AnchorPane listPane;
    @FXML private VBox mailListBox;

    @FXML private TextField searchBar;

    @FXML private MenuButton filterMailListBox;
    @FXML private MenuItem allMenuItem;
    @FXML private MenuItem unseenMenuItem;
    @FXML private MenuItem seenMenuItem;
    @FXML private MenuItem answeredMenuItem;
    @FXML private MenuItem flaggedMenuItem;

    @FXML private Button searchButton;
    @FXML private Button refreshListButton;
    
    @FXML private Label mailSenderAddressLabel;
    @FXML private Label mailSubjectLabel;
    
    private static MailListController instance;

    private EmailsList emailList;

    private String currentFolder = "INBOX";
    private Flags currentFlag;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub

    }

    /**
     * MailListController constructor
     */
    public MailListController() {
        instance = this;
    }

    /**
     * @return the instance of MailListController
     */
    public static MailListController getInstance() {
        return instance;
    }

    public void updateList(Object input) throws IOException {
        mailListBox.getChildren().clear();

        IMAPController controller = new IMAPController();
        controller.openFolder("inbox");
        if(input instanceof Flags) {
            currentFlag = (Flags) input;
            emailList = new EmailsList(currentFolder, currentFlag);
        }
        else if (input instanceof String) {
            String searchString = (String) input;
            emailList = new EmailsList(currentFolder, searchString);
        }

        HashMap<String, ArrayList<String>> list = emailList.getEmailList();

        for (Map.Entry<String, ArrayList<String>> entry : list.entrySet()) {
            String address = entry.getValue().get(0);
            String subject = entry.getValue().get(1);

            FxmlLoader loader = new FxmlLoader();
            Pane listElement = loader.getPage("MailListElement");
            
            MailListElementController.getInstance().setMessageNumber(entry.getKey());
            MailListElementController.getInstance().setMailFlags(controller.getMessageFlags(entry.getKey()));
            MailListElementController.getInstance().setElementColors();

            MailListElementController.getInstance().setMailSenderAddressLabel(address);
            MailListElementController.getInstance().setMailSubjectLabel(subject);

            mailListBox.getChildren().add(listElement);
            // mailListBox.getChildren().add(createListElement(address, subject));
        }
    }

    @FXML
    public void searchInList(MouseEvent event) throws IOException {
        System.out.println("Pressed: " + searchBar.getText());
        updateList(searchBar.getText());
    }

    @FXML
    public void refreshList(MouseEvent event) throws IOException {
        updateList(currentFlag);
    }

    @FXML
    public void filterByAll(ActionEvent event) throws IOException {
        currentFlag = Flags.ALL;
        updateList(currentFlag);
    }

    @FXML
    public void filterByUnseen(ActionEvent event) throws IOException {
        currentFlag = Flags.UNSEEN;
        updateList(currentFlag);
    }

    @FXML
    public void filterBySeen(ActionEvent event) throws IOException {
        currentFlag = Flags.SEEN;
        updateList(currentFlag);
    }

    @FXML
    public void filterByAnswered(ActionEvent event) throws IOException {
        currentFlag = Flags.ANSWERED;
        updateList(currentFlag);
    }

    @FXML
    public void filterByFlagged(ActionEvent event) throws IOException {
        currentFlag = Flags.FLAGGED;
        updateList(currentFlag);
    }
    
    /* public Button createListElement(String address, String subject) {
        Label mailAddress = new Label(address);
        Label mailSubject = new Label(subject);

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(mailAddress, mailSubject);
        
        Button button = new Button("", pane);
        button.setPrefSize(298, 70);
        button.setOnAction((ActionEvent)->{
            System.out.println("Pressed!");
        });

        return button;
    } */
}
