package Java.Message;

import Java.Constants.ConnectionConstants;
import Java.Constants.EmailPartsConstants;
import Java.Constants.SMTPCommands;

public class Message {
    
    private String message = "";

    public Message(String sender, String recipient, String subject, String body) {

        message += EmailPartsConstants.USER_AGENT_HEADER_FIELD + ConnectionConstants.LF;
        message += EmailPartsConstants.TO_HEADER_FIELD + " " + recipient + ConnectionConstants.LF;
        message += EmailPartsConstants.FROM_HEADER_FIELD + " " + sender + ConnectionConstants.LF;
        message += EmailPartsConstants.SUBJECT_HEADER_FIELD + " " + subject + ConnectionConstants.LF;

        message += body + SMTPCommands.MESSAGE_END;
    }

    public String getMessage() {
        return message;
    }
}
