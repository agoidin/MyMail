package Java.Connections;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Random;

import Java.Constants.ConnectionConstants;
import Java.Constants.IMAPCommands;

public class IMAPConnection extends Connection {
    
    public IMAPConnection() throws UnknownHostException, IOException {
        super(ConnectionConstants.IMAP_HOSTNAME, ConnectionConstants.IMAP_PORT_NUMBER);
        
        executeCommand(IMAPCommands.LOGIN + " " + new Account().USERNAME + " " + new Account().PASSWORD);
    }

    /**
     * Executes the IMAP command and prints all responses to System log
     * @param command user command to be executed
     * @return response
     * @throws IOException
     */
    @Override
    public String executeCommand(String command) throws IOException {
        String tag = generateTag();
        
        writer.print(tag + " " + command + ConnectionConstants.CRLF);
        writer.flush();

        String response = "";
        String newLine;
        boolean responseRecieved = false;

        while (!responseRecieved) {
            newLine = reader.readLine();
            response += newLine;
            // System.out.println(newLine);

            if(newLine.contains(tag + " OK"))
                responseRecieved = true;
        }

        return response;
    }

    /**
     * Generates tag for IMAP commands.
     * Tag format is one Upper-Case letter A-Z plus two digits 0-9
     * E.G. A01
     * @return generated tag
     */
    private String generateTag() {
        String tag = "";
        Random rand = new Random();
        tag += (char)(rand.nextInt(26) + 'a');
        tag += rand.nextInt((9 - 0) + 1) + 0;
        tag += rand.nextInt((9 - 0) + 1) + 0;

        return tag.toUpperCase();
    }    

    /* public static void main(String[] args) {
        try {
            IMAPConnection con = new IMAPConnection();
            con.executeCommand(IMAPCommands.LOGOUT);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    } */
}
