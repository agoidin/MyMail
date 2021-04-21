package Java.Connections;

import java.io.IOException;
import java.net.UnknownHostException;

import Java.Constants.ConnectionConstants;
import Java.Constants.SMTPCommands;

/**
 * SMTPConnection
 */

public abstract class SMTPConnection extends Connection {

    protected SMTPConnection() throws UnknownHostException, IOException {
        super(ConnectionConstants.SMTP_HOSTNAME, ConnectionConstants.SMTP_PORT_NUMBER);
        System.out.println("\n\n");
        executeCommand(SMTPCommands.HELO + " " + ConnectionConstants.SMTP_HOSTNAME);
    }

    /**
     * Executes the command and prints all responses to System log
     * 
     * @param command user command to be executed
     * @throws IOException
     */
    @Override
    protected String executeCommand(String command) throws IOException {
        writer.println(command);
        System.out.println(command);
        String line = reader.readLine();
        System.out.println(line);

        return line;
    }
} 
