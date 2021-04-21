package Java.Display;

import java.io.IOException;
import java.util.HashMap;

import Java.Connections.IMAPController;
import Java.Visuals.Controllers.MailContentController;

/**
 * EmailContent
 */
public class EmailContent {

    private IMAPController controller;

    private HashMap<String, String> messageFields;

    public EmailContent(String messageNumber) throws IOException {
        controller = new IMAPController();

        controller.openFolder("INBOX");

        messageFields = new HashMap<String, String>();

        messageFields.put("sender", controller.getSender(messageNumber, true));
        messageFields.put("subject", controller.getSubject(messageNumber, true));
        messageFields.put("date", controller.getDate(messageNumber, true));
        messageFields.put("message", controller.getMessageBody(messageNumber));

        MailContentController.getInstance().setMessageId(controller.getId(messageNumber, true));

        controller.closeConnection();
    }

    public HashMap<String, String> getMessageFields() {
        return messageFields;
    }
}