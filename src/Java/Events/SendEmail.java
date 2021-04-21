package Java.Events;

import java.io.IOException;
import java.net.UnknownHostException;

import Java.Connections.SMTPConnection;
import Java.Constants.SMTPCommands;
import Java.Message.Message;

public class SendEmail extends SMTPConnection {

    public SendEmail(String senderAddress, String receiverAddress, String subjectContent, String messageContent)
            throws UnknownHostException, IOException {
        
        super();

        executeCommand(SMTPCommands.MAIL_FROM + "<" + senderAddress + ">");

        executeCommand(SMTPCommands.RCPT_TO + "<" + receiverAddress + "> NOTIFY=success,failure");

        executeCommand(SMTPCommands.DATA);

        executeCommand(new Message(senderAddress, receiverAddress, subjectContent, messageContent).getMessage());

        closeConnection(SMTPCommands.QUIT);

        System.out.println("\n\n");
    }

    /* public static void main(String[] args) {
        String receiverAddress = "tom@localhost";
        String senderAddress = "goidin@localhost";
        String subjectContent = "Test From Alex";
        String messageContent = "Hi, Tom! It is a test message...\nLet's make multiple lines to see if it worked\n\nSee you soon!";

        try {
            new SendEmail(senderAddress, receiverAddress, subjectContent, messageContent);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } */
}