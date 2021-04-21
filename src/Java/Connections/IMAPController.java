package Java.Connections;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import Java.Constants.IMAPCommands;
import Java.Display.Flags;

/**
 * IMAPController
 */
public class IMAPController extends IMAPConnection {

    public IMAPController() throws UnknownHostException, IOException {
        super();
    }

    public void openFolder(String folderName) throws IOException {
        executeCommand(IMAPCommands.SELECT_FOLDER + " " + folderName);
    }

    /**
     * Gets the mail flags from IMAP response 
     * @param messageNumber
     * @return the Arraylist of Flags assigned to message
     * @throws IOException
     */
    public ArrayList<Flags> getMessageFlags(String messageNumber) throws IOException {
        ArrayList<Flags> messagesFlags = new ArrayList<Flags>();
        
        String flagsResponse = executeCommand(IMAPCommands.FETCH + " " + messageNumber + " " + IMAPCommands.MESSAGE_FLAGS);
        if(flagsResponse.contains("\\")) {

            String[] flags = (flagsResponse.substring(flagsResponse.indexOf("\\")+1, flagsResponse.lastIndexOf("))", flagsResponse.indexOf("OK")))).split(" \\\\");

            for (String flag : flags) {
                messagesFlags.add(Flags.valueOf(flag.toUpperCase()));
            }

        }
        
        //IMAP doesn't return the UNSEEN flag by default, so adding it manully if seen flag doesn't exists
        if(!messagesFlags.contains(Flags.SEEN)){
            messagesFlags.add(Flags.UNSEEN);
        }
        
        return messagesFlags;
    }

    /**
     * Adds the flag to message
     * @param messageNumber message to add flag to 
     * @param flag the flag to add
     * @throws IOException
     */
    public void addFlag(String messageNumber, String flag) throws IOException {
        executeCommand( IMAPCommands.STORE + " " + messageNumber + " " + IMAPCommands.ADD_FLAG + " (" + flag + ")");
    }

    /**
     * Adds the flag to message
     * @param messageNumber message to remove flag from
     * @param flag the flag to remove
     * @throws IOException
     */
    public void removeFlag(String messageNumber, String flag) throws IOException {
        executeCommand( IMAPCommands.STORE + " " + messageNumber + " " + IMAPCommands.REMOVE_FLAG + " (" + flag + ")");
    }

    /**
     * Gets the mail sender address from IMAP response 
     * @param messageNumber message number to fetch the info from
     * @return sender address
     * @throws IOException
     */
    public String getSender(String messageNumber, boolean setSeen) throws IOException {
        String senderBody;
        if (!setSeen) senderBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY_PEEK + IMAPCommands.SENDER_FIELD);
        else senderBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY + IMAPCommands.SENDER_FIELD);
        return senderBody.substring(senderBody.lastIndexOf(": ") + 2, senderBody.indexOf(")", senderBody.lastIndexOf(":")));
    }

    /**
     * Gets the mail subject from IMAP response 
     * @param messageNumber message number to fetch the info from
     * @return mail subject
     * @throws IOException
     */
    public String getSubject(String messageNumber, boolean setSeen) throws IOException {
        String subjectBody;
        if(!setSeen) subjectBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY_PEEK + IMAPCommands.SUBJECT_FIELD);
        else subjectBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY + IMAPCommands.SUBJECT_FIELD);
        return subjectBody.substring(subjectBody.lastIndexOf(": ") + 2, subjectBody.indexOf("OK") - 5);
    }

    public String getDate(String messageNumber, boolean setSeen) throws IOException {
        String dateBody;
        if(!setSeen) dateBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY_PEEK + IMAPCommands.DATE_FIELD);
        else dateBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY + IMAPCommands.DATE_FIELD);
        return dateBody.substring(dateBody.lastIndexOf(": ") + 2, dateBody.indexOf("OK") - 5);
    }

    /**
     * Gets the mail subject from IMAP response 
     * @param messageNumber message number to fetch the info from
     * @return mail subject
     * @throws IOException
     */
    public String getId(String messageNumber, boolean setSeen) throws IOException {
        String idBody;
        if(!setSeen) idBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY_PEEK + IMAPCommands.ID_FIELD);
        else idBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY + IMAPCommands.ID_FIELD);
        return idBody.substring(idBody.lastIndexOf(": ") + 2, idBody.indexOf(")", idBody.lastIndexOf(":")));
    }

    public String getMessageBody(String messageNumber) throws IOException {
        String messageBody = executeCommand(IMAPCommands.FETCH + " " + messageNumber  + " " + IMAPCommands.MESSAGE_BODY + IMAPCommands.MIME_MESSAGE_CONTENT_FIELD);

        return messageBody.substring(messageBody.indexOf("}") + 1, messageBody.indexOf("OK") - 5);
    }

    public List<String> getSearchList(String folderName, Flags flag)throws IOException {
        openFolder(folderName);
        String response = executeCommand(IMAPCommands.SEARCH + " " + flag);
        if (response.length() < 54) return Collections.emptyList();

        String[] searchList = response.substring(response.indexOf("* SEARCH") + 9, response.indexOf("OK") - 4).split(" ");

        return Arrays.asList(searchList);
    }

    public List<String> getSearchList(String folderName, String searchString)throws IOException {
        if (searchString.equals("")) return getSearchList(folderName, Flags.ALL);

        openFolder(folderName);
        String response = executeCommand(IMAPCommands.SEARCH + " " + "TEXT " + searchString);
        System.out.println(response.length());
        if (response.length() < 62) return Collections.emptyList();

        String[] searchList = response.substring(response.indexOf("* SEARCH") + 9, response.indexOf("OK") - 4).split(" ");

        return Arrays.asList(searchList);
    }

    public void closeConnection() throws IOException {
        super.closeConnection(IMAPCommands.LOGOUT);
    }

    /* public static void main(String[] args) {
        IMAPController c;
        try {
            c = new IMAPController();
            System.out.println("\n\n");
            c.openFolder("inbox");
            // System.out.println("\n\n");
            // // System.out.println("\nCUT STRING: " + c.getSubject("1", false));
            // System.out.println("\nCUT STRING: " + c.getSearchList("inbox", "frie!"));
            System.out.println(c.getDate("1", false));
            // System.out.println("\n\n");
            c.closeConnection();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    } */
}