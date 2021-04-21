package Java.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Java.Connections.IMAPController;

public class EmailsList {

    private HashMap<String, ArrayList<String>> emailList;

    public EmailsList(String folder, Flags flag) throws IOException {
        IMAPController controller = new IMAPController();

        emailList = new HashMap<String,ArrayList<String>>();

        for (String number : controller.getSearchList(folder, flag)) {
            emailList.put(number, new ArrayList<String>() {
                private static final long serialVersionUID = 1L;

                {
                add(controller.getSender(number, false));
                add(controller.getSubject(number, false));
            }});
        }
        controller.closeConnection();
    }

    public EmailsList(String folder, String searchString) throws IOException {
        IMAPController controller = new IMAPController();

        emailList = new HashMap<String,ArrayList<String>>();

        for (String number : controller.getSearchList(folder, searchString)) {
            emailList.put(number, new ArrayList<String>() {
                private static final long serialVersionUID = 1L;

                {
                add(controller.getSender(number, false));
                add(controller.getSubject(number, false));
            }});
        }
        controller.closeConnection();
    }

    /**
     * @return generated mail list
     */
    public HashMap<String, ArrayList<String>> getEmailList() {
        return emailList;
    }
}
